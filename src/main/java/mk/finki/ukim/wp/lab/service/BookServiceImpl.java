package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.repository.AuthorRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

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
        // Apply filters independently:
        // - if text is null/empty -> ignore title filter
        // - if rating is null -> ignore rating filter
        return bookRepository.findAll()
                .stream()
                .filter(book ->
                        text == null
                                || text.isEmpty()
                                || book.getTitle().toLowerCase()
                                .contains(text.toLowerCase())
                )
                .filter(book ->
                        rating == null
                                || book.getAverageRating() >= rating
                )
                .toList();
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {

        // 1) најди author од база
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("AuthorNotFound"));

        // 2) создади книга (ID НЕ го сетираш – @GeneratedValue ќе го направи тоа)
        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        // 3) зачувај во база
        return bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String title, String genre, Double averageRating, Long authorId) {
        // 1) најди ја постоечката книга од база
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookNotFound"));

        // 2) најди го новиот author од база
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("AuthorNotFound"));

        // 3) ажурирај полиња
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        // 4) зачувај промени
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
