package com.example.demo.users.services;

import com.example.demo.users.entities.UserEntity;
import com.example.demo.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserEntity user) {
        user.setDate(LocalDateTime.now());
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
