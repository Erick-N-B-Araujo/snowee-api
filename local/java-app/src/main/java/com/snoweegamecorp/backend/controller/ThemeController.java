package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.ThemeModel;
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

@RestController
@RequestMapping(value = "/themes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping
    public ResponseEntity<Page<ThemeModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<ThemeModel> themes = themeService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(themes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ThemeModel> findById(@PathVariable Long id){
        ThemeModel themeModel = themeService.findById(id);
        return ResponseEntity.ok().body(themeModel);
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<List<ThemeModel>> findListAll(){
        List<ThemeModel> themeModels = themeRepository.findAll();
        return ResponseEntity.ok().body(themeModels);
    }

    @PostMapping()
    public ResponseEntity<ThemeModel> insert(@RequestBody ThemeModel themeModel){
        themeModel = themeService.insert(themeModel);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(themeModel.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(themeModel);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ThemeModel> update(@PathVariable Long id, @RequestBody ThemeModel themeModel){
        themeModel = themeService.update(id, themeModel);
        return ResponseEntity
                .ok()
                .body(themeModel);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        themeService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }


}
