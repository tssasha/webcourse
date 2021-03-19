package dao;

import models.Topic;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class TopicDAOImpl implements TopicDAO {

    @Override
    public User findByName(String name) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            TypedQuery<User> query = session.createQuery(
                    "SELECT t FROM topics t WHERE t.topic_name = :name"
            ).setParameter("name", name);
            return query.getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }

    @Override
    public void save(Topic topic) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(topic);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Topic topic) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(topic);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Topic topic) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(topic);
        tx1.commit();
        session.close();
    }

    @Override
    public User findUserById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public List<Topic> findAll() {
        List<Topic> topics = (List<Topic>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Topic").list();
        return topics;
    }
}

