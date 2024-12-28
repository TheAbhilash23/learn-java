package com.example.demo.users.services;

import com.example.demo.users.entities.UserEntity;
import com.example.demo.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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

    public UserEntity getUserById (String id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity updateUserById (String id, UserEntity userData) {
        UserEntity old = userRepository.findById(id).orElse(null);
        if (old != null) {
            old.setName(userData.getName() != null && !userData.getName().equals("") ? userData.getName() : old.getName());
            old.setEmail(userData.getEmail() != null && !userData.getEmail().equals("") ? userData.getEmail() : old.getEmail());
            old.setDate(userData.getDate() != null && !userData.getDate().equals("") ? userData.getDate() : LocalDateTime.now());
            userRepository.save(old);
            return old;
        }
        return null;
    }
}
