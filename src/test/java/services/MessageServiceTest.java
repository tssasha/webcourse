package services;

import models.Message;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class MessageServiceTest {
    @Test
    public void testSaveMessage() {
        MessageService messageService = new MessageService();
        Message new_message = new Message(11, "Про отладку программ", "Новое сообщение", "InvalidCode", java.sql.Timestamp.valueOf("2021-03-23 14:34:00"));
        messageService.saveMessage(new_message);

        List<Message> check_message = messageService.findAllMessagesInTopic("Про отладку программ");
        Assert.assertEquals(new_message.getMessageNo(), check_message.get(check_message.size() - 1).getMessageNo());

        messageService.deleteMessage(new_message);
    }

    @Test
    public void testUpdateMessage() {
        MessageService messageService = new MessageService();
        Message new_message = new Message(11, "Про отладку программ", "Новое сообщение", "InvalidCode", java.sql.Timestamp.valueOf("2021-03-23 14:34:00"));
        messageService.saveMessage(new_message);

        new_message.setMessageText("Измененное сообщение");
        messageService.updateMessage(new_message);

        List<Message> check_message = messageService.findAllMessagesInTopic("Про отладку программ");
        Assert.assertEquals(new_message.getMessageNo(), check_message.get(check_message.size() - 1).getMessageNo());

        messageService.deleteMessage(new_message);
    }

    @Test
    public void testDeleteMessage() {
        MessageService messageService = new MessageService();
        Message new_message = new Message(11, "Про отладку программ", "Новое сообщение", "InvalidCode", java.sql.Timestamp.valueOf("2021-03-23 14:34:00"));
        messageService.saveMessage(new_message);

        List<Message> check_message1 = messageService.findAllMessagesInTopic("Про отладку программ");
        messageService.deleteMessage(new_message);

        List<Message> check_message2 = messageService.findAllMessagesInTopic("Про отладку программ");
        Assert.assertEquals(check_message1.size() - 1, check_message2.size());
    }

    @Test
    public void testFindAllMessagesInTopic() {
        MessageService messageService = new MessageService();
        Set<Message> expected_list = Set.of(new Message(8, "Прозрачные окна в JAVA", "Вот случайно наткнулся в нете на такие статейки, " +
                "ранее тоже натыкался , но там реализация слабовата была, а тут все красиво и культурно)) так что вот ссылки может кому пригодиться " +
                "1. http://www.pushing-pixels.org/?p=260 2. http://weblogs.java.net/blog/j... e_fad.html", "lifestyle", java.sql.Timestamp.valueOf("2010-07-06 01:03:00")),
                new Message(9, "Прозрачные окна в JAVA", "Интерсно-интересно. Так это родной параметр? Без сторонних библиотек, значит?" +
                        "Мда.. действительно работает. " +
                        "Прикольно.. " +
                        "Я у себя подсказки всплывающие так сделал теперь.", "InvalidCode", java.sql.Timestamp.valueOf("2016-01-14 23:05:00")),
                new Message(10, "Прозрачные окна в JAVA", "И без этого можно - Component.setTooltipText()", "Programming", java.sql.Timestamp.valueOf("2017-06-11 12:13:00")));

        List<Message> check_messages = messageService.findAllMessagesInTopic("Прозрачные окна в JAVA");
        Assert.assertEquals(check_messages.size(), expected_list.size());
        //Assert.assertTrue(expected_list.contains(check_messages.get(0)));
        //Assert.assertTrue(expected_list.contains(check_messages.get(1)));
        //Assert.assertTrue(expected_list.contains(check_messages.get(2)));
    }
}
