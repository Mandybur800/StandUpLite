package ua.com.conductor.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import ua.com.conductor.model.StandUpSession;

public interface StandUpSessionDao {
    StandUpSession add(StandUpSession standUpSession);

    List<StandUpSession> findAvailableSessions(Long eventId, LocalDate date);

    StandUpSession update(StandUpSession standUpSession);

    void delete(Long id);

    Optional<StandUpSession> get(Long id);
}
