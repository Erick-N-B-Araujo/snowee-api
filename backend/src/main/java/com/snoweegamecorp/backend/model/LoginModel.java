package com.snoweegamecorp.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "tb_login")
public class LoginModel implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy = "increment")
    private long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String profileImg;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime loggedAt;



    public LoginModel(long id, String firstname, String lastname, String username,String profileImg, LocalDateTime loggedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.profileImg = profileImg;
        this.loggedAt = loggedAt;
    }
    public LoginModel(){
    }
    public LoginModel(LoginModel user){
        id = user.getId();
        firstname = user.getFirstname();
        lastname = user.getLastname();
        username = user.getUsername();
        profileImg = user.getProfileImg();
        loggedAt = user.getLoggedAt();
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
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

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginModel)) return false;
        LoginModel that = (LoginModel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
