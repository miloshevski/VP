package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookReservation;
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

        // 1) најди ја книгата од база
        Book book = bookService.findById(bookId);
        if (book == null) {
            throw new RuntimeException("BookNotFound");
        }

        // 2) креирај BookReservation (не се перзистира, само се враќа)
        return new BookReservation(
                book.getTitle(),
                readerName,
                readerAddress,
                numberOfCopies
        );
    }
}
