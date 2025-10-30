package mk.finki.ukim.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class BookReservation {
    private Book book;
    private String readerName;
    private String readerAddress;
    private Long numberOfCopies;
}
