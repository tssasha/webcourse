package services;

import dao.MessageDAO;
import dao.MessageDAOImpl;
import models.Message;
import models.Topic;

import java.util.List;

public class MessageService {

    private MessageDAO messagesDao = new MessageDAOImpl();

    public void saveMessage(Message message) {
        messagesDao.save(message);
    }

    public void updateMessage(Message message) {
        messagesDao.update(message);
    }

    public void deleteMessage(Message message) {
        messagesDao.delete(message);
    }

    public List<Message> findAllMessagesInTopic(int no) {
        return messagesDao.findAllMessagesInTopic(no);
    }

    public Message findByNo(int no) {
        return messagesDao.findByNo(no);
    }
}
