package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.LoginModel;
import com.snoweegamecorp.backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginModel, Long> {
    LoginModel findByUsername(String username);
    LoginModel findTopByOrderByIdDesc();
}
