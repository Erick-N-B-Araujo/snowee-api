package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.PermissionModel;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PermissionController {

    @Autowired
    private PermissionRepository repository;

    @PostMapping
    public PermissionModel savePermission(@RequestBody PermissionModel permission)
    {
        return repository
                .save(permission);
    }

    @GetMapping()
    public List<PermissionModel> getAllPermission(){
        return repository
                .findAll();
    }

    @GetMapping("{id}")
    public PermissionModel getPermissionById(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                ));
    }

    @PutMapping
    public ResponseEntity<PermissionModel> putPermission(@RequestBody PermissionModel permission){
        return ResponseEntity.ok(repository.save(permission));
    }

    @DeleteMapping("{id}")
    public void deletePermissionById(@PathVariable Long id){
        repository
                .deleteById(id);
    }
}
