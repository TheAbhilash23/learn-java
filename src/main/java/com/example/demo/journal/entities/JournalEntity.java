package com.example.demo.journal.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "journals")
@Data
public class JournalEntity {

    @Id
    private ObjectId id;
    private String title;
    private String content;

}

