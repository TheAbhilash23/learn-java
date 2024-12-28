package com.example.demo.journal.controllers;

import com.example.demo.journal.entities.JournalEntity;
import com.example.demo.journal.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Convention is : controller --> service --> repository


@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping("")
    public ResponseEntity<List<JournalEntity>> list(){
        return new ResponseEntity<>(journalService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntity> retrieve(@PathVariable String id){
        JournalEntity journal = journalService.getJournalById(id);
        if (journal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody JournalEntity newJournal){
        if (newJournal.getId() != null) {
            newJournal.setId(null);
        }
        journalService.saveJournal(newJournal);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PatchMapping("/{id}/")
    public ResponseEntity<JournalEntity> partialUpdate(@PathVariable String id, @RequestBody JournalEntity updateJournal){
        JournalEntity journal = journalService.getJournalById(id);
        if (journal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        journal = journalService.updateJournalById(id, updateJournal); // Updated journal
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }
}
