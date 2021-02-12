package ua.com.conductor.service;

import java.time.LocalDate;
import java.util.List;
import ua.com.conductor.model.MovieSession;

public interface MovieSessionService {
    MovieSession add(MovieSession session);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession update(MovieSession movieSession);

    MovieSession delete(MovieSession movieSession);

    MovieSession get(Long id);
}
