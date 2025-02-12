package com.snoweegamecorp.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.snoweegamecorp.backend.model.ArticleModel;
import com.snoweegamecorp.backend.model.UserModel;
import lombok.Getter;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
public class UserDTO implements Serializable {
    private static final long serialVerionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profileImgUrl;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime updatedAt;
    private Set<PermissionDTO> permissions = new HashSet<>();
    private List<ArticleModel> articles;
    public UserDTO(){}
    public UserDTO(Long id, String firstName, String lastName, String email, String password, String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, Set<PermissionDTO> permissions, List<ArticleModel> articles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profileImgUrl = profileImgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.permissions = permissions;
        this.articles = articles;
    }
    public UserDTO(UserModel userModel) {
        this.id = userModel.getId();
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
        this.profileImgUrl = userModel.getProfileImgUrl();
        this.createdAt = userModel.getCreatedAt();
        this.updatedAt = userModel.getUpdatedAt();
        userModel.getPermissions()
                .forEach(permission -> {
                            this.permissions.add(new PermissionDTO(permission.getId(), permission.getPermissionName()));
                });
        this.articles = userModel.getArticles();
    }
    public UserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }
    public UserDTO(Long id, String firstName, String lastName, String email, String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    @Transactional
    public List<UserDTO> getAllUsers(List<UserModel> userList){
        List<UserDTO> usersDTO = new ArrayList<>();
        for(UserModel user : userList){
            UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getProfileImgUrl(), user.getCreatedAt(), user.getUpdatedAt());
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }
    @Transactional
    public List<UserDTO> getAllUsersData(List<UserModel> userList){
        List<UserDTO> usersDTO = new ArrayList<>();
        for(UserModel user : userList){
            UserDTO userDTO = new UserDTO(user);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    @Transactional
    public UserDTO makeDTO(UserModel user){
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getProfileImgUrl(), user.getCreatedAt(), user.getUpdatedAt());
        return userDTO;
    }

    @Transactional
    public UserDTO makeDTOAll(UserModel user){
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
