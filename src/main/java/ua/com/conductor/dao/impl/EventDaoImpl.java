package ua.com.conductor.dao.impl;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.conductor.dao.EventDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.model.Event;

@Repository
public class EventDaoImpl implements EventDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public EventDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Event add(Event event) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();
            return event;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert event " + event, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Event> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Event> getAllMoviesQuery = session.createQuery("FROM Event", Event.class);
            return getAllMoviesQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all events", e);
        }
    }

    @Override
    public Optional<Event> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Event.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get event by id: " + id, e);
        }
    }
}
