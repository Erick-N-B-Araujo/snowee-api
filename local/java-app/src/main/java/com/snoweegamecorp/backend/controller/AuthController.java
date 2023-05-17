package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.dto.LoginDTO;
import com.snoweegamecorp.backend.dto.UserDTO;
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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity login(@RequestBody LoginModel loginModel) {
        UserModel user = userRepository.findByEmail(loginModel.getUsername());
        if (user != null){
            return new ResponseEntity(
                    new LoginDTO(authService.Login(loginModel)), HttpStatus.OK
            );
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@Valid @RequestBody UserModel user)
    {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user.setCreatedAt(LocalDateTime.now());
        return new ResponseEntity(
                new UserDTO().makeDTO(userRepository.save(user)), HttpStatus.CREATED
        );
    }

}
