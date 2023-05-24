package my.project.feed.news.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Global error controller that handles custom error scenarios across multiple controllers.
 * The class is annotated with @ControllerAdvice to define global exception handlers.
 */
@ControllerAdvice
public class CustomErrorController {
    @Value("${spring.servlet.multipart.max-file-size}")
    private String allowedMaxFileSize;

    /**
     * Exception handler for MaxUploadSizeExceededException.
     * Handles the case when the maximum file size allowed for upload is exceeded.
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeExceededException(
            MaxUploadSizeExceededException ex,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        // Get the HTTP status code for a Bad Request (400)
        int statusCode = HttpStatus.BAD_REQUEST.value();
        // Get the size of the uploaded file from the request
        long uploadedFileSize = request.getContentLengthLong();
        // Create an error message with information about the exceeded file size
        String errorMessage = "File size exceeded the maximum limit.Maximum file size: " + allowedMaxFileSize + ". Your file size: " + uploadedFileSize + " bytes.";
        // Set the error response details in the HttpServletResponse and Model objects
        setErrorResponse(statusCode, errorMessage, response, model);
        // Return the view name for the error page
        return "error-page";
    }

    /**
     * Exception handler for NoHandlerFoundException.
     * Handles the case when no handler is found for the requested URL.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpServletResponse response,
            Model model) {
        // Get the HTTP status code for a Not Found (404) error
        int statusCode = HttpStatus.NOT_FOUND.value();
        // Create an error message indicating the requested page was not found
        String errorMessage = "Page " + ex.getRequestURL() + " not found.";
        // Set the error response details in the HttpServletResponse and Model objects
        setErrorResponse(statusCode, errorMessage, response, model);
        // Return the view name for the error page
        return "error-page";
    }

    /**
     * Generic exception handler for Throwable.
     * Handles any other unexpected exception.
     */
    @ExceptionHandler(Throwable.class)
    public String handlerError(Throwable ex, HttpServletResponse response, Model model) {
        // Get the HTTP status code for an Internal Server Error (500)
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        // Get the error message from the Throwable object
        String errorMessage = ex.getMessage();
        // Set the error response details in the HttpServletResponse and Model objects
        setErrorResponse(statusCode, errorMessage, response, model);
        // Return the view name for the error page
        return "error-page";
    }

    /**
     * Sets the error response details in the HttpServletResponse and Model objects.
     */
    private void setErrorResponse(int statusCode, String errorMessage, HttpServletResponse response, Model model) {
        // Set the HTTP response status code
        response.setStatus(statusCode);
        // Add the status code as an attribute to the Model object
        model.addAttribute("statusCode", statusCode);
        // Add the error message as an attribute to the Model object
        model.addAttribute("errorMessage", errorMessage);
    }
}
