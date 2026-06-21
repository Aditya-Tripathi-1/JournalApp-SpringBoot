package com.springBoot.journalApp.Repository;

import com.springBoot.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepo extends MongoRepository<User, ObjectId> {

     User findByuserName(String userName);


     void deleteByuserName(String userName);
}
