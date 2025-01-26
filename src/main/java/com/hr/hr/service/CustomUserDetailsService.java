package com.hr.hr.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hr.models.UserInfo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;  // Your service that fetches user info from the database

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user info from the database
        UserInfo userInfo = userInfoService.findUserByEmail(email); // Assuming your service has this method

        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Return a Spring Security User object with the necessary info
        return User.builder()
                .username(userInfo.getEmail())  // Use email as username
                .password(userInfo.getPassword())  // Password from the database (ensure it's encrypted)
                .roles(userInfo.getRole())  // Roles (adjust this based on your database schema)
                .build();
    }
}
