package dao;

import models.File;
import models.Message;
import models.Topic;
import models.User;

public interface MessageDAO {
    Message findByNo(int no);

    void save(Message message);

    void update(Message message);

    void delete(Message message);

    User findUserById(int id);

    Topic findTopicById(int id);

    File findFileById(int id);
}
