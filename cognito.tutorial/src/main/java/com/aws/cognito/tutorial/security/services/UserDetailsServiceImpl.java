package com.aws.cognito.tutorial.security.services;

import com.aws.cognito.tutorial.security.entities.User;
import com.aws.cognito.tutorial.security.entities.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return UserMain.build(user);
    }
}
