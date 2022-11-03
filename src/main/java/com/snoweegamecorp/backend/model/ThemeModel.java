package com.snoweegamecorp.backend.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "tb_themes")
public class ThemeModel implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy = "increment")
    private Long id;

    @Column
    @NotBlank(message = "Campo nome do TEMA é requerido")
    private String name;

    public ThemeModel(){
    }

    public ThemeModel(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThemeModel themeModel = (ThemeModel) o;
        return id.equals(themeModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
