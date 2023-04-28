package com.snoweegamecorp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tb_articles")
public class ArticleModel implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy = "increment")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String descriptionText;

    @Column(columnDefinition = "TEXT")
    private String articleText;

    private String imgUrl;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @ManyToOne
    @JsonIgnoreProperties("articles")
    private UserModel user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_article_themes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    @JsonIgnoreProperties("articles")
    Set<ThemeModel> themeModels;

    @PrePersist
    public void beforeSave() {
        if (getCreatedAt() == null){
            setCreatedAt(Instant.now());
        }
        setUpdatedAt(Instant.now());
    }

    public ArticleModel(){}

    public ArticleModel(Long id, String title, String descriptionText, String articleText, String imgUrl, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.descriptionText = descriptionText;
        this.articleText = articleText;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ArticleModel(Long id, String title, String descriptionText, String articleText, String imgUrl, Instant createdAt, Instant updatedAt, UserModel user, Set<ThemeModel> themeModels) {
        this.id = id;
        this.title = title;
        this.descriptionText = descriptionText;
        this.articleText = articleText;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.themeModels = themeModels;
    }

    public ArticleModel(ArticleModel articleModel) {
        this.id = articleModel.getId();
        this.title = articleModel.getTitle();
        this.descriptionText = articleModel.getDescriptionText();
        this.articleText = articleModel.getArticleText();
        this.imgUrl = articleModel.getImgUrl();
        this.createdAt = articleModel.getCreatedAt();
        this.updatedAt = articleModel.getUpdatedAt();
        this.user = articleModel.getUser();
        this.themeModels = articleModel.getThemes();
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

    public Set<ThemeModel> getThemes() {
        return themeModels;
    }

    public void setThemes(Set<ThemeModel> themeModels) {
        this.themeModels = themeModels;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }
}
