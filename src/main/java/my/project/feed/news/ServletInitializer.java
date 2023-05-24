package my.project.feed.news;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The ServletInitializer class is used for configuring the application when deploying it as a WAR file in a servlet container.
 * It extends the SpringBootServletInitializer class, which provides a convenient way to configure a Spring Boot application for deployment to a servlet container.
 * The configure method is overridden to specify the main application class (BlogApplication) as the source for the application.
 * This method is called by the servlet container to initialize the application when deploying the WAR file.
 */
public class ServletInitializer extends SpringBootServletInitializer {
    /**
     * Configures the application for deployment in a servlet container.
     * The configure method takes a SpringApplicationBuilder as a parameter and returns the same builder instance
     * after configuring it with the main application class (BlogApplication).
     *
     * @param application The builder for configuring the application
     * @return The configured builder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogApplication.class);
    }

}
