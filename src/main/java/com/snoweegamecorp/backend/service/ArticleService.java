package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.model.Article;
import com.snoweegamecorp.backend.model.Theme;
import com.snoweegamecorp.backend.repository.ArticleRepository;
import com.snoweegamecorp.backend.repository.ThemeRepository;
import com.snoweegamecorp.backend.resources.exceptions.DatabaseException;
import com.snoweegamecorp.backend.resources.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Transactional(readOnly = true)
    public Page<Article> findAllPaged(PageRequest pageRequest){
        Page<Article> listPaged = articleRepository.findAll(pageRequest);
        return listPaged.map(x -> new Article(x));
    }

    @Transactional(readOnly = true)
    public Article findById(Long id) {
        Optional<Article> objArticle = articleRepository.findById(id);
        return  objArticle
                .orElseThrow(
                        () -> new ResourceNotFoundException("Entity not found")
                );
    }

    @Transactional
    public Article insert(Article articleReq) {
        Article article = new Article();
        article.setTitle(article.getTitle());
        article.setArticleText(articleReq.getArticleText());
        article.setCreatedAt(articleReq.getCreatedAt());
        article.setImgUrl(articleReq.getImgUrl());
        Set<Theme> themeList = new HashSet<>();
        for (Theme theme: articleReq.getThemes()){
            theme = themeRepository.getOne(theme.getId());
            themeList.add(theme);
        }
        article.setThemes(themeList);
        return articleRepository.save(articleReq);
    }

    @Transactional
    public Article update(Long id, Article articleReq) {
        try {
            Article article = articleRepository.getOne(id);
            article.setTitle(articleReq.getTitle());
            article.setArticleText(articleReq.getArticleText());
            article.setCreatedAt(articleReq.getCreatedAt());
            article.setImgUrl(articleReq.getImgUrl());
            Set<Theme> themeList = new HashSet<>();
            for (Theme theme: articleReq.getThemes()){
                theme = themeRepository.getOne(theme.getId());
                themeList.add(theme);
            }
            article.setThemes(themeList);
            return articleRepository.save(article);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException d){
            throw new DatabaseException("Integrity violation: "+ d);
        }
    }

    public void delete(Long id){
        try {
            articleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException d){
            throw new DatabaseException("Integrity violation: "+ d);
        }
    }
}
