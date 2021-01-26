package ua.com.conductor.service;

import java.util.List;
import ua.com.conductor.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
