package com.example.demo.journal.repository;

import com.example.demo.journal.entities.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalRepository extends MongoRepository<JournalEntity, String> {

}
