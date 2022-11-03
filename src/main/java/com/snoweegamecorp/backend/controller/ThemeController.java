package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.dto.ThemeDTO;
import com.snoweegamecorp.backend.model.ThemeModel;
import com.snoweegamecorp.backend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/themes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping
    public ResponseEntity<List<ThemeDTO>> findAll(){
        List<ThemeDTO> themes = themeService.findall();
        return ResponseEntity.ok().body(themes);
    }


}
