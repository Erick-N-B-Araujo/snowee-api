package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.ArticleModel;
import com.snoweegamecorp.backend.repository.ArticleRepository;
import com.snoweegamecorp.backend.service.ArticleService;
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
@RequestMapping(value = "/articles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public ResponseEntity<List<ArticleModel>> findListAll(){
        List<ArticleModel> articleModels = articleRepository.findAll();
        return ResponseEntity.ok().body(articleModels);
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<Page<ArticleModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "title") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<ArticleModel> articles = articleService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArticleModel> findById(@PathVariable Long id){
        ArticleModel articleModel = articleService.findById(id);
        return ResponseEntity.ok().body(articleModel);
    }

    @PostMapping()
    public ResponseEntity<ArticleModel> insert(@RequestBody ArticleModel articleModel){
        articleModel = articleService.insert(articleModel);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(articleModel.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(articleModel);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ArticleModel> update(@PathVariable Long id, @RequestBody ArticleModel articleModel){
        articleModel = articleService.update(id, articleModel);
        return ResponseEntity
                .ok()
                .body(articleModel);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        articleService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
