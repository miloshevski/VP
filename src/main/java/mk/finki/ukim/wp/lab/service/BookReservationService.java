package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.BookReservation;

public interface BookReservationService {
    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies);

}
