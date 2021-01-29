package ua.com.conductor.service;

import java.time.LocalDate;
import java.util.List;
import ua.com.conductor.model.MovieSession;

public interface MovieSessionService extends GenericService<MovieSession, Long> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
