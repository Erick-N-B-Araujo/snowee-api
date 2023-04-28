package com.snoweegamecorp.backend.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snoweegamecorp.backend.dto.PermissionDTO;
import com.snoweegamecorp.backend.model.PermissionModel;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/permissions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PermissionController {

    @Autowired
    private PermissionRepository repository;

    @PostMapping
    public ResponseEntity savePermission(@Valid @RequestBody PermissionModel permission)
    {
        repository.save(permission);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<PermissionDTO>> getAllPermission(){
        return ResponseEntity
                .ok()
                .body(
                        new PermissionDTO()
                                .getAllPermission(
                                        repository
                                                .findAll()));
    }
    @GetMapping("/users")
    public ResponseEntity<List<PermissionDTO>> getAllPermissionUsers(){
        return ResponseEntity
                .ok()
                .body(
                        new PermissionDTO()
                                .getAllPermissionUsers(
                                        repository
                                                .findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity getPermissionById(@PathVariable Long id) {
        if (repository.existsById(id)){
            return ResponseEntity
                    .ok()
                    .body(repository
                            .findById(id));
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<PermissionModel> putPermission(@Valid @RequestBody PermissionModel permission){

        return ResponseEntity.ok(repository.save(permission));
    }

    @DeleteMapping("{id}")
    public void deletePermissionById(@PathVariable Long id){
        repository
                .deleteById(id);
    }
}
