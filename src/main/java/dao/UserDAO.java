package dao;

import models.Topic;
import models.User;

import java.util.List;

public interface UserDAO {
    User findByLogin(String login);

    void save(User user);

    void update(User user);

    void delete(User user);

    Topic findTopicById(int id);

    List<User> findAll();
}
