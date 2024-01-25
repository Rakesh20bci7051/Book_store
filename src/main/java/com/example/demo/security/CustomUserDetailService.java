package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.*;
import com.example.demo.security.CustomUserDetailService;

@Service
public class CustomUserDetailService implements UserDetailsService {

	
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    
	   // private UserRepo userRepo;
	    private final UserRepo userRepo;

	    @Autowired
	    public CustomUserDetailService(UserRepo userRepo) {
	        this.userRepo = userRepo;
	    }
	    //logger.info("Searching for user with email: {}", username);
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	logger.info("Searching for user with Username: {}", username);
	    	logger.info(username);
	    	
	    	  //System.out.println("Received username: " + username);
	        User user = this.userRepo.findByUsername(username)
	                .orElseThrow(() -> new ResourceNotFoundException("User", "username:" + username, 0));

	        // Log the email using SLF4J logger
	        logger.info("Username is: {}", user.getUsername());

	        // Return the UserDetails object (assuming User implements UserDetails)
	        return user;
	    }
}
