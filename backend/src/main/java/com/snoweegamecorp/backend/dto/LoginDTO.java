package com.snoweegamecorp.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.UserModel;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginDTO  implements Serializable {
    private static final long serialVerionUID = 1L;
    private Long id;
    private String firstname;
    private String lastname;
    private String profileImg;
    private String username;
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime loggedAt;
    public LoginDTO() {
    }

    public LoginDTO(Long id, String firstname, String lastname, String profileImg, String username, String password, LocalDateTime loggedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profileImg = profileImg;
        this.username = username;
        this.password = password;
        this.loggedAt = loggedAt;
    }

    public LoginDTO(Long id, String firstname, String lastname, String profileImg, String username, LocalDateTime loggedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profileImg = profileImg;
        this.username = username;
        this.loggedAt = loggedAt;
    }

    public LoginDTO(LoginModel loginModel) {
        id = loginModel.getId();
        firstname = loginModel.getFirstname();
        lastname = loginModel.getLastname();
        profileImg = loginModel.getProfileImg();
        username = loginModel.getUsername();
        loggedAt = loginModel.getLoggedAt();
    }
    @Transactional
    public List<LoginDTO> getAllLoggedUsers(List<LoginModel> loginList){
        List<LoginDTO> logginsDTO = new ArrayList<>();
        for(LoginModel login : loginList){
            LoginDTO logginDTO = new LoginDTO(login);
            logginsDTO.add(logginDTO);
        }
        return logginsDTO;
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
