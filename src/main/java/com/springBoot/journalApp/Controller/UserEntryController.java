package com.springBoot.journalApp.Controller;

import com.springBoot.journalApp.Entity.JournalEntry;
import com.springBoot.journalApp.Entity.User;
import com.springBoot.journalApp.Service.JournalEntryService;
import com.springBoot.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// controller -> special types of classes or components who handles https requests
// note --> controller endpoint bnayega aur service ko call krega ,aur service ke andar hm bs
// business logic likhate h
@RestController
@RequestMapping("/User")  // ye es puri class me mapping  add kr deta hai
public class UserEntryController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllusers(){
        return userService.getall();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{UserName}")
    public ResponseEntity<?> UpdateUser(@RequestBody User user,@PathVariable String UserName ) {
        User userIndb = userService.findByuserName(UserName);
        if ( userIndb != null ) {
            userIndb.setUserName(user.getUserName());
            userIndb.setPassword(user.getPassword());
            userService.saveEntry(userIndb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
