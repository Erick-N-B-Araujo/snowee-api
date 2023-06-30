package com.snoweegamecorp.backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Table(name = "tb_users")
public class UserModel implements UserDetails, Serializable {
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
	@NotBlank(message = "Campo requerido")
	private String email;
	@Column
	private String password;
	@Column
	private String profileImgUrl;
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdAt;
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_permissions",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "permission_id"))
	@JsonIgnoreProperties("users")
	private Set<PermissionModel> permissions = new HashSet<>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"user"})
	private List<ArticleModel> articles;
	@PrePersist
	public void beforeSave() {
		/*if (!getEmail().equals("batistasd678@gmail.com")){
			PermissionModel permission = new PermissionModel( 2L, "ROLE_OPERATOR");
			Set<PermissionModel> permissions = new HashSet<>(Arrays.asList(permission));
			setPermissions(permissions);
		}*/
	}
	@PostPersist
	public void afterSave(){
	}
	public UserModel(){
	}
	public UserModel(Long id, String firstName, String lastName, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Set<PermissionModel> permissions) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.permissions = permissions;
	}
	public UserModel(Long id, String firstName, String lastName, String email, String password, String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, Set<PermissionModel> permissions) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.profileImgUrl = profileImgUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.permissions = permissions;
	}
	public UserModel(Long id, String firstName, String lastName, String email, String password, String profileImgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, Set<PermissionModel> permissions, List<ArticleModel> articles) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.permissions = permissions;
		this.articles = articles;
		this.profileImgUrl = profileImgUrl;
	}
	public UserModel(UserModel userModel) {
		this.id = userModel.getId();
		this.firstName = userModel.getFirstName();
		this.lastName = userModel.getLastName();
		this.email = userModel.getEmail();
		this.password = userModel.getPassword();
		this.profileImgUrl = userModel.getProfileImgUrl();
		this.createdAt = userModel.getCreatedAt();
		this.updatedAt = userModel.getUpdatedAt();
		this.permissions = userModel.getPermissions();
		this.articles = userModel.getArticles();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissions
				.stream()
				.map(
						permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
				.collect(Collectors.toList());
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setEmail(String email) { this.email = email; }
	public List<ArticleModel> getArticles() { return articles; }
	public void setArticles(List<ArticleModel> articles) {
		this.articles = articles;
	}
	public String getProfileImgUrl() { return profileImgUrl; }
	public void setProfileImgUrl(String profileImgUrl) { this.profileImgUrl = profileImgUrl; }
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
