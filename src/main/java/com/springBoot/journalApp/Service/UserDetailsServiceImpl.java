package com.springBoot.journalApp.Service;

import com.springBoot.journalApp.Entity.User;
import com.springBoot.journalApp.Repository.UserEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


// ye user authentication ke liye h esko userservice me bhi kr skte h lekin clean rakhne ke ly
// alg kr rhe h
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserEntryRepo userEntryRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byuserName = userEntryRepo.findByuserName(username);
        if ( byuserName != null) {
          return   org.springframework.security.core.userdetails.User.builder()
                    .username(byuserName.getUserName())
                    .password(byuserName.getPassword())
                    .roles(byuserName.getRole().toArray(new String[0]))
                    .build();

        }
        throw new UsernameNotFoundException("User not found with username : "+username);
    }
}
