package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleModel, Long> {
    List<ArticleModel> findByTitleLike(String title);
    List<ArticleModel> findByTitleContainingIgnoreCase(String title);
    List<ArticleModel> findByTitleContains(String title);
    List<ArticleModel> findByTitleIsContaining(String title);
}
