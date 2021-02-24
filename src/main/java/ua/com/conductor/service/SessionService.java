package ua.com.conductor.service;

import java.time.LocalDate;
import java.util.List;
import ua.com.conductor.model.Session;

public interface SessionService {
    Session add(Session session);

    List<Session> findAvailableSessions(Long eventId, LocalDate date);

    Session update(Session session);

    void delete(Long id);

    Session get(Long id);
}
