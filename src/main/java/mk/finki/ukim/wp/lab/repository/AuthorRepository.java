package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> findAll();
    void likeAuthor(Long id); // нов метод
}
