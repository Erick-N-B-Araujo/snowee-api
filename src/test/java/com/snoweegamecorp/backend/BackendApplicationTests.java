package com.snoweegamecorp.backend;

import com.snoweegamecorp.backend.utils.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BackendApplicationTests {

	private String adminUsername;
	private String adminPassword;
	@Autowired
	private TokenUtil tokenUtil;
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception{
		adminUsername = "batistasd678@gmail.com";
		adminPassword = "1234";
	}

	@Test
	void contextLoads() {
	}
	@Test
	public void getToken() throws Exception {
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
	}

}
