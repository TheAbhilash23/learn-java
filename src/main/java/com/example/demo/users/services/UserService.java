package com.example.demo.users.services;

import com.example.demo.users.entities.UserEntity;
import com.example.demo.users.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // if the PasswordEncoder is not linted, don't worry, it is a bean.
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String getHashedPassword(String rawPassword) {
        return this.passwordEncoder.encode(rawPassword);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public void createUser(UserEntity user) throws ValidationException {
        user.setDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean isUsernameTaken = userRepository.existsByUsername(user.getUsername());
        if (isUsernameTaken) {
            throw new ValidationException("Username already taken.");
        } else if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email address already Registered.");
        }
        userRepository.save(user);
    }


    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity updateUserById(String id, UserEntity userData) {
        UserEntity old = userRepository.findById(id).orElse(null);
        if (old != null) {
            // username and date can't change
            old.setUsername(!userData.getUsername().isBlank() ? userData.getUsername() : old.getUsername());
            old.setDate(userData.getDate() != null && !userData.getDate().toString().isBlank() ? userData.getDate() : LocalDateTime.now());

            // first name, last name can change
            old.setFirstName(userData.getFirstName() != null && !userData.getFirstName().isBlank() ? userData.getFirstName() : old.getFirstName());
            old.setLastName(userData.getLastName() != null && !userData.getLastName().isBlank() ? userData.getLastName() : old.getLastName());
            userRepository.save(old);
            return old;
        }
        return null;
    }

    public boolean deleteUserById(String id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
