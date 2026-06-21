package com.springBoot.journalApp.Controller;

import com.springBoot.journalApp.Entity.User;
import com.springBoot.journalApp.Repository.UserEntryRepo;
import com.springBoot.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

// controller -> special types of classes or components who handles https requests
// note --> controller endpoint bnayega aur service ko call krega ,aur service ke andar hm bs
// business logic likhate h
@RestController
@RequestMapping("/User")  // ye es puri class me mapping  add kr deta hai
public class UserEntryController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserEntryRepo userEntryRepo;

    @PutMapping
    public ResponseEntity<?> UpdateUser(@RequestBody User user ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userIndb = userService.findByuserName(userName);

        userIndb.setUserName(user.getUserName());
        userIndb.setPassword(user.getPassword());
        userService.saveNewEntry(userIndb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> DeleteUserByName(@RequestBody User user ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userEntryRepo.deleteByuserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


// spring security add krne se pahle ka scene

// @RestController
//@RequestMapping("/User")  // ye es puri class me mapping  add kr deta hai
//public class UserEntryController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<User> getAllusers(){
//        return userService.getall();
//    }
//
//    @PostMapping
//    public void createUser(@RequestBody User user){
//        userService.saveEntry(user);
//    }
//
//    @PutMapping("/{UserName}")
//    public ResponseEntity<?> UpdateUser(@RequestBody User user,@PathVariable String UserName ) {
//        User userIndb = userService.findByuserName(UserName);
//        if ( userIndb != null ) {
//            userIndb.setUserName(user.getUserName());
//            userIndb.setPassword(user.getPassword());
//            userService.saveEntry(userIndb);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//}
