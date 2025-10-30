package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookReservation;

import java.util.List;

public interface BookReservationRepository {
    BookReservation save(BookReservation reservation);

    List<BookReservation> findAll();

}
