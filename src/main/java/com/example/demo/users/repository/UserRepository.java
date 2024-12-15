package com.example.demo.users.repository;

import com.example.demo.users.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<UserEntity, String> {

}
