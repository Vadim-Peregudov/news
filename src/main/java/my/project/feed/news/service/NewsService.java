package my.project.feed.news.service;

import my.project.feed.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This interface defines the contract for managing news-related operations.
 * It provides methods to retrieve, create, update, and delete news articles.
 */
public interface NewsService {
    /**
     * Retrieves a sorted page of news articles based on their publication date.
     *
     * @param currentPage the current page number
     * @param pageSize    the number of news articles per page
     * @return the sorted page of news articles
     */
    Page<News> getNewsSortedByPublicationDate(int currentPage, int pageSize);

    /**
     * Saves a new news article along with its associated image file.
     *
     * @param news      the news article to save
     * @param imageFile the image file associated with the news article
     * @return true if the news article is successfully saved, false otherwise
     * @throws IOException if an error occurs while saving the image file
     */
    boolean saveNews(News news, MultipartFile imageFile) throws IOException;
}
