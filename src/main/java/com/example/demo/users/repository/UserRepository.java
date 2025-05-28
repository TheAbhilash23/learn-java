package com.example.demo.users.repository;

import com.example.demo.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
