package my.project.feed.news.repository;

import my.project.feed.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing News entities.
 * Provides data access methods for News entity using a Long identifier.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
