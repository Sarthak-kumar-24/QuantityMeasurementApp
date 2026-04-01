package com.quantitymeasurement.security;


import com.quantitymeasurement.entity.User;
import com.quantitymeasurement.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * =========================================================
 * Custom User Details Service
 * =========================================================
 *
 * Purpose:
 * This class tells Spring Security HOW to load user data
 * from the database during authentication.
 *
 * It acts as a bridge between:
 * Spring Security <-> Database (User entity)
 * 
 * 
 * UserDetailsService is a prebuilt interface provided by Spring Security.
 * It tells Spring Security HOW to fetch user data during login
 */
@Service  // Marks this as a Spring service component
public class CustomUserDetailsService implements UserDetailsService {

    /*
     * Repository used to fetch user from database
     */
    @Autowired
    private UserRepository repository;

    
    /*
     * =========================================================
     * loadUserByUsername()
     * =========================================================
     *
     * This method is called automatically by Spring Security
     * during login.
     *
     * Input:
     * username (from login request)
     *
     * Output:
     * UserDetails object (Spring Security format)
     * 
     * 
     *  UsernameNotFoundException ?
     *  - It is a prebuilt exception class provided by Spring Security.
     */
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Search strictly by EMAIL
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // We return the email as the first parameter (the 'username' in Spring Security terms)
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), 
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
