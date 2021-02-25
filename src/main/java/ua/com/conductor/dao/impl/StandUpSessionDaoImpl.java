package ua.com.conductor.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.conductor.dao.StandUpSessionDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.model.StandUpSession;

@Repository
public class StandUpSessionDaoImpl implements StandUpSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public StandUpSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public StandUpSession add(StandUpSession standUpSessionEvent) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(standUpSessionEvent);
            transaction.commit();
            return standUpSessionEvent;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert session " + standUpSessionEvent, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<StandUpSession> findAvailableSessions(Long eventId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<StandUpSession> getAllSessionsDateQuery =
                    session.createQuery("SELECT s FROM StandUpSession s "
                    + "LEFT JOIN FETCH s.location LEFT JOIN FETCH s.event"
                            + " WHERE s.event.id = :id "
                            + "AND DATE_FORMAT(s.showTime, '%Y-%m-%d') = :date ",
                            StandUpSession.class);
            getAllSessionsDateQuery.setParameter("id", eventId);
            getAllSessionsDateQuery.setParameter("date",
                    date.toString());
            return getAllSessionsDateQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all sessions at "
                    + date.toString(), e);
        }
    }

    @Override
    public StandUpSession update(StandUpSession movieStandUpSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(movieStandUpSession);
            transaction.commit();
            return movieStandUpSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update session " + movieStandUpSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            StandUpSession loadedStandUpSession = session.load(StandUpSession.class, id);
            session.delete(loadedStandUpSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete session " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<StandUpSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from StandUpSession s "
                    + "left join fetch s.location "
                    + "left join fetch s.event "
                    + "where s.id = :id", StandUpSession.class)
                    .setParameter("id", id).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get session by id: " + id, e);
        }
    }
}
