package com.example.demo.users.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "users")
@Data
public class UserEntity {

    @Id
    private ObjectId id;

    private String name;
    private String email;
    private LocalDateTime date;

}

