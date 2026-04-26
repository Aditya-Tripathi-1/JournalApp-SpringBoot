package net.engineeringdigest.journalApp.Controler;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_Journal")  // ye es puri class me mapping  add kr deta hai
public class JournalEntryController {

    // yha pr hm database ka use abhi nhi kr rhe so map ka use kr rhe h
    private Map<Integer, JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getall() {   // localhost:8080//Journal -> Get
        return new ArrayList<>(journalEntryMap.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry ) {   // localhost:8080//Journal --> Post
        journalEntryMap.put(myEntry.getId() ,myEntry);
        return true;
    }

    @GetMapping("id/{myId}")  // eski help se hm perticular id ki value dekh payenge
    public JournalEntry getJournalEntryByID(@PathVariable Integer myId) {
        return journalEntryMap.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryByID(@PathVariable Integer myId) {
        return journalEntryMap.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry UpdateJournalEntryByID(@PathVariable Integer myId,@RequestBody JournalEntry myentry) {
        return journalEntryMap.put(myId,myentry);
    }

}
