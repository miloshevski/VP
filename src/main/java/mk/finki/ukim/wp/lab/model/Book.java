package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String genre;
    private double averageRating;
    private Author author;

}
