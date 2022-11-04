package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.dto.ArticleDTO;
import com.snoweegamecorp.backend.model.ArticleModel;
import com.snoweegamecorp.backend.repository.ArticleRepository;
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
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDTO> findAllPaged(PageRequest pageRequest){
        Page<ArticleModel> listPaged = articleRepository.findAll(pageRequest);
        return listPaged.map(x -> new ArticleDTO(x));
    }

    @Transactional(readOnly = true)
    public ArticleDTO findById(Long id) {
        Optional<ArticleModel> objTheme = articleRepository.findById(id);
        ArticleModel theme = objTheme
                .orElseThrow(
                        () -> new ResourceNotFoundException("Entity not found")
                );
        return  new ArticleDTO(theme);
    }

    @Transactional
    public ArticleDTO insert(ArticleDTO dto) {
        ArticleModel article = new ArticleModel();
        article.setTitle(dto.getTitle());
        article.setArticleText(dto.getArticleText());
        article.setImgUrl(dto.getImgUrl());
        article.setCreatedAt(dto.getCreatedAt());
        article = articleRepository.save(article);
        return new ArticleDTO(article);
    }

    @Transactional
    public ArticleDTO update(Long id, ArticleDTO dto) {
        try {
            ArticleModel article = articleRepository.getOne(id);
            article.setTitle(dto.getTitle());
            article.setArticleText(dto.getArticleText());
            article.setImgUrl(dto.getImgUrl());
            article.setCreatedAt(dto.getCreatedAt());
            article = articleRepository.save(article);
            return new ArticleDTO(article);
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
