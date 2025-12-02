package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.BookReservation;

public interface BookReservationService {

    BookReservation placeReservation(Long bookId,
                                     String readerName,
                                     String readerAddress,
                                     Long numberOfCopies);
}
