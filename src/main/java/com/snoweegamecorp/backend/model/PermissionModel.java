package com.snoweegamecorp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "tb_permissions")
public class PermissionModel implements Serializable {
    private static final long serialVerionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy = "increment")
    private Long id;

    @Column
    private String permissionName;

    @OneToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("permission")
    private List<UserModel> users;

    public PermissionModel(){
    }
    public PermissionModel(Long id, String permissionName, List<UserModel> users) {
        super();
        this.id = id;
        this.permissionName = permissionName;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionModel that = (PermissionModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
