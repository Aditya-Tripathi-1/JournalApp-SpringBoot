package net.engineeringdigest.journalApp.Entity2;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document    //  ye map krwayega esko collection se jo db me hai
@Getter
@Setter
public class JournalEntry2 {

    @Id // map as primary key
    private ObjectId id;
    private String title ;
    private String content;
    private LocalDate date;

}
