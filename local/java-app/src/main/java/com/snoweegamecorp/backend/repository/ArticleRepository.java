package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleModel, Long> {
}
