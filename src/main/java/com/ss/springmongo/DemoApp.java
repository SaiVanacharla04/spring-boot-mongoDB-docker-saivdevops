// Define the package this class belongs to
package com.ss.springmongo;

// Import necessary Spring Boot and Spring MVC classes
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Marks this class as a Spring MVC Controller, which can handle HTTP requests
@Controller

// Enables Spring Boot's auto-configuration feature, which automatically configures
// your Spring application based on the dependencies you have in your project
@EnableAutoConfiguration

// Tells Spring to scan the specified package and its sub-packages for components,
// configurations, and services (like @Service, @Repository, @Controller)
@ComponentScan(basePackages = "com.ss.*")
public class DemoApp {

    // Main method: the entry point of the Spring Boot application
    public static void main(String[] args) {
        // Launches the Spring Boot application
        SpringApplication.run(DemoApp.class, args);
    }

    // Maps HTTP requests to the root URL ("/") to this method
    @RequestMapping("/")
    public String index() {
        // Returns the "index.html" page as the response
        return "index.html";
    }
}
