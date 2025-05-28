package com.example.demo.users.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "User entity representing a user in the system")
@Data
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_username", columnList = "username")
        }
)
public class UserEntity {

    @Schema(description = "Unique identifier of the user", example = "507f1f77bcf86cd799439011")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Schema(description = "A unique username for the user.", example = "johndoe")
    @Column(nullable = false, unique = true)
    private String username;

    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Schema(description = "Email address of the user", example = "JohnDoe@example.com")
    private String email;

    @Schema(description = "Password for the user", example = "Test@12345")
    private String password;

    @Schema(description = "Date when the user was created")
    private LocalDateTime date;


}

