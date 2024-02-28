package com.snoweegamecorp.backend;

import java.time.LocalDateTime;
import java.util.*;
import com.snoweegamecorp.backend.model.PermissionModel;
import com.snoweegamecorp.backend.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	private PermissionRepository permissionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Value("${env.user.admin.username}")
	private String adminUsername;
	@Value("${env.user.admin.password}")
	private String adminPassword;
	@Value("${env.user.tester.username}")
	private String testerUsername;
	@Value("${env.user.tester.password}")
	private String testerPassword;
	@Value("${env.user.guest.username}")
	private String guestUsername;
	@Value("${env.user.guest.password}")
	private String guestPassword;
	public String profilePic = "https://i.imgur.com/CWf3Y4j.jpg";

	@Bean
	public CommandLineRunner init() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				String adminEncodedPass = passwordEncoder.encode(adminPassword);
				String testerEncodedPass = passwordEncoder.encode(testerPassword);
				String guestEncodedPass = passwordEncoder.encode(guestPassword);
				PermissionModel permissionAdmin = new PermissionModel(1L, "ROLE_ADMIN");
				PermissionModel permissionOperator = new PermissionModel(2L, "ROLE_OPERATOR");

				if (permissionRepository.existsById(permissionAdmin.getId()) && permissionRepository.existsById(permissionOperator.getId())){
						System.out.println("Permissions already recorded!");
				} else {
					permissionRepository.save(permissionAdmin);
					permissionRepository.save(permissionOperator);
					Set<PermissionModel> permissionsAll = new HashSet<>(Arrays.asList(permissionAdmin, permissionOperator));
					Set<PermissionModel> permissionsOperator = new HashSet<>(Arrays.asList(permissionAdmin, permissionOperator));
					UserModel adminUser = new UserModel(
							1L,
							"Erick",
							"Admin",
							adminUsername,
							adminEncodedPass,
							profilePic,
							LocalDateTime.now(),
							null,
							permissionsAll
					);
					userRepository.save(adminUser);
					UserModel testerUser = new UserModel(
							2L,
							"Erick",
							"Tester",
							testerUsername,
							testerEncodedPass,
							profilePic,
							LocalDateTime.now(),
							null,
							permissionsAll
					);
					userRepository.save(testerUser);
					UserModel operatorUser = new UserModel(
							3L,
							"Erick",
							"Operator",
							guestUsername,
							guestEncodedPass,
							profilePic,
							LocalDateTime.now(),
							null,
							permissionsOperator
					);
					userRepository.save(operatorUser);
				}
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
