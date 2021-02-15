package ua.com.conductor.dao;

import java.util.List;
import java.util.Optional;
import ua.com.conductor.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();

    Optional<Movie> get(Long id);
}
