package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.dto.LoginDTO;
import com.snoweegamecorp.backend.dto.UserDTO;
import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.LoginRepository;
import com.snoweegamecorp.backend.repository.PermissionRepository;
import com.snoweegamecorp.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService{

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private  LoginRepository loginRepository;

    public LoginModel Login(LoginModel loginModel){
        UserModel user = userRepository.findByEmail(loginModel.getUsername());
        if (user != null){
            if (passwordEncoder.matches(loginModel.getPassword(),user.getPassword())){
                loginModel.setId(user.getId());
                loginModel.setFirstname(user.getFirstName());
                loginModel.setLastname(user.getLastName());
                loginModel.setUsername(user.getUsername());
                loginModel.setProfileImg(user.getProfileImgUrl());
                loginModel.setLoggedAt(LocalDateTime.now());
                return loginRepository.save(loginModel);
            } else {
                return null;
            }
        }
        return null;
    }
}
