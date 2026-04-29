package com.springBoot.journalApp.Entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document (collection = "JournalEntry")   //  ye map krwayega esko collection se jo db me hai
@Getter
@Setter
public class JournalEntry {

    @Id // map as primary key
    private ObjectId id;
    @NonNull
    private String title ;
    private String content;
    private LocalDate date;


}
