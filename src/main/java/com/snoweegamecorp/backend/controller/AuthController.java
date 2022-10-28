package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.LoginRepository;
import com.snoweegamecorp.backend.repository.UserRepository;
import com.snoweegamecorp.backend.service.AuthService;
import com.snoweegamecorp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginModel authentication(@RequestBody LoginModel userlogin){
        return authService.Login(userlogin);
    }

    /*@PostMapping("/signin")
    public UserModel signin(@Valid @RequestBody UserModel user)
    {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return userRepository.save(user);
    }*/
}
