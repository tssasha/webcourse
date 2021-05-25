package dao;

import models.Message;
import models.Topic;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TopicDAOImpl implements TopicDAO {

    @Override
    public Topic findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypedQuery<Topic> query = session.createQuery(
                "SELECT t FROM Topic t WHERE t.topic_name = :name"
        ).setParameter("name", name);
        Topic topic = query.getSingleResult();
        session.close();
        return topic;
    }

    @Override
    public Topic findByNo(int no) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypedQuery<Topic> query = session.createQuery(
                "SELECT t FROM Topic t WHERE t.topic_no = :no"
        ).setParameter("no", no);
        Topic topic = query.getSingleResult();
        session.close();
        return topic;
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

    @Override
    public List<Topic> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Topic> topics = session.createQuery("From Topic").list();
        session.close();
        return topics;
    }

    @Override
    public List<Topic> findAllSections() {
        Set<String> ssections = new HashSet<>();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Topic> topics = (List<Topic>)  session.createQuery("From Topic").list();
        List<Topic> sections = new ArrayList<>();
        for (Topic topic : topics) {
            if (!ssections.contains(topic.getSectionName())) {
                ssections.add(topic.getSectionName());
                sections.add(topic);
            }
        }
        session.close();
        return sections;
    }

    @Override
    public List<Topic> findAllTopicsInSection(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypedQuery<Topic> query = session.createQuery(
                "SELECT t FROM Topic t WHERE t.section_name = :name"
        ).setParameter("name", name);;
        List<Topic> topics = query.getResultList();
        session.close();
        return topics;
    }
}

