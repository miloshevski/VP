package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void init() {

        Author a1 = new Author(1L, "George", "Orwell", "UK",
                "Author of 1984 and Animal Farm.");
        Author a2 = new Author(2L, "J.R.R.", "Tolkien", "UK",
                "Author of The Hobbit and LOTR.");
        Author a3 = new Author(3L, "Fyodor", "Dostoevsky", "Russia",
                "Author of Crime and Punishment.");

        authors.add(a1);
        authors.add(a2);
        authors.add(a3);

        books.add(new Book(1L, "1984", "Dystopian", 4.8, a1));
        books.add(new Book(2L, "The Hobbit", "Fantasy", 4.7, a2));
        books.add(new Book(3L, "Crime and Punishment", "Classic", 4.9, a3));

        reservations = new ArrayList<>();
    }
}
