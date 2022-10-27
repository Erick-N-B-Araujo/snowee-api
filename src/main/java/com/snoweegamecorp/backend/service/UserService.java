package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.PermissionModel;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.model.actions.user.UserInsert;
import com.snoweegamecorp.backend.repository.PermissionRepository;
import com.snoweegamecorp.backend.repository.UserRepository;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username);
        if (user == null){
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: "+ username);
        return user;
    }

    public Optional<LoginModel> Login(Optional<LoginModel> userLogin){
        UserModel user = userRepository.findByEmail(userLogin.get().getUsername());
        if (user != null){
            if (passwordEncoder.matches(user.getPassword(), userLogin.get().getPassword())){
                String auth = userLogin.get().getUsername() + ":" + userLogin.get().getPassword();
                byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                userLogin.get().setToken(authHeader);
                userLogin.get().setId(user.getId());
                userLogin.get().setPermissions(user.getPermissions());
                return userLogin;
            }
        }
        return null;
    }
}
