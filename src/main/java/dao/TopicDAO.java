package dao;

import models.Topic;
import models.User;

import java.util.List;

public interface TopicDAO {

    Topic findByName(String name);

    void save(Topic topic);

    void update(Topic topic);

    void delete(Topic topic);

    //User findUserByLogin(String login);

    List<Topic> findAll();

    List<Topic> findAllTopicsInSection(String name);

}
