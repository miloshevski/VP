package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.BookReservation;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository{
    @Override
    public BookReservation save(BookReservation reservation) {
        DataHolder.reservations.removeIf(r -> r == reservation);

        DataHolder.reservations.add(reservation);

        return reservation;
    }
}
