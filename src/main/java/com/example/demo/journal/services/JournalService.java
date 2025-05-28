package com.example.demo.journal.services;

import com.example.demo.journal.dto.JournalDto;
import com.example.demo.journal.entities.JournalEntity;
import com.example.demo.journal.repository.JournalRepository;
import com.example.demo.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveJournal(JournalDto journal) throws ChangeSetPersister.NotFoundException {
        JournalEntity newJournal = JournalEntity.builder().
                title(journal.getTitle()).
                content(journal.getContent()).
                user(userRepository.findById(String.valueOf(journal.getUserId())).orElseThrow(ChangeSetPersister.NotFoundException::new)).
                build();

        journalRepository.save(newJournal);
    }

    public List<JournalEntity> getAll() {
        return journalRepository.findAll();
    }

    public JournalEntity getJournalById(String id) {
        return journalRepository.findById(id).orElse(null);
    }

    public JournalEntity updateJournalById(String id, JournalDto journalData) {
        JournalEntity old = journalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (old != null) {
            old.setTitle(journalData.getTitle() != null && !journalData.getTitle().equals("") ? journalData.getTitle() : old.getTitle());
            old.setContent(journalData.getContent() != null && !journalData.getContent().equals("") ? journalData.getContent() : old.getContent());
            journalRepository.save(old);
            return old;
        }
        return null;
    }
}
