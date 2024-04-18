package com.example.demo.repositories;

import com.example.demo.models.User;
import com.google.cloud.firestore.WriteResult;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository extends GenericRepositoryImpl {
    public void createUser(User user) throws ExecutionException, InterruptedException {
        String documentId = UUID.randomUUID().toString();
        createDocument("Users", documentId, user);
    }

    public Optional<User> findByUsername(String username) throws ExecutionException, InterruptedException {
        List<User> users = getDocumentsByAttribute("Users", "username", username, User.class);
        if (users.isEmpty()) {
            return Optional.empty();
        }
    
        return Optional.of(users.get(0));
    }
}