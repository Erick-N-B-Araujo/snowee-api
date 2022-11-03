package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.dto.ThemeDTO;
import com.snoweegamecorp.backend.model.ThemeModel;
import com.snoweegamecorp.backend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ThemeDTO> findById(@PathVariable Long id){
        ThemeDTO theme = themeService.findById(id);
        return ResponseEntity.ok().body(theme);
    }

    @PostMapping()
    public ResponseEntity<ThemeDTO> insert(@RequestBody ThemeDTO dto){
        dto = themeService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(dto);
    }
}
