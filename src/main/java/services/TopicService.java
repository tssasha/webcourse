package services;

import dao.TopicDAO;
import dao.TopicDAOImpl;
import models.Topic;
import models.User;

import java.util.List;

public class TopicService {

    private TopicDAO topicsDao = new TopicDAOImpl();

    public Topic findByName(String name) {
        return topicsDao.findByName(name);
    }

    public Topic findByNo(int no) {
        return topicsDao.findByNo(no);
    }

    public void saveTopic(Topic topic) {
        topicsDao.save(topic);
    }

    public void updateTopic(Topic topic) {
        topicsDao.update(topic);
    }

    public void deleteTopic(Topic topic) {
        topicsDao.delete(topic);
    }

    public List<Topic> findAllTopics() {
        return topicsDao.findAll();
    }

    public List<Topic> findAllSections() {
        return topicsDao.findAllSections();
    }

    public List<Topic> findAllTopicsInSection(String name) {
        return topicsDao.findAllTopicsInSection(name);
    }
}
