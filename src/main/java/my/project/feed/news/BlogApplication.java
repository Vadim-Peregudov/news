package my.project.feed.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class of the feed application.
 * The @SpringBootApplication annotation is used to enable Spring Boot's auto-configuration and component scanning.
 * It combines the @Configuration, @EnableAutoConfiguration, and @ComponentScan annotations.
 * The main method is the entry point for the application. It calls the run method of SpringApplication class to start the Spring Boot application.
 */
@SpringBootApplication
public class BlogApplication {
    /**
     * The main method of the feed application.
     * It starts the Spring Boot application by calling the run method of SpringApplication class,
     * passing the BlogApplication class and command-line arguments.
     *
     * @param args The command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
