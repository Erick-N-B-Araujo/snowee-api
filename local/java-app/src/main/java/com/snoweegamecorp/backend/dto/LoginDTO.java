package com.snoweegamecorp.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.snoweegamecorp.backend.model.LoginModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class LoginDTO  implements Serializable {
    private static final long serialVerionUID = 1L;
    private Long id;
    private String firstname;
    private String lastname;
    private String profileImg;
    private String username;
    private String password;
    private String token;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime loggedAt;
    public LoginDTO() {
    }

    public LoginDTO(Long id, String firstname, String lastname, String profileImg, String username, String password, String token, LocalDateTime loggedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profileImg = profileImg;
        this.username = username;
        this.password = password;
        this.token = token;
        this.loggedAt = loggedAt;
    }

    public LoginDTO(Long id, String firstname, String lastname, String profileImg, String username, String token, LocalDateTime loggedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profileImg = profileImg;
        this.username = username;
        this.token = token;
        this.loggedAt = loggedAt;
    }

    public LoginDTO(LoginModel loginModel) {
        id = loginModel.getId();
        firstname = loginModel.getFirstname();
        lastname = loginModel.getLastname();
        profileImg = loginModel.getProfileImg();
        username = loginModel.getUsername();
        token = loginModel.getToken();
        loggedAt = loginModel.getLoggedAt();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return id.equals(loginDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
