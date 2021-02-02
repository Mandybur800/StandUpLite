package ua.com.conductor.dao.impl;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.com.conductor.dao.UserDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.lib.Dao;
import ua.com.conductor.model.User;
import ua.com.conductor.util.HibernateUtil;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> getByEmailQuery = session.createQuery("FROM User WHERE email = :email",
                    User.class);
            getByEmailQuery.setParameter("email", email);
            return getByEmailQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user by email:" + email, e);
        }
    }
}
