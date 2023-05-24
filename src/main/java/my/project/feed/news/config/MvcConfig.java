package my.project.feed.news.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for customizing the Spring MVC configuration.
 * Implements the WebMvcConfigurer interface to override default configurations.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.dir.image}")
    private String uploadPath;

    /**
     * Configures the resource handlers to serve static resources.
     *
     * @param registry the resource handler registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configures the resource handler for serving image files from the specified upload directory
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + "/");
        // Configures the resource handler for serving static resources from the classpath
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
