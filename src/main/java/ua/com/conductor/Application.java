package ua.com.conductor;

import ua.com.conductor.lib.Injector;
import ua.com.conductor.model.Movie;
import ua.com.conductor.service.MovieService;

public class Application {
    private static Injector injector = Injector.getInstance("ua.com.conductor");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
