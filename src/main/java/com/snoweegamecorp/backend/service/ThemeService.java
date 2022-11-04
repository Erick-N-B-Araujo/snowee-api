package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.dto.ThemeDTO;
import com.snoweegamecorp.backend.model.ThemeModel;
import com.snoweegamecorp.backend.repository.ThemeRepository;
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
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Transactional(readOnly = true)
    public Page<ThemeDTO> findAllPaged(PageRequest pageRequest){
        Page<ThemeModel> listPaged = themeRepository.findAll(pageRequest);
        return listPaged.map(x -> new ThemeDTO(x));
    }

    @Transactional(readOnly = true)
    public ThemeDTO findById(Long id) {
        Optional<ThemeModel> objTheme = themeRepository.findById(id);
        ThemeModel theme = objTheme
                .orElseThrow(
                        () -> new ResourceNotFoundException("Entity not found")
                );
        return  new ThemeDTO(theme);
    }

    @Transactional
    public ThemeDTO insert(ThemeDTO dto) {
        ThemeModel theme = new ThemeModel();
        theme.setName(dto.getName());
        theme = themeRepository.save(theme);
        return new ThemeDTO(theme);
    }

    @Transactional
    public ThemeDTO update(Long id, ThemeDTO dto) {
        try {
            ThemeModel theme = themeRepository.getOne(id);
            theme.setName(dto.getName());
            theme = themeRepository.save(theme);
            return new ThemeDTO(theme);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException d){
            throw new DatabaseException("Integrity violation: "+ d);
        }
    }

    public void delete(Long id){
        try {
            themeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException d){
            throw new DatabaseException("Integrity violation: "+ d);
        }
    }
}
