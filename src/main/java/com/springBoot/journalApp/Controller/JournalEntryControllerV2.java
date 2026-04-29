package com.springBoot.journalApp.Controller;

import com.springBoot.journalApp.Entity.JournalEntry;
import com.springBoot.journalApp.Entity.User;
import com.springBoot.journalApp.Service.JournalEntryService;
import com.springBoot.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

// controller -> special types of classes or components who handles https requests
// note --> controller endpoint bnayega aur service ko call krega ,aur service ke andar hm bs
// business logic likhate h
@RestController
@RequestMapping("/Journal")  // ye es puri class me mapping  add kr deta hai
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByuserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if ( all != null && !all.isEmpty() ) {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName ) {
        try {
            myEntry.setDate(LocalDate.now());
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findbyId(myId);
        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryByID(@PathVariable ObjectId myId,@PathVariable String userName) {
        journalEntryService.deletebyId(myId,userName);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> UpdateJournalEntryByID(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry,@PathVariable String userName) {
        JournalEntry old = journalEntryService.findbyId(myId).orElse(null);
        if ( old != null ) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
