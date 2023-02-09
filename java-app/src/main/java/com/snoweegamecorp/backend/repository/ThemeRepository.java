package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
