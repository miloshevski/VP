package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.repository.AuthorRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {

        // 1) најди author
        Author author = authorRepository.findAll()
                .stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("AuthorNotFound"));

        // 2) генерирај нов ID
        Long newId = DataHolder.books.stream()
                .map(Book::getId)
                .max(Comparator.naturalOrder())
                .orElse(0L) + 1;

        // 3) создади книга
        Book book = new Book(newId, title, genre, averageRating, author);

        // 4) зачувај во DataHolder
        DataHolder.books.add(book);

        return book;
    }
    @Override
    public Book edit(Long id, String title, String genre, Double averageRating, Long authorId) {
        // 1) најди ја постоечката книга
        Book book = DataHolder.books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("BookNotFound"));

        // 2) најди го новиот author
        Author author = authorRepository.findAll()
                .stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("AuthorNotFound"));

        // 3) ажурирај полиња
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return book;
    }
    @Override
    public void deleteById(Long id) {
        DataHolder.books.removeIf(b -> b.getId().equals(id));
    }
    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
