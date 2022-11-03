package com.snoweegamecorp.backend.dto;

import com.snoweegamecorp.backend.model.ThemeModel;

import java.io.Serializable;

public class ThemeDTO implements Serializable {

    private static final long serialVerionUID = 1L;
    private Long id;
    private String name;

    public ThemeDTO(){
    }

    public ThemeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ThemeDTO(ThemeModel theme){
        this.id = theme.getId();
        this.name = theme.getName();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
