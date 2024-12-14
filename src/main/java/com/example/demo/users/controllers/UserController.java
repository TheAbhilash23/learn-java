package com.example.demo.users.controllers;

import com.example.demo.users.entities.UserEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Long, UserEntity> users = new HashMap<>();

    @GetMapping("")
    public List<UserEntity> list(){
        return new ArrayList<>(users.values());
    }

    @PostMapping("/")
    public UserEntity create(@RequestBody UserEntity newUser){
        if (!users.isEmpty()){
            Long id = Collections.max(users.keySet()) + 1L;
            System.out.println(id);
            users.put(id, newUser);
            return users.get(id);
        }
        System.out.println("1L");
        users.put(1L, newUser);
        return users.get(1L);
    }
}
