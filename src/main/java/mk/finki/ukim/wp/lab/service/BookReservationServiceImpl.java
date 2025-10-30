package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookReservation;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import mk.finki.ukim.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReservationServiceImpl implements BookReservationService{

    private final BookReservationRepository bookReservationRepository;
    private final BookRepository bookRepository;
    public BookReservationServiceImpl(BookReservationRepository bookReservationRepository, BookRepository bookRepository) {
        this.bookReservationRepository = bookReservationRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        Book book = bookRepository.findByTitle(bookTitle)
                .orElseThrow(() -> new RuntimeException("Book not found: " + bookTitle));

        BookReservation reservation = new BookReservation(book, readerName, readerAddress, (long) numberOfCopies);
        this.bookReservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public List<BookReservation> findAll() {
        return bookReservationRepository.findAll();
    }
}
