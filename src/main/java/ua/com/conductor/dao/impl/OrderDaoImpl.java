package ua.com.conductor.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.conductor.dao.OrderDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.model.Order;
import ua.com.conductor.model.User;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
        try (Session session = sessionFactory.openSession()) {
            Query<Order> getCartQuery =
                    session.createQuery("SELECT DISTINCT o FROM Order o"
                            + " LEFT JOIN FETCH o.tickets t"
                            + " LEFT JOIN FETCH t.session s"
                            + " LEFT JOIN FETCH s.event"
                            + " LEFT JOIN FETCH s.location"
                            + " WHERE o.user = :user", Order.class);
            getCartQuery.setParameter("user", user);
            return getCartQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get orders for "
                    + user, e);
        }
    }
}
