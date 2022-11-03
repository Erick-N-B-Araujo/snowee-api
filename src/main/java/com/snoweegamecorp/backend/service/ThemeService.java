package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.dto.ThemeDTO;
import com.snoweegamecorp.backend.model.ThemeModel;
import com.snoweegamecorp.backend.repository.ThemeRepository;
import com.snoweegamecorp.backend.resources.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Transactional(readOnly = true)
    public List<ThemeDTO> findall(){
        List<ThemeModel> list = themeRepository.findAll();

        return list
                .stream()
                .map(x -> new ThemeDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ThemeDTO findById(Long id) {
        Optional<ThemeModel> objTheme = themeRepository.findById(id);
        ThemeModel theme = objTheme
                .orElseThrow(
                        () -> new EntityNotFoundException("Entity not found")
                );
        return  new ThemeDTO(theme);
    }

    @Transactional(readOnly = true)
    public ThemeDTO insert(ThemeDTO dto) {
        ThemeModel theme = new ThemeModel();
        theme.setName(dto.getName());
        theme = themeRepository.save(theme);
        return new ThemeDTO(theme);
    }
}
