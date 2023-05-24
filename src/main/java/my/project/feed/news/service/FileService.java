package my.project.feed.news.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This interface defines the contract for managing file-related operations, such as saving images.
 */
public interface FileService {
    /**
     * Saves the provided image file and returns the generated filename.
     *
     * @param file the image file to be saved
     * @return the generated filename of the saved image
     * @throws IOException if an error occurs while saving the image file
     */
    String saveImage(MultipartFile file) throws IOException;
}
