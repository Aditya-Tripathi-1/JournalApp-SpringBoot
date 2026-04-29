package com.springBoot.journalApp.Service;

import com.springBoot.journalApp.Entity.JournalEntry;
import com.springBoot.journalApp.Entity.User;
import com.springBoot.journalApp.Repository.JournalEntryRepo;
import com.springBoot.journalApp.Repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
// service package ke ander hm apna business logic likhate h
    @Autowired

    private UserEntryRepo userEntryRepo;

    public void saveEntry(User user) {
        userEntryRepo.save(user);
    }

    public List<User> getall() {
        return userEntryRepo.findAll();
    }

    public Optional<User> findbyId(ObjectId id) {
        return userEntryRepo.findById(id);
    }
    public void deletebyId(ObjectId id) {
         userEntryRepo.deleteById(id);
    }

    public User findByuserName(String userName) {
        return userEntryRepo.findByuserName(userName);
    }


}
// controller--> call krega --> service ko --> call --> JournalEntryRepo ko