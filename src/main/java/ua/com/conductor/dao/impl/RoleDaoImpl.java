package ua.com.conductor.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.conductor.dao.RoleDao;
import ua.com.conductor.exception.DataProcessingException;
import ua.com.conductor.model.Role;
import ua.com.conductor.model.Roles;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new role " + role.getRoleName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Role WHERE roleName = :roleName", Role.class)
                    .setParameter("roleName", Roles.valueOf(roleName))
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Role:" + roleName, e);
        }
    }
}
