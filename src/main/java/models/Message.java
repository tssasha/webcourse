package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int message_no;

    private int topic_no;
    private String message;
    private String user_login;
    private java.sql.Timestamp time_stamp;

//    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<File> files;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_login", insertable=false, updatable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_no", insertable=false, updatable=false)
    private Topic topic;

    public Message() {
    }

    public Message(int no, String mess, String login, java.sql.Timestamp time) {
        //message_no = no;
        topic_no = no;
        message = mess;
        user_login = login;
        time_stamp = time;
//        files = new ArrayList<>();
    }

    public void addFile(File file) {
        file.setMessage(this);
//        files.add(file);
    }

//    public void removeFile(File file) {
//        files.remove(file);
//    }

    public int getMessageNo() {
        return message_no;
    }

    public int getTopicName() {
        return topic_no;
    }
    public void setTopicName(int no) {
        topic_no = no;
    }

    public String getMessageText() {
        return message;
    }
    public void setMessageText(String mess) {
        message = mess;
    }

    public String getUserLogin() {
        return user_login;
    }
    public void setUserLogin(String login) {
        user_login = login;
    }

    public java.sql.Timestamp getTimeStamp() {
        return time_stamp;
    }
    public void setTimeStamp(java.sql.Timestamp time) {
        time_stamp = time;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user1) {
        user = user1;
    }

    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic1) {
        topic = topic1;
    }

//    public List<File> getFiles() {
//        return files;
//    }
//
//    public void setFiles(List<File> files1) {
//        files = files1;
//    }

}
