package ua.com.conductor.dao.impl;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.conductor.dao.MovieSessionDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.model.MovieSession;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> getAllSessionsDateQuery =
                    session.createQuery("SELECT m FROM MovieSession m "
                    + "LEFT JOIN FETCH m.cinemaHall LEFT JOIN FETCH m.movie"
                            + " WHERE m.movie.id = :id_movie "
                            + "AND DATE_FORMAT(m.showTime, '%Y-%m-%d') = :date ",
                            MovieSession.class);
            getAllSessionsDateQuery.setParameter("id_movie", movieId);
            getAllSessionsDateQuery.setParameter("date",
                    date.toString());
            return getAllSessionsDateQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movie sessions at "
                    + date.toString(), e);
        }
    }
}
