package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookReservation;
import mk.finki.ukim.wp.lab.service.BookReservationService;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookService bookService;

    public BookReservationServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public BookReservation placeReservation(Long bookId,
                                            String readerName,
                                            String readerAddress,
                                            Long numberOfCopies) {

        // 1) најди ја книгата по ID
        Book book = bookService.findById(bookId);
        if (book == null) {
            throw new RuntimeException("BookNotFound");
        }

        // 2) креирај BookReservation со TITLE (според твојата класа)
        BookReservation reservation = new BookReservation(
                book.getTitle(),
                readerName,
                readerAddress,
                numberOfCopies
        );

        // 3) зачувај ја резервацијата in-memory
        DataHolder.reservations.add(reservation);

        return reservation;
    }
}
