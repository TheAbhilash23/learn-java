package com.example.demo.journal.repository;

import com.example.demo.journal.entities.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JournalRepository extends JpaRepository<JournalEntity, String> {
    JournalEntity findByUserId();
}
