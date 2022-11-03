package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.ThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {
}
