package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor_Id(Long authorId);

    List<Book> findByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(
            String text,
            Double rating
    );

    List<Book> findByTitleContainingIgnoreCase(String text);
}
