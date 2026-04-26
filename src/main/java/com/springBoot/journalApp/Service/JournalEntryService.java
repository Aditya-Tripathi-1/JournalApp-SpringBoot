package com.springBoot.journalApp.Service;

import com.springBoot.journalApp.Entity.JournalEntry2;
import com.springBoot.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
// service package ke ander hm apna business logic likhate h
    @Autowired

    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry2 journalEntry2) {
        journalEntryRepo.save(journalEntry2);
    }

    public List<JournalEntry2> getall() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry2> findbyId(ObjectId id) {
        return journalEntryRepo.findById(id);
    }
    public void deletebyId(ObjectId id) {
         journalEntryRepo.deleteById(id);
    }



}
// controller--> call krega --> service ko --> call --> JournalEntryRepo ko