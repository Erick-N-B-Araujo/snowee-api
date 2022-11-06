package com.snoweegamecorp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tb_themes")
public class Theme implements Serializable {

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
    Set<Article> articles;

    public Theme(){}

    public Theme(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Theme(Long id, String name, Set<Article> articles) {
        this.id = id;
        this.name = name;
        this.articles = articles;
    }

    public Theme(Theme x) {
        this.id = x.getId();
        this.name = x.getName();
        this.articles = x.getArticles();
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

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
