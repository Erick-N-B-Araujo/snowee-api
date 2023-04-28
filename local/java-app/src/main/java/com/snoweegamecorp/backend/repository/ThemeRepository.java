package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.ThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {
}
