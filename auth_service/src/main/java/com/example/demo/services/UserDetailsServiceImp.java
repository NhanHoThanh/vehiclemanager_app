package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            Optional<User> user = repository.findByUsername(username);
            userDetails = user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        } catch (ExecutionException | InterruptedException e) {
            // Handle the exceptions
            e.printStackTrace();
            throw new UsernameNotFoundException("Error occurred while fetching user details", e);
        }
        return userDetails;
    }
}