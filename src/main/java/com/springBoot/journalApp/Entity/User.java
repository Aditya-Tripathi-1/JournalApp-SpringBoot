package com.springBoot.journalApp.Entity;


import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection ="user")
@Getter
@Setter
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true) // indexed se searching fast hogi aur unique true se unique hoga
    // lekin ye automatic nhi,automatic krne ke liye  resource me ek property set krni hogi
    //  i.e -> spring.data.mongodb.auto-index-creation=true
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @DBRef  // ye user collections me JournalEntry collection ka ref rakhega
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
