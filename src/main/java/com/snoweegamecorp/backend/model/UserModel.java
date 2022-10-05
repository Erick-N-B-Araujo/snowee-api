package com.snoweegamecorp.backend.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.PackagePrivate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "tb_users")
public class UserModel {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name= "increment", strategy = "increment")
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private boolean admin;
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdAt;
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime updatedAt;

	@ManyToOne
	@JsonIgnoreProperties("users")
	private PermissionModel permission;
	
	@PrePersist
	public void beforeSave() {
		setCreatedAt(LocalDateTime.now());
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
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
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
	public PermissionModel getPermission() {
		return permission;
	}
	public void setPermission(PermissionModel permission) {
		this.permission = permission;
	}
}
