package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "topics")
public class Topic {

    //@OneToMany(mappedBy = Message)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topic_no;

    private String topic_name;
    private String section_name;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    public Topic() {
    }

    public Topic(String topic, String section) {
        topic_name = topic;
        section_name = section;
        messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        message.setTopic(this);
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    public int getTopicNo() {
        return topic_no;
    }

    public String getTopicName() {
        return topic_name;
    }


    public void setTopicName(String name) {
        topic_name = name;
    }

    public String getSectionName() {
        return section_name;
    }

    public int getSectionNo() {
        return topic_no;
    }

    public void setSectionName(String name) {
        section_name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> mess) {
        messages = mess;
    }
}
