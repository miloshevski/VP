package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    void likeAuthor(Long id); // нов метод
}
