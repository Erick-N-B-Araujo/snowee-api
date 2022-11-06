package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
