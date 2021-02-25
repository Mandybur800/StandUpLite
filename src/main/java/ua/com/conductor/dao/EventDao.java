package ua.com.conductor.dao;

import java.util.List;
import java.util.Optional;
import ua.com.conductor.model.Event;

public interface EventDao {
    Event add(Event event);

    List<Event> getAll();

    Optional<Event> get(Long id);
}
