package com.example.demo.users.entities;

import com.example.demo.journal.entities.JournalEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Schema(description = "User entity representing a user in the system")
public class UserEntity {

    @Id
    @Schema(description = "Unique identifier of the user", example = "507f1f77bcf86cd799439011")
    private ObjectId id;

    @Schema(description = "A unique username for the user.", example = "johndoe")
    @Indexed(unique = true)
    private String username;

    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Schema(description = "Email address of the user", example = "JohnDoe@example.com")
    @Indexed
    private String email;

    @Schema(description = "Password for the user", example = "Test@12345")
    private String password;

    @Schema(description = "Date when the user was created")
    private LocalDateTime date;

    // To create foreign key relation with journal entries we use DBRef annotation
    @DBRef
    @Schema(description = "List of journal entries associated with the user")
    private List<JournalEntity> journalEntries = new ArrayList<>();

}

