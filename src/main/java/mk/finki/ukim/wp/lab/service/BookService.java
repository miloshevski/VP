package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Optional<Book> findByTitle(String title);
}
