package com.snoweegamecorp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_article_themes",
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    @JsonIgnoreProperties("themes")
    Set<ArticleModel> articleModels;

    public ThemeModel(){}

    public ThemeModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ThemeModel(Long id, String name, Set<ArticleModel> articleModels) {
        this.id = id;
        this.name = name;
        this.articleModels = articleModels;
    }

    public ThemeModel(ThemeModel x) {
        this.id = x.getId();
        this.name = x.getName();
        this.articleModels = x.getArticles();
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

    public Set<ArticleModel> getArticles() {
        return articleModels;
    }

    public void setArticles(Set<ArticleModel> articleModels) {
        this.articleModels = articleModels;
    }
}
