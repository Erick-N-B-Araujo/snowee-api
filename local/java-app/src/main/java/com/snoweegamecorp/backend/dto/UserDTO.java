package com.snoweegamecorp.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snoweegamecorp.backend.model.PermissionModel;
import com.snoweegamecorp.backend.model.UserModel;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class UserDTO implements Serializable {
    private static final long serialVerionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String token;
    private LocalDateTime loggedAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @JsonIgnoreProperties("users")
    private Set<PermissionModel> permissions = new HashSet<>();

    public UserDTO(){}

    public UserDTO(Long id, String firstName, String lastName, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt, String token, LocalDateTime loggedAt, Set<PermissionModel> permissions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.token = token;
        this.loggedAt = loggedAt;
        this.permissions = permissions;
    }

    public UserDTO(UserModel userModel) {
        this.id = userModel.getId();
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
        this.createdAt = userModel.getCreatedAt();
        this.updatedAt = userModel.getUpdatedAt();
        //this.token = userModel.getToken();
        //this.loggedAt = userModel.getLoggedAt();
        this.permissions = userModel.getPermissions();
    }

    public UserDTO(Long id, String email, String token, LocalDateTime loggedAt) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.loggedAt = LocalDateTime.now();
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
