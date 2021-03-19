package dao;

import models.Topic;
import models.User;

import java.util.List;

public interface TopicDAO {

    User findByName(String name);

    void save(Topic topic);

    void update(Topic topic);

    void delete(Topic topic);

    User findUserById(int id);

    List<Topic> findAll();
}
