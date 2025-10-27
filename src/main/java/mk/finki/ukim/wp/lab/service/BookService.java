package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
}
