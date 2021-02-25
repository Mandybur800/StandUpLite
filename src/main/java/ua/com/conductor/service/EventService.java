package ua.com.conductor.service;

import java.util.List;
import ua.com.conductor.model.Event;

public interface EventService {
    Event add(Event event);

    List<Event> getAll();

    Event get(Long id);
}
