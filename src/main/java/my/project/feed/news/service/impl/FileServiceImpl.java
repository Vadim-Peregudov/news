package my.project.feed.news.service.impl;

import my.project.feed.news.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Implementation of the FileService interface for handling file operations.
 * Manages the saving of image files to a specified upload directory.
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${upload.dir.image}")
    private String uploadDir;

    /**
     * Saves the provided image file to the upload directory.
     *
     * @param imageFile the image file to be saved
     * @return the generated unique file name
     * @throws IOException if an I/O error occurs during the file saving process
     */
    @Override
    public String saveImage(MultipartFile imageFile) throws IOException {
        // Define the upload path using the provided upload directory
        Path uploadPath = Path.of(uploadDir);
        // Create the upload directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        // Generate a unique file name for the image file
        String fileName = generateUniqueFileName(imageFile);
        // Transfer the image file to the upload directory
        imageFile.transferTo(Path.of(uploadDir + File.separator + fileName));
        // Returns the generated unique file name for the saved image
        return fileName;
    }

    /**
     * Generates a unique file name for the provided file by appending a UUID to its original file name.
     *
     * @param file the file for which to generate a unique name
     * @return the unique file name
     */
    private String generateUniqueFileName(MultipartFile file) {
        // Extract the original file name from the provided file
        String originalFileName = file.getOriginalFilename();
        // Extract the file extension from the original file name
        String extension = StringUtils.getFilenameExtension(originalFileName);
        // Generate a random UUID as the unique part of the file name
        String uniqueFileName = UUID.randomUUID().toString();
        // Combine the unique part with the file extension
        return uniqueFileName + "." + extension;
    }

}