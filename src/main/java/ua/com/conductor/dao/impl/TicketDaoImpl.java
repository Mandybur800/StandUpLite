package ua.com.conductor.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.conductor.dao.TicketDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.lib.Dao;
import ua.com.conductor.model.Ticket;
import ua.com.conductor.util.HibernateUtil;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ticket " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
