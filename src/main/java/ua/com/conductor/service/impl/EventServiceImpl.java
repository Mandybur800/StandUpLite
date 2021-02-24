package ua.com.conductor.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.conductor.dao.EventDao;
import ua.com.conductor.model.Event;
import ua.com.conductor.service.EventService;

@Service
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;

    @Autowired
    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Event add(Event event) {
        return eventDao.add(event);
    }

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }

    @Override
    public Event get(Long id) {
        return eventDao.get(id).get();
    }
}
