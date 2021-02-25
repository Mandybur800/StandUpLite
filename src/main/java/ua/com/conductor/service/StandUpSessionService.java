package ua.com.conductor.service;

import java.time.LocalDate;
import java.util.List;
import ua.com.conductor.model.StandUpSession;

public interface StandUpSessionService {
    StandUpSession add(StandUpSession standUpSession);

    List<StandUpSession> findAvailableSessions(Long eventId, LocalDate date);

    StandUpSession update(StandUpSession standUpSession);

    void delete(Long id);

    StandUpSession get(Long id);
}
