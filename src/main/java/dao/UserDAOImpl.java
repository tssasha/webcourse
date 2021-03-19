package dao;

import models.Topic;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findByLogin(String login) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            TypedQuery<User> query = session.createQuery(
                    "SELECT u FROM users u WHERE u.user_login = :login"
            ).setParameter("login", login);
            return query.getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }

    @Override
    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public Topic findTopicById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Topic.class, id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }
}