package com.snoweegamecorp.backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snoweegamecorp.backend.service.validation.UserInsertValid;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.PackagePrivate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "tb_users")
public class UserModel implements Serializable {
	private static final long serialVerionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name= "increment", strategy = "increment")
	private Long id;
	@Column
	@Size(min = 3, max = 60, message = "FirstName deve ter entre 3 a 60 caracteres")
	@NotBlank(message = "Campo requerido")
	private String firstName;
	@Column
	@Size(min = 3, max = 60, message = "LastName deve ter entre 3 a 60 caracteres")
	@NotBlank(message = "Campo requerido")
	private String lastName;
	@Column(unique = true)
	@Email(message = "Não entrar com email inválido!")
	private String email;
	@Column
	@Size(min = 4, max = 8, message = "Password deve ter entre 4 a 8 caracteres")
	@NotBlank(message = "Campo requerido")
	private String password;
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdAt;
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime updatedAt;
	@ManyToMany
	@JoinTable(name = "tb_user_permissions",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "permission_id"))
	@JsonIgnoreProperties("users")
	private Set<PermissionModel> permissions;
	@PrePersist
	public void beforeSave() {
		setCreatedAt(LocalDateTime.now());
	}
	public UserModel(){
	}
	public UserModel(Long id, String firstName, String lastName, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Set<PermissionModel> permissions) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.permissions = permissions;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Set<PermissionModel> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<PermissionModel> permissions) {
		this.permissions = permissions;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserModel userModel = (UserModel) o;
		return id.equals(userModel.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
