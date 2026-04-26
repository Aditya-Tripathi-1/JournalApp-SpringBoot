package net.engineeringdigest.journalApp.Controler;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity2.JournalEntry2;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
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

    @GetMapping
    public ResponseEntity<List<JournalEntry2>> getall() {
        List<JournalEntry2> all = journalEntryService.getall();
        if ( all != null && !all.isEmpty() ) {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry2> createEntry(@RequestBody JournalEntry2 myEntry ) {
        try {
            myEntry.setDate(LocalDate.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry2> getJournalEntryByID(@PathVariable ObjectId myId) {
        Optional<JournalEntry2> journalEntry2 = journalEntryService.findbyId(myId);
        if(journalEntry2.isPresent()) {
            return new ResponseEntity<>(journalEntry2.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryByID(@PathVariable ObjectId myId) {
        journalEntryService.deletebyId(myId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry2> UpdateJournalEntryByID(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry) {
        JournalEntry2 old = journalEntryService.findbyId(myId).orElse(null);
        if ( old != null ) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());

            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
