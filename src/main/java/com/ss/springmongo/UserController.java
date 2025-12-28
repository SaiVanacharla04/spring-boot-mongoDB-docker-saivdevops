// Define the package this class belongs to
package com.ss.springmongo;

// Import required classes for logging, dependency injection, and Spring MVC
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Marks this class as a Spring MVC controller that can handle HTTP requests
@Controller
public class UserController {

    // Repository for performing CRUD operations on User documents in MongoDB
    private final UserRepository userRepository;
    
    // Logger instance for logging information, warnings, and errors
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // Constructor-based dependency injection of UserRepository
    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Maps HTTP POST requests to /save to this method
    @PostMapping(value = "/save")
    public String save(
            // Binds request parameters from the form to method parameters
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email) {

        // Log the creation of a new user
        logger.info("Creating user name: " + firstName);

        // Create a new User object with the provided data
        User user = new User(firstName, lastName, email);

        // Save the User object to MongoDB using the repository
        userRepository.save(user);

        // Redirect the user back to the root URL ("/") after saving
        return "redirect:/";
    }
}
