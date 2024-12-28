package com.example.demo.journal.services;

import com.example.demo.journal.entities.JournalEntity;
import com.example.demo.journal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public void saveJournal(JournalEntity journal) {
        journalRepository.save(journal);
    }

    public List<JournalEntity> getAll() {
        return journalRepository.findAll();
    }

    public JournalEntity getJournalById (String id) {
        return journalRepository.findById(id).orElse(null);
    }

    public JournalEntity updateJournalById (String id, JournalEntity journalData) {
        JournalEntity old = journalRepository.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(journalData.getTitle() != null && !journalData.getTitle().equals("") ? journalData.getTitle() : old.getTitle());
            old.setContent(journalData.getContent() != null && !journalData.getContent().equals("") ? journalData.getContent() : old.getContent());
            journalRepository.save(old);
            return old;
        }
        return null;
    }
}
