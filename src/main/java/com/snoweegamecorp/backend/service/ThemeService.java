package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.dto.ThemeDTO;
import com.snoweegamecorp.backend.model.ThemeModel;
import com.snoweegamecorp.backend.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

}
