package controllers;

import models.Message;
import models.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.MessageService;
import services.TopicService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TopicsController {
    TopicService topicService = new TopicService();
    MessageService messageService = new MessageService();

    @GetMapping("/")
    String index(Model model,
                 @CookieValue(value = "username", defaultValue = "") String username) {
        List<Topic> sections = topicService.findAllSections();
        List<Integer> topics_count = new ArrayList<>();
        for (Topic section : sections) {
            topics_count.add(topicService.findAllTopicsInSection(section.getSectionName()).size());
        }
        model.addAttribute("sections", sections);
        model.addAttribute("username", username);
        model.addAttribute("topics_count", topics_count);
        return "home";
    }

    @GetMapping("/section")
    String section(@RequestParam(name="section", required=true) int section_no, Model model,
                   @CookieValue(value = "username", defaultValue = "") String username) {
        String section = topicService.findByNo(section_no).getSectionName();
        model.addAttribute("section", section);
        model.addAttribute("section_no", section_no);
        List<Topic> topics = topicService.findAllTopicsInSection(section);
        model.addAttribute("topics", topics);
        model.addAttribute("username", username);
        return "section";
    }

    @PostMapping("/newTopic")
    String postTopic(@RequestParam(name="section_no") int section_no,
                       @RequestParam(name="text", required=false) String text,
                       @CookieValue(value = "username", defaultValue = "") String username) {
        if (text == null || text.length() == 0) {
            return String.format("redirect:/section?section=%d", section_no);
        }
        String section = topicService.findByNo(section_no).getSectionName();
        Topic topic = new Topic(text, section);
        topicService.saveTopic(topic);
        return String.format("redirect:/section?section=%d", section_no);
    }

    @GetMapping("/topic")
    String topic(@RequestParam(name="topic", required=true) int topic_no, Model model,
                 @CookieValue(value = "username", defaultValue = "") String username) {
        String topic = topicService.findByNo(topic_no).getTopicName();
        model.addAttribute("topic", topic);
        model.addAttribute("topic_no", topic_no);
        List<Message> messages = messageService.findAllMessagesInTopic(topic_no);
        model.addAttribute("messages", messages);
        model.addAttribute("username", username);
        return "topic";
    }

    @PostMapping("/newMessage")
    String postMessage(@RequestParam(name="topic_no") int topic_no,
                       @RequestParam(name="text", required=false) String text,
                       @CookieValue(value = "username", defaultValue = "") String username) {
        if (text == null || text.length() == 0) {
            return String.format("redirect:/topic?topic=%d", topic_no);
        }
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Message message = new Message(topic_no, text, username, date);
        messageService.saveMessage(message);
        return String.format("redirect:/topic?topic=%d", topic_no);
    }

//    @PostMapping("/delMessage")
//    String delMessage(@RequestParam(name="topic_no") int topic_no,
//                      @RequestParam(name="message_no") int message_no) {
//        System.out.println("---------------------------------------");
//        Message message = messageService.findByNo(message_no);
//        System.out.println(message.getMessageNo());
//        messageService.deleteMessage(message);
//        return String.format("redirect:/topic?topic=%d", topic_no);
//    }

}
