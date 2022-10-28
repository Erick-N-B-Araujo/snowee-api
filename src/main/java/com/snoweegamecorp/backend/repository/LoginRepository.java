package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginModel, Long> {
}
