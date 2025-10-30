package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.BookReservation;

import java.util.List;

public interface BookReservationService {
    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies);

    List<BookReservation> findAll();
}
