package com.springBoot.journalApp.Service;

import com.springBoot.journalApp.Entity.JournalEntry;
import com.springBoot.journalApp.Entity.User;
import com.springBoot.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
// service package ke ander hm apna business logic likhate h

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    @Transactional      // ye method ka pura kaam khatm hone pr hi output me ok dega,agr koi bhi work
    // method ke adar ke nhi ho paya error ki wjh se toh ye jo work hua hoga use bhi rollback kr dega.
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByuserName(userName);
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occured while saving entry",e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getall() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findbyId(ObjectId id) {
        return journalEntryRepo.findById(id);
    }
    @Transactional
    public boolean deletebyId(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByuserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveEntry(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the entry : " ,e );
        }
return removed;

    }




}
// controller--> call krega --> service ko --> call --> JournalEntryRepo ko