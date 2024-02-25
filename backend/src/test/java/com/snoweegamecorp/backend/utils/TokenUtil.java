package com.snoweegamecorp.backend.utils;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestController
@RequestMapping("/oauth/token")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
public class TokenUtil {
    private String clientId = "snoweeapi";
    private String clientSecret  = "snoweeapi";
    public String obtainAccessToken(MockMvc mvc, String username, String password) throws Exception {

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client-id", clientId);
        params.add("username", username);
        params.add("password", password);

        ResultActions result = mvc
                .perform(post("/oauth/token")
                        .params(params)
                        .with(httpBasic(clientId,clientSecret))
                        .accept("application/json;charset=UTF-8"))
                        .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result
                .andReturn()
                .getResponse()
                .getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}
