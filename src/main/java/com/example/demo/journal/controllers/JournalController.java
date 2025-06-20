package com.example.demo.journal.controllers;

import com.example.demo.core.controllers.BaseController;
import com.example.demo.journal.dto.JournalDto;
import com.example.demo.journal.entities.JournalEntity;
import com.example.demo.journal.services.JournalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Convention is : controller --> service --> repository


@RestController
@RequestMapping("/journals")
@Tag(name = "Journal Management", description = "APIs for managing journal entries")
public class JournalController extends BaseController<JournalEntity, JournalDto> {

    @Autowired
    private JournalService journalService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<JournalEntity>> list(){
        return new ResponseEntity<>(journalService.getAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<JournalEntity> retrieve(@PathVariable String id){
        JournalEntity journal = journalService.getJournalById(id);
        if (journal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody JournalDto newJournal) {
        try {
            JournalDto journalDto = new JournalDto();
            journalDto.setTitle(newJournal.getTitle());
            journalDto.setContent(newJournal.getContent());
            journalDto.setUserId(newJournal.getUserId());
            journalService.saveJournal(journalDto);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PatchMapping("/{id}/")
    public ResponseEntity<JournalEntity> partialUpdate(@PathVariable String id, @RequestBody JournalDto updateJournal){

        JournalEntity journal = journalService.getJournalById(id);
        if (journal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        journal = journalService.updateJournalById(id, updateJournal);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }
    @Override
    @DeleteMapping("/{id}/")
    public ResponseEntity<?> delete(@PathVariable String id){
//        boolean isDeleted = journalService.deleteUserById(id);
        boolean isDeleted = true;
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
