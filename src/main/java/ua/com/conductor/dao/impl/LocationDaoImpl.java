package ua.com.conductor.dao.impl;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.conductor.dao.LocationDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.model.Location;

@Repository
public class LocationDaoImpl implements LocationDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public LocationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location add(Location location) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
            return location;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert new location " + location, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Location> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Location> getAllHallsQuery = session.createQuery("FROM Location",
                    Location.class);
            return getAllHallsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all locations ", e);
        }
    }

    @Override
    public Optional<Location> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Location.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get location by id: " + id, e);
        }
    }
}
