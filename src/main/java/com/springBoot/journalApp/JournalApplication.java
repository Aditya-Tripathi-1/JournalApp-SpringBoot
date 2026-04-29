package com.springBoot.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement  // ye @Transactional ko working bnayega means uski service enable krega
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);



    }

    @Bean
    public PlatformTransactionManager xyz(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager( dbFactory);
    }
}
// PlatformTransactionManager -> ye ek interface h jo internal working krta h , jaise rollback ydi error aya
// commit krega jb sb ok hoga etc.

// MongoTransactionManager --> ye PlatformTransactionManager ka implementation h but esko implement
// krne ke liye ke bean bnani pdegi usame hm btayenge ki PlatformTransactionManager ek interface h
// aur MongoTransactionManager uska implementation h

// MongoDatabaseFactory -> ye help krta h database ke sath connetion banane me

// @Transactional , @EnableTransactionManagement,PlatformTransactionManager,MongoTransactionManager
// ye sb work krega replica set ke liye kewal. kyu?
// Replication kya hota hai?
//MongoDB me replication ka matlab hota hai:
// Ek hi data ko multiple servers (nodes) par rakhna
// Taaki agar ek server down ho jaye, doosra kaam kare
//Ye group ko bolte hain Replica Set.
// kyuki -> Without replication:
//Single server crash → data loss
//Transaction half complete ho sakta hai (ACID break).

// abhi hmara mongo db server localhost pr chal rha h , ab hm ese aws pr store krenge with the
// help of mongoDB atlas