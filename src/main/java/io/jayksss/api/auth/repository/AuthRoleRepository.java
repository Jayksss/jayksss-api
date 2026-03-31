package io.jayksss.api.auth.repository;

import io.jayksss.api.auth.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, String> {
}

