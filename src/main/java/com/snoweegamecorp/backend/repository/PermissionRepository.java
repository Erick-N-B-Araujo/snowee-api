package com.snoweegamecorp.backend.repository;

import com.snoweegamecorp.backend.model.PermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionModel, Long> {
}
