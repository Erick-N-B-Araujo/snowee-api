package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.EmailModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody EmailModel email){
        return ResponseEntity.noContent().build();
    }
}
