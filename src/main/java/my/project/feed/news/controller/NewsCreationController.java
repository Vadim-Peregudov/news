package my.project.feed.news.controller;

import jakarta.validation.Valid;
import my.project.feed.news.entity.News;
import my.project.feed.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller class for handling news creation-related operations.
 */
@Controller
@RequestMapping("/home")
public class NewsCreationController {

    private final NewsService newsService;

    @Autowired
    public NewsCreationController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * Handles the GET request to show the create news page.
     *
     * @param model the model object to add attributes
     * @return the name of the create news page template
     */
    @GetMapping("/news")
    public String showPageCreateNews(Model model) {
        // Create a new instance of News and get it as an attribute on the model to display on the form
        model.addAttribute("news", new News());
        // Returns the name of the create news page template.
        return "create-news";
    }

    /**
     * Handles the POST request to save news and associated image.
     *
     * @param image         the news image file
     * @param news          the news object to save
     * @param bindingResult the binding result for validation errors
     * @return the view name to redirect or display in case of errors
     */
    @PostMapping("/api/news/create")
    public String saveNewsAndImage(
            @RequestParam("imageNews") MultipartFile image,
            @Valid @ModelAttribute("news") News news,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return the create-news view to display the errors
            return "create-news";
        }
        try {
            // Attempt to save the news and associated image
            newsService.saveNews(news, image);
        } catch (IOException e) {
            // If there is an exception while saving the image, add an error to the binding result and return create-news view
            bindingResult.reject("error.file", "Failed to save the file");
            return "create-news";
        }
        // Redirects to the home page after successfully saving the news and associated image
        return "redirect:/home/";
    }
}
