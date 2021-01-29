package ua.com.conductor.dao.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> getAllSessionsDateQuery =
                    session.createQuery("SELECT m FROM MovieSession m "
                    + "LEFT JOIN FETCH m.cinemaHall LEFT JOIN FETCH m.movie"
                            + " WHERE m.movie.id = :id_movie "
                            + "AND DATE_FORMAT(m.showTime, '%Y-%m-%d') = :date ",
                            MovieSession.class);
            getAllSessionsDateQuery.setParameter("id_movie", movieId);
            getAllSessionsDateQuery.setParameter("date",
                    DateTimeFormatter.ISO_LOCAL_DATE.format(date));
            return getAllSessionsDateQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movie sessions at "
                    + date.toString(), e);
        }
    }
}
