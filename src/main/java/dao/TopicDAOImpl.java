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
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            TypedQuery<Topic> query = session.createQuery(
                    "SELECT t FROM Topic t WHERE t.topic_name = :name"
            ).setParameter("name", name);
            return query.getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }

    @Override
    public Topic findByNo(int no) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            TypedQuery<Topic> query = session.createQuery(
                    "SELECT t FROM Topic t WHERE t.topic_no = :no"
            ).setParameter("no", no);
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
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(topic);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        List<Topic> topics = (List<Topic>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Topic").list();
        return topics;
    }

    @Override
    public List<Topic> findAllSections() {
        Set<String> ssections = new HashSet<>();
        List<Topic> topics = (List<Topic>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Topic").list();
        List<Topic> sections = new ArrayList<>();
        for (Topic topic : topics) {
            if (!ssections.contains(topic.getSectionName())) {
                ssections.add(topic.getSectionName());
                sections.add(topic);
            }
        }
        return sections;
    }

    @Override
    public List<Topic> findAllTopicsInSection(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypedQuery<Topic> query = session.createQuery(
                "SELECT t FROM Topic t WHERE t.section_name = :name"
        ).setParameter("name", name);;
        return query.getResultList();
    }
}

