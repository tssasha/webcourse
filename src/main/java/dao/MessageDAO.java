package dao;

import models.File;
import models.Message;
import models.Topic;
import models.User;

import java.util.List;

public interface MessageDAO {

    void save(Message message);

    void update(Message message);

    void delete(Message message);

//    User findUserByLogin(String login);
//
//    Topic findTopicByName(String name);
//
//    File findFileById(int id);

    List<Message> findAllMessagesInTopic(String name);
}
