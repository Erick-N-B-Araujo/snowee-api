package com.snoweegamecorp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tb_articles")
public class Article implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy = "increment")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String articleText;

    private String imgUrl;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @ManyToOne
    @JsonIgnoreProperties("articles")
    private LoginModel user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_article_themes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    @JsonIgnoreProperties("articles")
    Set<Theme> themes;

    @PrePersist
    public void beforeSave() {
        setCreatedAt(Instant.now());
    }

    public Article(){}

    public Article(Long id, String title, String articleText, String imgUrl, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.articleText = articleText;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
    }

    public Article(Long id, String title, String articleText, String imgUrl, Instant createdAt, LoginModel user, Set<Theme> themes) {
        this.id = id;
        this.title = title;
        this.articleText = articleText;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.user = user;
        this.themes = themes;
    }

    public Article(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.articleText = article.getArticleText();
        this.imgUrl = article.getImgUrl();
        this.createdAt = article.getCreatedAt();
        this.user = article.getUser();
        this.themes = article.getThemes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Theme> getThemes() {
        return themes;
    }

    public void setThemes(Set<Theme> themes) {
        this.themes = themes;
    }

    public LoginModel getUser() {
        return user;
    }

    public void setUser(LoginModel user) {
        this.user = user;
    }
}
