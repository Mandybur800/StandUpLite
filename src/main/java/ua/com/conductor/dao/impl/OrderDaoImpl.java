package ua.com.conductor.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.com.conductor.dao.OrderDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.lib.Dao;
import ua.com.conductor.model.Order;
import ua.com.conductor.model.User;
import ua.com.conductor.util.HibernateUtil;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert order " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> getCartQuery =
                    session.createQuery("SELECT o FROM Order o"
                            + " LEFT JOIN FETCH o.tickets t"
                            + " LEFT JOIN FETCH t.movieSession m"
                            + " LEFT JOIN FETCH m.movie"
                            + " LEFT JOIN FETCH m.cinemaHall"
                            + " WHERE o.user = :user", Order.class);
            getCartQuery.setParameter("user", user);
            return getCartQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get orders for "
                    + user, e);
        }
    }
}
