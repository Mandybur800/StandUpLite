package ua.com.conductor.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.conductor.dao.SessionDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.model.Session;

@Repository
public class SessionDaoImpl implements SessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public SessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session add(Session sessionEvent) {
        Transaction transaction = null;
        org.hibernate.Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(sessionEvent);
            transaction.commit();
            return sessionEvent;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert session " + sessionEvent, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Session> findAvailableSessions(Long eventId, LocalDate date) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            Query<Session> getAllSessionsDateQuery =
                    session.createQuery("SELECT s FROM Session s "
                    + "LEFT JOIN FETCH s.location LEFT JOIN FETCH s.event"
                            + " WHERE s.event.id = :id "
                            + "AND DATE_FORMAT(s.showTime, '%Y-%m-%d') = :date ",
                            Session.class);
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
    public Session update(Session movieSession) {
        Transaction transaction = null;
        org.hibernate.Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update session " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        org.hibernate.Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Session loadedSession = session.load(Session.class, id);
            session.delete(loadedSession);
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
    public Optional<Session> get(Long id) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            return session.createQuery("from Session s "
                    + "left join fetch s.location "
                    + "left join fetch s.event "
                    + "where s.id = :id", Session.class)
                    .setParameter("id", id).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get session by id: " + id, e);
        }
    }
}
