package com.snoweegamecorp.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private JwtTokenStore tokenStore;

    @Autowired
    private Environment env;

    private static final String[] AUTH = {"/oauth/token", "/auth/**", "/h2-console/**"};
    private static final String[] PUBLIC = {"/themes/list-all", "/articles/list-all", "/themes/**", "/articles/**", "/users/find"};
    private static final String[] OPERATOR_OR_ADMIN = {"/users/**", "/themes/**", "/articles/**"};
    private static final String[] ADMIN = {"/users/**","/permissions/**", "/themes/**", "/articles/**"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //Liberar H2
        if (Arrays.asList(env.getActiveProfiles()).contains("dev")){
            http.headers().frameOptions().disable();
        }

        http.authorizeRequests()
                .antMatchers(AUTH).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll()
                .antMatchers(HttpMethod.PATCH, OPERATOR_OR_ADMIN).permitAll()
                .antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR","ADMIN")
                .antMatchers(ADMIN).hasRole("ADMIN")
                .anyRequest().authenticated();
    }
}
