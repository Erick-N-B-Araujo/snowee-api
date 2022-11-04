package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.dto.ThemeDTO;
import com.snoweegamecorp.backend.model.ThemeModel;
import com.snoweegamecorp.backend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<ThemeDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<ThemeDTO> themes = themeService.findAllPaged(pageRequest);

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

    @PutMapping(value = "/{id}")
    public ResponseEntity<ThemeDTO> update(@PathVariable Long id, @RequestBody ThemeDTO dto){
        dto = themeService.update(id, dto);
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        themeService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }


}
