package ua.com.conductor.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import ua.com.conductor.model.Session;

public interface SessionDao {
    Session add(Session session);

    List<Session> findAvailableSessions(Long eventId, LocalDate date);

    Session update(Session session);

    void delete(Long id);

    Optional<Session> get(Long id);
}
