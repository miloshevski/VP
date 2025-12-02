package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.BookReservation;

public interface BookReservationRepository {
    BookReservation save(BookReservation reservation);
}
