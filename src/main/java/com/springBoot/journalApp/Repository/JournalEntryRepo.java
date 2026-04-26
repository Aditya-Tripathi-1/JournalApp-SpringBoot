package com.springBoot.journalApp.Repository;

import com.springBoot.journalApp.Entity.JournalEntry2;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry2, ObjectId> {

}
