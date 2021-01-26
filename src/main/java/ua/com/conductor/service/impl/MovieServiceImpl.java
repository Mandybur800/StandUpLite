package ua.com.conductor.service.impl;

import java.util.List;
import ua.com.conductor.dao.MovieDao;
import ua.com.conductor.lib.Inject;
import ua.com.conductor.lib.Service;
import ua.com.conductor.model.Movie;
import ua.com.conductor.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
