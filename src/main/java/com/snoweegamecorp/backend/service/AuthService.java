package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.LoginRepository;
import com.snoweegamecorp.backend.repository.PermissionRepository;
import com.snoweegamecorp.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public LoginModel Login(LoginModel userLogin){
        UserModel user = userRepository.findByEmail(userLogin.getUsername());
        if (user != null){
            if (passwordEncoder.matches(userLogin.getPassword(),user.getPassword())){
                //String auth = userLogin.getUsername() + ":" + userLogin.getPassword();
                //byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
                //String authHeader = "Basic " + new String(encodedAuth);
                //userLogin.setToken(authHeader);
                userLogin.setId(user.getId());
                String encodedPass = passwordEncoder.encode(userLogin.getPassword());
                userLogin.setPassword(encodedPass);
                userLogin.setToken(userLogin.getToken());
                userLogin.setPermissions(user.getPermissions());
                loginRepository.save(userLogin);
                return userLogin;
            }
        }
        return null;
    }
}
