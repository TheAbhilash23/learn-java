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
    public ResponseEntity<List<UserEntity>> list(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
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
    public ResponseEntity<?> create(@RequestBody UserEntity newUser){
        if (newUser.getId() != null) {
            newUser.setId(null);
        }
        userService.saveUser(newUser);
        return new ResponseEntity<>(true, HttpStatus.OK);
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
