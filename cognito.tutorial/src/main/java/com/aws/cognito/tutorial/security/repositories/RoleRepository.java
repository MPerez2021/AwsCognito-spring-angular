package com.aws.cognito.tutorial.security.repositories;

import com.aws.cognito.tutorial.security.entities.Role;
import com.aws.cognito.tutorial.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(RoleName roleName);
}
