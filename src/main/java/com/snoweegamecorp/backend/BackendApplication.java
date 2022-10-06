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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Long id = Long.valueOf(1);
	@Bean
	public CommandLineRunner init() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				PermissionModel permissionAdmin = new PermissionModel();
				permissionAdmin.setPermissionName("admin");
				permissionRepo.save(permissionAdmin);
				PermissionModel permissionGuest = new PermissionModel();
				permissionGuest.setPermissionName("guest");
				permissionRepo.save(permissionGuest);
				Set<PermissionModel> permissions = new HashSet<>(Arrays.asList(permissionAdmin, permissionGuest));
				UserModel user = new UserModel(
						id,
						"Erick",
						"Erick",
						"batistasd678@gmail.com",
						"1234",
						LocalDateTime.now(),
						null,
						permissions
				);
				userRepository.save(user);
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
