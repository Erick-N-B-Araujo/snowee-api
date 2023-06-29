package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.model.ArticleModel;
import com.snoweegamecorp.backend.model.ThemeModel;
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
    public Page<ArticleModel> findAllPaged(PageRequest pageRequest){
        Page<ArticleModel> listPaged = articleRepository.findAll(pageRequest);
        return listPaged.map(x -> new ArticleModel(x));
    }

    @Transactional(readOnly = true)
    public ArticleModel findById(Long id) {
        Optional<ArticleModel> objArticle = articleRepository.findById(id);
        return  objArticle
                .orElseThrow(
                        () -> new ResourceNotFoundException("Entity not found")
                );
    }

    @Transactional
    public ArticleModel insert(ArticleModel articleToInsert) {
        return articleRepository.save(articleToInsert);
    }

    @Transactional
    public ArticleModel update(Long id, ArticleModel updatedArticle) {
        try {
            ArticleModel article = articleRepository.getOne(id);
            article.setTitle(updatedArticle.getTitle());
            article.setSubTitle(updatedArticle.getSubTitle());
            article.setDescription(updatedArticle.getDescription());
            article.setInstructionList(updatedArticle.getInstructionList());
            article.setCodeList(updatedArticle.getCodeList());
            article.setImgUrl(updatedArticle.getImgUrl());
            article.setUser(updatedArticle.getUser());
            Set<ThemeModel> themeModelList = new HashSet<>();
            for (ThemeModel themeModel : updatedArticle.getThemes()){
                themeModel = themeRepository.getOne(themeModel.getId());
                themeModelList.add(themeModel);
            }
            article.setThemes(themeModelList);
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
