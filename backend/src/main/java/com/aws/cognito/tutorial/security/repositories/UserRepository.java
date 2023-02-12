package com.aws.cognito.tutorial.security.repositories;

import com.aws.cognito.tutorial.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String userEmail);
}
