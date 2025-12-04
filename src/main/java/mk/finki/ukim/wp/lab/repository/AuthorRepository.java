package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
