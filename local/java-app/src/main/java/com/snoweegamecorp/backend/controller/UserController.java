package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.dto.UserDTO;
import com.snoweegamecorp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.UserRepository;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	private UserRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@PostMapping
	public ResponseEntity save(@Valid @RequestBody UserModel user)
	{
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		user.setCreatedAt(LocalDateTime.now());
		return new ResponseEntity(
				new UserDTO().makeDTO(repository.save(user)), HttpStatus.CREATED
		);
	}
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity
				.ok()
				.body(
						new UserDTO()
								.getAllUsers(repository.findAll())
				);
	}
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAllUsersPermissions(){
		return ResponseEntity
				.ok()
				.body(
						new UserDTO()
								.getAllUsersData(repository.findAll())
				);
	}
	@GetMapping("{id}")
	public ResponseEntity getById(@PathVariable Long id) {
		return ResponseEntity
				.ok()
				.body(
						new UserDTO()
								.makeDTO(
										repository
												.findById(id)
												.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND))
								)
				);
	}
	@GetMapping("{id}/all")
	public ResponseEntity getByIdData(@PathVariable Long id) {
		return ResponseEntity
				.ok()
				.body(
						new UserDTO()
								.makeDTOAll(
										repository
												.findById(id)
												.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND))
								)
				);
	}
	@GetMapping("/find/{email}")
	public ResponseEntity getByUsername(@PathVariable String email) {
		UserDTO foundedUser = new UserDTO()
									.makeDTO(
											repository
													.findByEmail(email)
									);
		return ResponseEntity
				.ok()
				.body(
						foundedUser
				);
	}
	@PutMapping("{id}")
	public ResponseEntity updateUserBy(@PathVariable Long id, @Valid @RequestBody UserModel userModel){
		UserModel user = new UserModel(
				repository.findById(id)
						.orElseThrow(()->
								new ResponseStatusException(HttpStatus.NOT_FOUND)
						)
		);
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setProfileImgUrl(userModel.getProfileImgUrl());
		user.setUpdatedAt(LocalDateTime.now());
		repository.save(user);
		return new ResponseEntity(
				new UserDTO().makeDTO(user), HttpStatus.ACCEPTED
		);
	}
	@DeleteMapping("{id}")
	public ResponseEntity deleteUserById(@PathVariable Long id){
		repository
				.deleteById(id);

		return ResponseEntity
				.noContent()
				.build();
	}
	@PatchMapping("{id}/admin")
	public UserModel definePermission(@PathVariable Long id){
		return repository
				.findById(id)
				.map(user -> {
					user.setUpdatedAt(LocalDateTime.now());
					repository.save(user);
					return user;
				})
				.orElse(null);
	}
	@PatchMapping("{id}")
	public ResponseEntity patchUserById(@PathVariable Long id, @Valid @RequestBody UserModel userModel){
		UserModel user = new UserModel(
				repository.findById(id)
						.orElseThrow(()->
								new ResponseStatusException(HttpStatus.NOT_FOUND)
						)
		);
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setProfileImgUrl(userModel.getProfileImgUrl());
		user.setUpdatedAt(LocalDateTime.now());
		repository.save(user);
		return new ResponseEntity(
				new UserDTO().makeDTO(user), HttpStatus.ACCEPTED
		);
	}
}
