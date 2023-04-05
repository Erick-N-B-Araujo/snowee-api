package com.snoweegamecorp.backend.service;

import com.snoweegamecorp.backend.model.Theme;
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
    public Page<Theme> findAllPaged(PageRequest pageRequest){
        Page<Theme> listPaged = themeRepository.findAll(pageRequest);
        return listPaged.map(x -> new Theme(x));
    }

    @Transactional(readOnly = true)
    public Theme findById(Long id) {
        Optional<Theme> objTheme = themeRepository.findById(id);
        return objTheme
                .orElseThrow(
                        () -> new ResourceNotFoundException("Entity not found")
                );
    }

    @Transactional
    public Theme insert(Theme theme) {
        return themeRepository.save(theme);
    }

    @Transactional
    public Theme update(Long id, Theme theme) {
        try {
            Theme themeFound = themeRepository.getOne(id);
            themeFound.setName(theme.getName());
            return themeRepository.save(themeFound);
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
