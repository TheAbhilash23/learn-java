package com.example.demo.users.controllers;

import com.example.demo.controllers.BaseController;
import com.example.demo.users.entities.UserEntity;
import com.example.demo.users.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Convention is : controller --> service --> repository

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController extends BaseController<UserEntity> {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<UserEntity>> list(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> retrieve(@PathVariable String id){
        UserEntity user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody UserEntity newUser){
        newUser.setId(null);
        userService.saveUser(newUser);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}/")
    public ResponseEntity<UserEntity> partialUpdate(@PathVariable String id, @RequestBody UserEntity updateUser){
        UserEntity user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user = userService.updateUserById(id, updateUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}/")
    public ResponseEntity<?> delete(@PathVariable String id){
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
