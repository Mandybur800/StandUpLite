package ua.com.conductor.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.conductor.dao.ShoppingCartDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.model.ShoppingCart;
import ua.com.conductor.model.User;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert shopping cart " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> getCartQuery =
                    session.createQuery("SELECT s FROM ShoppingCart s"
                                    + " LEFT JOIN FETCH s.tickets t"
                                    + " LEFT JOIN FETCH t.movieSession m"
                                    + " LEFT JOIN FETCH m.movie"
                                    + " LEFT JOIN FETCH m.cinemaHall"
                                    + " WHERE s.user = :user", ShoppingCart.class);
            getCartQuery.setParameter("user", user);
            return getCartQuery.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get shopping cart for "
                    + user, e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shopping cart " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
