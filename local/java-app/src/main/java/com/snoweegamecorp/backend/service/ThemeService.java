package com.snoweegamecorp.backend.service;

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
    public Page<ThemeModel> findAllPaged(PageRequest pageRequest){
        Page<ThemeModel> listPaged = themeRepository.findAll(pageRequest);
        return listPaged.map(x -> new ThemeModel(x));
    }

    @Transactional(readOnly = true)
    public ThemeModel findById(Long id) {
        Optional<ThemeModel> objTheme = themeRepository.findById(id);
        return objTheme
                .orElseThrow(
                        () -> new ResourceNotFoundException("Entity not found")
                );
    }

    @Transactional
    public ThemeModel insert(ThemeModel themeModel) {
        return themeRepository.save(themeModel);
    }

    @Transactional
    public ThemeModel update(Long id, ThemeModel themeModel) {
        try {
            ThemeModel themeModelFound = themeRepository.getOne(id);
            themeModelFound.setName(themeModel.getName());
            return themeRepository.save(themeModelFound);
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
