// Define the package this class belongs to
package com.ss.springmongo;

// Import Spring classes for configuration and web MVC
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Marks this class as a configuration class for Spring
@Configuration

// Enables Spring MVC-specific configuration, such as handling requests and static resources
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // Override the addResourceHandlers method to configure locations of static resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Define URL patterns and map them to locations in the classpath
        registry.addResourceHandler(
                "/webjars/**",  // For libraries packaged in webjars (e.g., Bootstrap, jQuery)
                "/img/**",      // For images
                "/css/**",      // For CSS files
                "/js/**")       // For JavaScript files
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/", // Webjar library location
                        "classpath:/static/img/",                // Images location
                        "classpath:/static/css/",                // CSS location
                        "classpath:/static/js/");                // JS location
    }

}
