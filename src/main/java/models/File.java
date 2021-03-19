package models;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {

    @Id
    private String file;
    private int message_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_no")
    private Message message;

    public File() {
    }

    public File(String file_path, int no) {
        file = file_path;
        message_no = no;
    }

    public String getFilePath() {
        return file;
    }

    public void setFilePath(String file_path) {
        file = file_path;
    }

    public int getMessageNo() {
        return message_no;
    }

    public void setMessageNo(int no) {
        message_no = no;
    }

    public Message getMessage() {
        return message;
    }
    public void setMessage(Message mess) {
        message = mess;
    }
}
