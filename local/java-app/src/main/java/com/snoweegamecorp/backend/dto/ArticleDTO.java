package com.snoweegamecorp.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snoweegamecorp.backend.model.ArticleModel;
import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.ThemeModel;
import com.snoweegamecorp.backend.model.UserModel;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ArticleDTO implements Serializable {
    private static final long serialVerionUID = 1L;
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String[] instructionList;
    private String[] codeList;
    private String ending;
    private String imgUrl;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime updatedAt;
    @JsonIgnoreProperties({
            "articles",
            "password",
            "createdAt",
            "updatedAt",
            "permissions",
            "enabled",
            "username",
            "accountNonLocked",
            "accountNonExpired",
            "credentialsNonExpired",
            "authorities"
    })
    private UserModel user;
    @JsonIgnoreProperties("articles")
    Set<ThemeModel> themes;
    public ArticleDTO() {
    }
    public ArticleDTO(Long id, String title, String subTitle, String description, String[] instructionList, String[] codeList, String ending, String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, UserModel user, Set<ThemeModel> themes) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.instructionList = instructionList;
        this.codeList = codeList;
        this.ending = ending;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.themes = themes;
    }
    public ArticleDTO(ArticleModel model){
        id = model.getId();
        title = model.getTitle();
        subTitle = model.getSubTitle();
        description = model.getDescription();
        instructionList = model.getInstructionList();
        codeList = model.getCodeList();
        ending = model.getEnding();
        imgUrl = model.getImgUrl();
        createdAt = model.getCreatedAt();
        updatedAt = model.getUpdatedAt();
        user = model.getUser();
        /*user.setId(model.getId());
        user.setFirstName(model.getUser().getFirstName());
        user.setLastName(model.getUser().getLastName());
        user.setEmail(model.getUser().getEmail());
        user.setProfileImgUrl(model.getUser().getProfileImgUrl());*/
        themes = model.getThemes();
    }

    @Transactional
    public List<ArticleDTO> getAllArticles(List<ArticleModel> articleList){
        List<ArticleDTO> articlesDTO = new ArrayList<>();
        for(ArticleModel article : articleList){
            ArticleDTO articleDTO = new ArticleDTO(article);
            articlesDTO.add(articleDTO);
        }
        return articlesDTO;
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
    public String getSubTitle() {
        return subTitle;
    }
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String[] getInstructionList() {
        return instructionList;
    }
    public void setInstructionList(String[] instructionList) {
        this.instructionList = instructionList;
    }
    public String[] getCodeList() {
        return codeList;
    }
    public void setCodeList(String[] codeList) {
        this.codeList = codeList;
    }
    public String getEnding() {
        return ending;
    }
    public void setEnding(String ending) {
        this.ending = ending;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
    public Set<ThemeModel> getThemes() {
        return themes;
    }
    public void setThemes(Set<ThemeModel> themes) {
        this.themes = themes;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDTO that = (ArticleDTO) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
