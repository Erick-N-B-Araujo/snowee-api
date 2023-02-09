package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.Theme;
import com.snoweegamecorp.backend.repository.ThemeRepository;
import com.snoweegamecorp.backend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/themes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping
    public ResponseEntity<Page<Theme>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<Theme> themes = themeService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(themes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Theme> findById(@PathVariable Long id){
        Theme theme = themeService.findById(id);
        return ResponseEntity.ok().body(theme);
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<List<Theme>> findListAll(){
        List<Theme> themes= themeRepository.findAll();
        return ResponseEntity.ok().body(themes);
    }

    @PostMapping()
    public ResponseEntity<Theme> insert(@RequestBody Theme theme){
        theme = themeService.insert(theme);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(theme.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(theme);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Theme> update(@PathVariable Long id, @RequestBody Theme theme){
        theme = themeService.update(id, theme);
        return ResponseEntity
                .ok()
                .body(theme);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        themeService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }


}
