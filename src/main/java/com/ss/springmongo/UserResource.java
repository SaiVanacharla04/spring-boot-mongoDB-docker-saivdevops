// Define the package this class belongs to
package com.ss.springmongo;

// Import required classes for collections, logging, and Spring REST
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Marks this class as a REST controller where each method returns JSON or other HTTP responses
@RestController

// Base URL for all endpoints in this controller: "/api/users"
@RequestMapping(value = "/api/users")
public class UserResource {

    // Repository for performing CRUD operations on User documents
    private final UserRepository userRepository;

    // Logger instance for logging information and debugging
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    
    // Constructor-based dependency injection of UserRepository
    @Autowired
    public UserResource(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Handles HTTP GET requests to "/api/users"
    // Produces JSON output for all users
    @GetMapping(produces = "application/JSON")
    public List<User> getAllUsers() {
        // Fetch all users from MongoDB
        List<User> users = (List<User>) userRepository.findAll();

        // Log the number of users retrieved
        logger.info("Get Users Total Users: " + users.size());

        // Return the list of users as JSON
        return users;
    }

    // Handles HTTP GET requests to "/api/users/{id}"
    // Produces JSON output for a single user
    @GetMapping(value = "/{id}", produces = "application/JSON")
    public Optional<User> findById(@PathVariable("id") String userID) {
        // Log the ID of the user being requested
        logger.info("Get User By Id : " + userID);

        // Retrieve the user by ID from MongoDB
        // Returns Optional<User> in case the user does not exist
        return userRepository.findById(userID);
    }
}
