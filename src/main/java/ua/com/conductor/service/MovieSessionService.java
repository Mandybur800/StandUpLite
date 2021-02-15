package ua.com.conductor.service;

import java.time.LocalDate;
import java.util.List;
import ua.com.conductor.model.MovieSession;

public interface MovieSessionService {
    MovieSession add(MovieSession session);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession update(MovieSession movieSession);

    void delete(Long id);

    MovieSession get(Long id);
}
