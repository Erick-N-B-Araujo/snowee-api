package com.snoweegamecorp.backend.dto;

import com.snoweegamecorp.backend.model.ArticleModel;
import com.snoweegamecorp.backend.model.ThemeModel;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArticleDTO implements Serializable {

    private static final long serialVerionUID = 1L;
    private Long id;
    private String title;
    private String articleText;
    private String imgUrl;
    private Instant createdAt;

    private List<ThemeDTO> themes = new ArrayList<>();

    public ArticleDTO(){}

    public ArticleDTO(Long id, String title, String articleText, String imgUrl, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.articleText = articleText;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
    }

    public ArticleDTO(ArticleModel article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.articleText = article.getArticleText();
        this.imgUrl = article.getImgUrl();
        this.createdAt = article.getCreatedAt();
    }

    public ArticleDTO(ArticleModel article, Set<ThemeModel> themesList) {
        this(article);
        themesList.forEach(
                theme -> this.themes.add(new ThemeDTO(theme))
        );
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

    public List<ThemeDTO> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeDTO> themes) {
        this.themes = themes;
    }
}
