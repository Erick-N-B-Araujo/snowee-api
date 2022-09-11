package com.snoweegamecorp.backend;

import java.time.LocalDateTime;

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

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
