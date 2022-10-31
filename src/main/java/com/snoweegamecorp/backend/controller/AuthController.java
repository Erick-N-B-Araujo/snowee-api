package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.PermissionModel;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.LoginRepository;
import com.snoweegamecorp.backend.repository.PermissionRepository;
import com.snoweegamecorp.backend.repository.UserRepository;
import com.snoweegamecorp.backend.service.AuthService;
import com.snoweegamecorp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PermissionRepository permissionRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginModel authentication(@RequestBody LoginModel userlogin) {
        LoginModel userLogged = authService.Login(userlogin);
        return loginRepository.save(userLogged);
    }

    @PatchMapping("/login/{id}/logged")
    public LoginModel secondLogin(@PathVariable Long id, @RequestBody LoginModel userlogin){
        return loginRepository
                .findById(id)
                .map(userLogged -> {
                    userLogged.setLoggedAt(LocalDateTime.now());
                    userLogged.setToken(userlogin.getToken());
                    loginRepository.save(userLogged);
                    return userLogged;
                })
                .orElse(null);
    }

    @GetMapping("/login/{username}")
    public LoginModel getById(@PathVariable String username) {
        return loginRepository
                .findByUsername(username);
    }

    @GetMapping("/login")
    public LoginModel getByTopId() {
        return loginRepository
                .findTopByOrderByIdDesc();
    }

    @PostMapping("/signin")
    public UserModel signin(@Valid @RequestBody UserModel user)
    {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return userRepository.save(user);
    }

    @GetMapping("/signin/{id}")
    public Optional<UserModel> getSignUpByID(@PathVariable Long id) {
        return userRepository.findById(id);
    }
    @GetMapping("/signin/username/{username}")
    public UserModel getSignUpByUsername(@PathVariable String username) {
        return userRepository.findByEmail(username);
    }

}
