package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.dto.ArticleDTO;
import com.snoweegamecorp.backend.model.ArticleModel;
import com.snoweegamecorp.backend.repository.ArticleRepository;
import com.snoweegamecorp.backend.service.ArticleService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping(value = "/articles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@OpenAPIDefinition(info = @Info(title = "API de Gerenciamento Snowee", version = "1.0", description = "Gerenciamento de usuários, temas e artigos"))
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<List<ArticleDTO>> findListAll(){
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        List<ArticleModel> articlesList = articleRepository.findAll();
        for (ArticleModel article: articlesList){
            ArticleDTO articleDTO = new ArticleDTO(article);
            articleDTOS.add(articleDTO);
        }
        return ResponseEntity.ok().body(articleDTOS);
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
    public ResponseEntity<ArticleDTO> findById(@PathVariable Long id){
        ArticleDTO articleDTO = new ArticleDTO(articleService.findById(id));
        return ResponseEntity.ok().body(articleDTO);
    }

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<List<ArticleDTO>> findByTitleLike(@PathVariable String title){
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        List<ArticleModel> articlesList = articleService.findTitleLike(title);
        for (ArticleModel article: articlesList){
            ArticleDTO articleDTO = new ArticleDTO(article);
            articleDTOS.add(articleDTO);
        }
        return ResponseEntity.ok().body(articleDTOS);
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
