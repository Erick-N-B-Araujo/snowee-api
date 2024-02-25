package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.ThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {
    List<ThemeModel> findByNameContainingIgnoreCase(String name);
}
