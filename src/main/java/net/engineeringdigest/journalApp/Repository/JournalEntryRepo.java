package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.Entity2.JournalEntry2;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry2, ObjectId> {

}
