package ua.com.conductor.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.conductor.dao.SessionDao;
import ua.com.conductor.model.Session;
import ua.com.conductor.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionDao sessionDao;

    @Autowired
    public SessionServiceImpl(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public Session add(Session session) {
        return sessionDao.add(session);
    }

    @Override
    public List<Session> findAvailableSessions(Long eventId, LocalDate date) {
        return sessionDao.findAvailableSessions(eventId, date);
    }

    @Override
    public Session update(Session session) {
        return sessionDao.update(session);
    }

    @Override
    public void delete(Long id) {
        sessionDao.delete(id);
    }

    @Override
    public Session get(Long id) {
        return sessionDao.get(id).get();
    }
}
