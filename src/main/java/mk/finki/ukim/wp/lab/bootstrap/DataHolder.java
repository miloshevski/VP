package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;

    @PostConstruct
    public void init(){
        books = new ArrayList<>();
        reservations = new ArrayList<>();

        books.add(new Book("Dune", "Sci-Fi", 9.5));
        books.add(new Book("Neuromancer", "Cyberpunk", 8.9));
        books.add(new Book("The Hobbit", "Fantasy", 9.2));
        books.add(new Book("Foundation", "Sci-Fi", 8.7));
        books.add(new Book("1984", "Dystopian", 9.1));
    }
}
