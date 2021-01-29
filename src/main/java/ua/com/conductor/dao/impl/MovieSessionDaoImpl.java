package ua.com.conductor.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.com.conductor.dao.MovieSessionDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.lib.Dao;
import ua.com.conductor.model.MovieSession;
import ua.com.conductor.util.HibernateUtil;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie session " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> getAllSessionsQuery =
                    session.createQuery("SELECT m FROM MovieSession m "
                    + " JOIN FETCH m.cinemaHall JOIN FETCH m.movie", MovieSession.class);
            return getAllSessionsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movie sessions ", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> getAllSessionsDateQuery =
                    session.createQuery("SELECT m FROM MovieSession m "
                    + " JOIN FETCH m.cinemaHall JOIN FETCH m.movie"
                            + " WHERE m.showTime >= :start AND m.showTime < :end ",
                            MovieSession.class);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            getAllSessionsDateQuery.setParameter("start", start);
            getAllSessionsDateQuery.setParameter("end", end);
            return getAllSessionsDateQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movie sessions at "
                    + date.toString(), e);
        }
    }
}
