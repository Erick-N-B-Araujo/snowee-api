package com.snoweegamecorp.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
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

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String subTitle;

    @Column(columnDefinition = "TEXT")
    private String description;
    @Column()
    @ElementCollection(targetClass = String.class)
    private List<String> instructionList;
    @Column()
    @ElementCollection(targetClass = String.class)
    private List<String> codeList;

    @Column(columnDefinition = "TEXT")
    private String ending;

    @Column(columnDefinition = "TEXT")
    private String imgUrl;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JsonIgnoreProperties("articles")
    private UserModel user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_article_themes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    @JsonIgnoreProperties("articles")
    Set<ThemeModel> themes;

    @PrePersist
    public void beforeSave() {
        if (getCreatedAt() == null){
            setCreatedAt(LocalDateTime.now());
        }
        setUpdatedAt(LocalDateTime.now());
    }

    public ArticleModel(){}

    public ArticleModel(Long id, String title, String subTitle, String description, List<String> instructionList, List<String> codeList, String ending, String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, UserModel user, Set<ThemeModel> themes) {
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

    public ArticleModel(ArticleModel articleModel) {
        this.id = articleModel.getId();
        this.title = articleModel.getTitle();
        this.subTitle = articleModel.getSubTitle();
        this.description = articleModel.getDescription();
        this.instructionList = articleModel.getInstructionList();
        this.codeList = articleModel.getCodeList();
        this.ending = articleModel.getEnding();
        this.imgUrl = articleModel.getImgUrl();
        this.createdAt = articleModel.getCreatedAt();
        this.updatedAt = articleModel.getUpdatedAt();
        this.user = articleModel.getUser();
        this.themes = articleModel.getThemes();
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

    public Set<ThemeModel> getThemes() {
        return themes;
    }

    public void setThemes(Set<ThemeModel> themeModels) {
        this.themes = themeModels;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public List<String> getInstructionList() {
        return instructionList;
    }

    public void setInstructionList(List<String> instructionList) {
        this.instructionList = instructionList;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }
}
