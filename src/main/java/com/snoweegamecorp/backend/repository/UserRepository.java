package com.snoweegamecorp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snoweegamecorp.backend.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{

}
