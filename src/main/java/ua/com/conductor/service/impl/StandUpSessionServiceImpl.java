package ua.com.conductor.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.conductor.dao.StandUpSessionDao;
import ua.com.conductor.model.StandUpSession;
import ua.com.conductor.service.StandUpSessionService;

@Service
public class StandUpSessionServiceImpl implements StandUpSessionService {
    private final StandUpSessionDao standUpSessionDao;

    @Autowired
    public StandUpSessionServiceImpl(StandUpSessionDao standUpSessionDao) {
        this.standUpSessionDao = standUpSessionDao;
    }

    @Override
    public StandUpSession add(StandUpSession standUpSession) {
        return standUpSessionDao.add(standUpSession);
    }

    @Override
    public List<StandUpSession> findAvailableSessions(Long eventId, LocalDate date) {
        return standUpSessionDao.findAvailableSessions(eventId, date);
    }

    @Override
    public StandUpSession update(StandUpSession standUpSession) {
        return standUpSessionDao.update(standUpSession);
    }

    @Override
    public void delete(Long id) {
        standUpSessionDao.delete(id);
    }

    @Override
    public StandUpSession get(Long id) {
        return standUpSessionDao.get(id).get();
    }
}
