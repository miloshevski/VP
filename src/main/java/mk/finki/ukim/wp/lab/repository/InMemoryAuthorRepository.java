package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryAuthorRepository implements AuthorRepository{
    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }
    @Override
    public void likeAuthor(Long id) {
        DataHolder.authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .ifPresent(Author::addLike);
    }

}
