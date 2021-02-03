package ua.com.conductor.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.com.conductor.dao.ShoppingCartDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.lib.Dao;
import ua.com.conductor.model.ShoppingCart;
import ua.com.conductor.model.User;
import ua.com.conductor.util.HibernateUtil;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
            session = HibernateUtil.getSessionFactory().openSession();
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
