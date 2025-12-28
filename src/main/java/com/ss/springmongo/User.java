// Define the package this class belongs to
package com.ss.springmongo;

// Import required Java and Spring Data classes
import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Marks this class as a MongoDB document and specifies the collection name as "users"
@Document(collection = "users")
public class User implements Serializable {

    // Serial version UID for Serializable interface to ensure compatibility during serialization
    private static final long serialVersionUID = 1L;
    
    // Marks this field as the unique identifier for MongoDB documents
    @Id
    private String id;

    // Fields for user details
    private String firstName;
    private String lastName;
    private String email;

    // Constructor to initialize User object with firstName, lastName, and email
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getter and Setter methods for each field

    // Returns the ID of the user
    public String getId() {
        return id;
    }

    // Sets the ID of the user
    public void setId(String id) {
        this.id = id;
    }

    // Returns the first name of the user
    public String getFirstName() {
        return firstName;
    }

    // Sets the first name of the user
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Returns the last name of the user
    public String getLastName() {
        return lastName;
    }

    // Sets the last name of the user
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Returns the email of the user
    public String getEmail() {
        return email;
    }

    // Sets the email of the user
    public void setEmail(String email) {
        this.email = email;
    }

    // Overrides equals() method to compare User objects based on their fields
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Same object reference
        if (o == null || getClass() != o.getClass()) return false; // Different class
        User user = (User) o;
        // Compare all relevant fields
        return Objects.equals(id, user.id) &&
               Objects.equals(firstName, user.firstName) &&
               Objects.equals(lastName, user.lastName) &&
               Objects.equals(email, user.email);
    }

    // Overrides hashCode() method to generate hash code based on fields
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}
