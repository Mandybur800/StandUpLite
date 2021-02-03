package ua.com.conductor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import ua.com.conductor.exception.AuthenticationException;
import ua.com.conductor.lib.Injector;
import ua.com.conductor.model.CinemaHall;
import ua.com.conductor.model.Movie;
import ua.com.conductor.model.MovieSession;
import ua.com.conductor.model.User;
import ua.com.conductor.service.AuthenticationService;
import ua.com.conductor.service.CinemaHallService;
import ua.com.conductor.service.MovieService;
import ua.com.conductor.service.MovieSessionService;
import ua.com.conductor.service.ShoppingCartService;

public class Application {
    private static Injector injector = Injector.getInstance("ua.com.conductor");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        Movie movieSimon = new Movie();
        movieSimon.setTitle("Love, Simon");
        movieService.add(movieSimon);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHallOne = new CinemaHall();
        CinemaHall cinemaHallTwo = new CinemaHall();
        cinemaHallOne.setCapacity(80);
        cinemaHallTwo.setCapacity(100);
        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHallOne);
        cinemaHallService.add(cinemaHallTwo);
        System.out.println(cinemaHallService.getAll());

        MovieSession movieSessionOne = new MovieSession();
        movieSessionOne.setMovie(movie);
        movieSessionOne.setShowTime(LocalDateTime.now());
        MovieSession movieSessionTwo = new MovieSession();
        movieSessionTwo.setMovie(movie);
        movieSessionTwo.setCinemaHall(cinemaHallTwo);
        movieSessionTwo.setShowTime(LocalDateTime.now());
        MovieSession movieSessionThree = new MovieSession();
        movieSessionThree.setMovie(movieSimon);
        movieSessionThree.setCinemaHall(cinemaHallTwo);
        movieSessionThree.setShowTime(LocalDateTime.of(2021, 1, 20, 20, 0));
        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSessionOne);
        movieSessionService.add(movieSessionTwo);
        movieSessionService.add(movieSessionThree);
        System.out.println();
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        User user = authenticationService.register("example@gmail.com", "TryToDoDoIt");
        try {
            authenticationService.login("example@gmail.com", "TryToDoDoIt");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Incorrect password or email!", e);
        }
        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSessionOne, user);
        shoppingCartService.addSession(movieSessionThree, user);
        System.out.println(shoppingCartService.getByUser(user));
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        System.out.println(shoppingCartService.getByUser(user));
    }
}
