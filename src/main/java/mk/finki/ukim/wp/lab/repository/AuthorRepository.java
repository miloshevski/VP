package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Author;

import java.util.List;

public interface AuthorRepository {
    public List<Author> findAll();
}
