package com.snoweegamecorp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@PostMapping
	public UserModel save(@RequestBody UserModel user)
	{
		return repository
				.save(user);
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
					//user.setAdmin(true);
					user.setUpdatedAt(LocalDateTime.now());
					repository.save(user);
					return user;
				})
				.orElse(null);
	}
}
