package com.snoweegamecorp.backend;

import java.time.LocalDateTime;
import java.util.*;
import com.snoweegamecorp.backend.model.PermissionModel;
import com.snoweegamecorp.backend.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.snoweegamecorp.backend.model.UserModel;
import com.snoweegamecorp.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@EntityScan(basePackages = {
			"com.snoweegamecorp.backend.model"
			})
@EnableJpaRepositories(basePackages = {
			"com.snoweegamecorp.backend.repository"
			})
public class BackendApplication {
	@Autowired
	private PermissionRepository permissionRepo;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public Long id = Long.valueOf(1);

	public String profilePic = "https://i.imgur.com/CWf3Y4j.jpg";

	@Bean
	public CommandLineRunner init() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				String encodedPass = passwordEncoder.encode("1234");
				PermissionModel permissionAdmin = new PermissionModel(1L, "ROLE_ADMIN");
				PermissionModel permissionGuest = new PermissionModel(2L, "ROLE_OPERATOR");

				if (permissionRepo.existsById(permissionAdmin.getId()) && permissionRepo.existsById(permissionGuest.getId())){
						System.out.println("Permissions already recorded!");
				} else {
					permissionRepo.save(permissionAdmin);
					permissionRepo.save(permissionGuest);
				}

				Set<PermissionModel> permissions = new HashSet<>(Arrays.asList(permissionAdmin, permissionGuest));
				UserModel user = new UserModel(
						id,
						"Erick",
						"Araujo",
						"batistasd678@gmail.com",
						encodedPass,
						profilePic,
						LocalDateTime.now(),
						null,
						permissions
				);
				userRepository.save(user);
				UserModel user2 = new UserModel(
						id,
						"Erick",
						"Araujo",
						"test@snowee.com",
						encodedPass,
						profilePic,
						LocalDateTime.now(),
						null,
						permissions
				);
				userRepository.save(user2);
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
