package dao;

import models.File;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.TypedQuery;
import java.util.List;

public class FileDAOImpl implements FileDAO {

    @Override
    public void save(File file) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(file);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(File file) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(file);
        tx1.commit();
        session.close();
    }

    @Override
    public List<File> findAllFilesInMessage(int no) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypedQuery<File> query = session.createQuery(
                "SELECT f FROM File f WHERE f.message_no = :no"
        ).setParameter("no", no);
        return query.getResultList();
    }
}
