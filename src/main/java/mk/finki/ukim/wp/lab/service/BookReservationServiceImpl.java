package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.BookReservation;
import mk.finki.ukim.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService{

    private final BookReservationRepository bookReservationRepository;

    public BookReservationServiceImpl(BookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        BookReservation reservation = new BookReservation(bookTitle, readerName,readerAddress, (long) numberOfCopies);
        this.bookReservationRepository.save(reservation);

        return reservation;
    }
}
