package com.snoweegamecorp.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snoweegamecorp.backend.model.PermissionModel;
import com.snoweegamecorp.backend.model.UserModel;
import lombok.Getter;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
public class PermissionDTO implements Serializable {
    private static final long serialVerionUID = 1L;
    private Long id;
    private String permissionName;
    @JsonIgnoreProperties({"permissions","firstName","lastName","password","createdAt","updatedAt", "username", "accountNonExpired", "credentialsNonExpired", "accountNonLocked", "authorities"})
    private Set<UserModel> users;
    public PermissionDTO(){
    }
    public PermissionDTO(Long id, String permissionName){
        this.id = id;
        this.permissionName = permissionName;
    }
    public PermissionDTO(PermissionModel permissionModel){
        id = permissionModel.getId();
        permissionName = permissionModel.getPermissionName();
        users = permissionModel.getUsers();
    }
    @Transactional
    public List<PermissionDTO> getAllPermission(List<PermissionModel> permissionList){
        List<PermissionDTO> permissionsDTO = new ArrayList<>();
        List<String> permDTO = new ArrayList<>();
        for(PermissionModel permission : permissionList){
            PermissionDTO perm = new PermissionDTO(permission.getId(), permission.getPermissionName());
            permissionsDTO.add(perm);
        }
        return permissionsDTO;
    }
    @Transactional
    public List<PermissionDTO> getAllPermissionUsers(List<PermissionModel> permissionList){
        List<PermissionDTO> permissionsDTO = new ArrayList<>();
        for(PermissionModel permission : permissionList){
            PermissionDTO perm = new PermissionDTO(permission);
            permissionsDTO.add(perm);
        }
        return permissionsDTO;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionDTO that = (PermissionDTO) o;
        return id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
