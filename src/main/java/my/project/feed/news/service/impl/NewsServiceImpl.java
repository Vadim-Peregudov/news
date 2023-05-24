package my.project.feed.news.service.impl;

import my.project.feed.news.entity.News;
import my.project.feed.news.repository.NewsRepository;
import my.project.feed.news.service.FileService;
import my.project.feed.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This class implements the NewsService interface to provide the functionality for managing news-related operations.
 */
@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final FileService fileService;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, FileService fileService) {
        this.newsRepository = newsRepository;
        this.fileService = fileService;
    }

    /**
     * Retrieves a sorted page of news articles based on their publication date.
     *
     * @param currentPage the current page number
     * @param pageSize    the number of news articles per page
     * @return the sorted page of news articles
     */
    @Override
    public Page<News> getNewsSortedByPublicationDate(int currentPage, int pageSize) {
        // Create a PageRequest object to specify the current page, page size, and sorting criteria
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize, Sort.by("publicationDateTime").descending());
        return newsRepository.findAll(pageRequest);
    }

    /**
     * Saves a new news article along with its associated image file.
     *
     * @param news      the news article to save
     * @param imageFile the image file associated with the news article
     * @return true if the news article is successfully saved, false otherwise
     * @throws IOException if an error occurs while saving the image file
     */
    @Override
    public boolean saveNews(News news, MultipartFile imageFile) throws IOException {
        // Check if the image file is not empty and is a valid image file
        if (!imageFile.isEmpty() && isImageFile(imageFile)) {
            news.setImage(fileService.saveImage(imageFile));
            news.setImageView(true);
        } else {
            news.setImageView(false);
        }
        // Set the publication date and time to the current date and time
        news.setPublicationDateTime(LocalDateTime.now());
        // Save the news article in the repository
        newsRepository.save(news);
        return true;
    }

    /**
     * Checks if the given file is an image file based on its content type.
     *
     * @param file the file to check
     * @return true if the file is an image file, false otherwise
     */
    private boolean isImageFile(MultipartFile file) {
        // Retrieve the content type of the file
        String contentType = file.getContentType();
        // Check if the content type is not null and starts with "image/"
        return contentType != null && contentType.startsWith("image/");
    }

}
