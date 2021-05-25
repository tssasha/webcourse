package dao;

import models.File;
import models.Message;
import models.Topic;
import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    @Override
    public void save(Message message) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(message);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Message message) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(message);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Message message) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(message);
        tx1.commit();
        session.close();
    }

//    @Override
//    public User findUserByLogin(String login) {
//        try {
//            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//            TypedQuery<User> query = session.createQuery(
//                    "SELECT u FROM users u WHERE u.user_login = :login"
//            ).setParameter("login", login);
//            return query.getSingleResult();
//        } catch(NoResultException ex) {
//            return null;
//        }
//    }
//
//    @Override
//    public Topic findTopicByName(String name) {
//        try {
//            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//            TypedQuery<Topic> query = session.createQuery(
//                    "SELECT t FROM topics t WHERE t.topic_name = :name"
//            ).setParameter("name", name);
//            return query.getSingleResult();
//        } catch(NoResultException ex) {
//            return null;
//        }
//    }
//
//    @Override
//    public File findFileById(int id) {
//        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(File.class, id);
//    }

    @Override
    public List<Message> findAllMessagesInTopic(int no) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypedQuery<Message> query = session.createQuery(
                "SELECT m FROM Message m WHERE m.topic_no = :no"
        ).setParameter("no", no);
        List<Message> messages = query.getResultList();
        session.close();
        return messages;
    }

    @Override
    public Message findByNo(int no) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypedQuery<Message> query = session.createQuery(
                "SELECT m FROM Message m WHERE m.message_no = :no"
        ).setParameter("no", no);
        Message message = query.getSingleResult();
        session.close();
        return message;
    }
}
