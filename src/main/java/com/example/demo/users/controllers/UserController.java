package com.example.demo.users.controllers;

import com.example.demo.users.entities.UserEntity;
import com.example.demo.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Convention is : controller --> service --> repository


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserEntity> list(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> retrieve(@PathVariable String id){
        UserEntity user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public boolean create(@RequestBody UserEntity newUser){
        userService.saveUser(newUser);
        return true;
    }

    @PatchMapping("/{id}/")
    public ResponseEntity<UserEntity> partialUpdate(@PathVariable String id, @RequestBody UserEntity updateUser){
        UserEntity user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user = userService.updateUserById(id, updateUser); // Updated user
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
