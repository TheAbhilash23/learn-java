package com.example.demo.journal.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journals")
@Data
@Schema(description = "Journal entry entity representing a journal entry in the system")
public class JournalEntity {

    @Id
    @Schema(description = "Unique identifier of the journal entry", example = "507f1f77bcf86cd799439011")
    private ObjectId id;

    @Schema(description = "Title of the journal entry", example = "My First Journal Entry")
    @NonNull
    private String title;

    @Schema(description = "Content of the journal entry", example = "Today was a great day...")
    private String content;
}

