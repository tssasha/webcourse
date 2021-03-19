package dao;

import models.File;
import models.Message;
import models.Topic;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class MessageDAOImpl implements MessageDAO {
    @Override
    public Message findByNo(int no) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Message.class, no);
    }

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

    @Override
    public User findUserById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public Topic findTopicById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Topic.class, id);
    }

    @Override
    public File findFileById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(File.class, id);
    }

//    public List<Message> findAll() {
//        List<Message> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
//        return users;
//    }
}
