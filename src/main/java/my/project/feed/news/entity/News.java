package my.project.feed.news.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News {
    /**
     * Unique identifier of the news
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The title of the news.
     */
    @NotEmpty(message = "Title should not be empty")
    @Column(name = "title")
    private String title;

    /**
     * The publication date and time of the news.
     */
    @Column(name = "publication_date_time")
    private LocalDateTime publicationDateTime;

    /**
     * Body text of the news
     */
    @NotEmpty(message = "Body should not be empty")
    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    /**
     * Image path of the news
     */
    @Column(name = "image")
    private String image;

    /**
     * Flag indicating the presence of an image
     */
    @Column(name = "is_image_view")
    private boolean isImageView;

    /**
     * Default constructor for News
     */
    public News() {
    }

    /**
     * Constructor for creating a News object with specified parameters.
     *
     * @param title               The title of the news.
     * @param publicationDateTime The publication date and time of the news.
     * @param body                The body text of the news.
     * @param image               The image path of the news.
     * @param isImageView         Flag indicating the presence of an image.
     */
    public News(String title, LocalDateTime publicationDateTime, String body, String image, boolean isImageView) {
        this.title = title;
        this.publicationDateTime = publicationDateTime;
        this.body = body;
        this.image = image;
        this.isImageView = isImageView;
    }

    /**
     * Get the identifier of the news
     *
     * @return The identifier of the news
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the identifier of the news
     *
     * @param id The identifier of the news
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the title of the news.
     *
     * @return The title of the news
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the news.
     *
     * @param title The title of the news
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the publication date and time of the news.
     *
     * @return The publication date and time.
     */
    public LocalDateTime getPublicationDateTime() {
        return publicationDateTime;
    }

    /**
     * Sets the publication date and time of the news.
     *
     * @param publicationDateTime The publication date and time to set.
     */
    public void setPublicationDateTime(LocalDateTime publicationDateTime) {
        this.publicationDateTime = publicationDateTime;
    }

    /**
     * Get the body text of the news
     *
     * @return The body text of the news
     */
    public String getBody() {
        return body;
    }

    /**
     * Set the body text of the news
     *
     * @param body The body text of the news
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Get the image path of the news
     *
     * @return The image path of the news
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the image path of the news
     *
     * @param image The image path of the news
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Check if the news has an image
     *
     * @return true if the news has an image; false otherwise
     */
    public boolean isImageView() {
        return isImageView;
    }

    /**
     * Set the flag indicating the presence of an image
     *
     * @param imageView Flag indicating the presence of an image
     */
    public void setImageView(boolean imageView) {
        this.isImageView = imageView;
    }

    /**
     * Returns a string representation of the News object.
     *
     * @return The string representation of the News object.
     */
    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationDateTime=" + publicationDateTime +
                ", body='" + body + '\'' +
                ", image='" + image + '\'' +
                ", isImageView=" + isImageView +
                '}';
    }
}
