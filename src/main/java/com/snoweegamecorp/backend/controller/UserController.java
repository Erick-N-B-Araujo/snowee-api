package com.snoweegamecorp.backend.controller;

import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.actions.user.UserInsert;
import com.snoweegamecorp.backend.repository.LoginRepository;
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
import java.util.Optional;

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
	public UserModel save(@Valid @RequestBody UserModel user)
	{
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		return repository.save(user);
	}
	@GetMapping()
	public List<UserModel> getAll(){
		return repository
				.findAll();
	}
	@GetMapping("{id}")
	public UserModel getById(@PathVariable Long id) {
		return repository
				.findById(id)
				.orElseThrow(()-> new ResponseStatusException(
						HttpStatus.NOT_FOUND
				));
	}
	@PutMapping("{id}")
	public UserModel updateUserBy(@PathVariable Long id, @Valid @RequestBody UserModel user){
		user.setUpdatedAt(LocalDateTime.now());
		user.setCreatedAt(
				repository.findById(id)
						.get().getCreatedAt()
		);
		ResponseEntity.ok(user);
		return repository.save(user);
	}
	@DeleteMapping("{id}")
	public void deleteUserById(@PathVariable Long id){
		repository
				.deleteById(id);
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
}
