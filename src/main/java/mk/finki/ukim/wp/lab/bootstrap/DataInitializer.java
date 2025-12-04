package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.repository.AuthorRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository,
                           BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {

        // 1) Create authors
        Author a1 = new Author( "George", "Orwell", "UK", "Author of 1984");
        Author a2 = new Author("J.R.R.", "Tolkien", "UK", "Author of LOTR");
        Author a3 = new Author("Fyodor", "Dostoevsky", "Russia", "Crime & Punishment");

        a1 = authorRepository.save(a1);
        a2 = authorRepository.save(a2);
        a3 = authorRepository.save(a3);

        // 2) Create books with JPA
        Book b1 = new Book(null, "1984", "Dystopian", 4.8, a1);
        Book b2 = new Book(null, "The Hobbit", "Fantasy", 4.7, a2);
        Book b3 = new Book(null, "Crime and Punishment", "Classic", 4.9, a3);

        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);
    }
}
