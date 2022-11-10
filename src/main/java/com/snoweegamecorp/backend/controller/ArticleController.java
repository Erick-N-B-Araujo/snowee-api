package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.Article;
import com.snoweegamecorp.backend.model.Theme;
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
    public ResponseEntity<List<Article>> findListAll(){
        List<Article> articles= articleRepository.findAll();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<Page<Article>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "title") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<Article> articles = articleService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Article> findById(@PathVariable Long id){
        Article article = articleService.findById(id);
        return ResponseEntity.ok().body(article);
    }

    @PostMapping()
    public ResponseEntity<Article> insert(@RequestBody Article article){
        article = articleService.insert(article);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(article.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(article);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article article){
        article = articleService.update(id, article);
        return ResponseEntity
                .ok()
                .body(article);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        articleService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
