package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String user_login;
    private String user_password;
    private java.sql.Date reg_date;
    private String rights;
    private String description;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    public User() {
    }

    public User(String login, String password, java.sql.Date date, String right, String desc) {
        user_login = login;
        user_password = password;
        reg_date = date;
        rights = right;
        description = desc;
        messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        message.setUser(this);
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }
    
    public String getUserLogin() {
        return user_login;
    }
    public void setUserLogin(String login) {
        user_login = login;
    }

    public String getUserPassword() {
        return user_password;
    }
    public void setUserPassword(String password) {
        user_password = password;
    }

    public java.sql.Date getRegDate() {
        return reg_date;
    }
    public void setRegDate(java.sql.Date date) {
        reg_date = date;
    }

    public String getRights() {
        return rights;
    }
    public void setRights(String right) {
        rights = right;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) {
        description = desc;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> mess) {
        messages = mess;
    }
}
