package my.project.feed.news.controller;

import my.project.feed.news.entity.News;
import my.project.feed.news.service.HtmlParserService;
import my.project.feed.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for handling home-related operations.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    private final HtmlParserService htmlParserService;
    private final NewsService newsService;

    @Autowired
    public HomeController(HtmlParserService htmlParserService, NewsService newsService) {
        this.htmlParserService = htmlParserService;
        this.newsService = newsService;
    }

    /**
     * Handles the GET request for the home page.
     *
     * @return the name of the home page template
     */
    @GetMapping("/")
    public String homePage() {
        //Returns the name of the home page template to be rendered.
        return "index";
    }

    /**
     * Handles the POST request to fetch news HTML with pagination.
     *
     * @param currentPage the current page number
     * @param pageSize    the number of news items per page
     * @return a map containing the news HTML, current page number, and total number of pages
     */
    @PostMapping("/api/news/get-html-pagination")
    @ResponseBody
    public Map<String, String> getNewsHtmlWithPagination(
            @RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        // Retrieve a paginated list of news sorted by publication date
        Page<News> newsPage = newsService.getNewsSortedByPublicationDate(currentPage, pageSize);
        // Create a map to store the response data
        Map<String, String> response = new HashMap<>();

        // Generate the news HTML by parsing the "news-view" fragment template with the newsPage data
        response.put("newsHtml", htmlParserService.parseHtml("fragments/news-view", "newsPage", newsPage));
        // Add the current page number and total number of pages to the response
        response.put("currentPage", String.valueOf(newsPage.getNumber() + 1));
        response.put("totalPages", String.valueOf(newsPage.getTotalPages()));

        // Returns the response containing the news HTML, current page number, and total number of pages.
        return response;
    }

}
