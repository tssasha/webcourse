package controllers;

import models.Message;
import models.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.MessageService;
import services.TopicService;

import java.util.List;

@Controller
public class TopicsController {
    TopicService topicService = new TopicService();
    MessageService messageService = new MessageService();

    @GetMapping("/")
    String index(Model model) {
        List<Topic> sections = topicService.findAllSections();
        model.addAttribute("sections", sections);
        return "home";
    }

    @GetMapping("/section")
    String section(@RequestParam(name="section", required=true) String section, Model model) {
        model.addAttribute("section", section);
        List<Topic> topics = topicService.findAllTopicsInSection(section);
        model.addAttribute("topics", topics);
        return "section";
    }

    @GetMapping("/topic")
    String topic(@RequestParam(name="topic", required=true) String topic, Model model) {
        model.addAttribute("topic", topic);
        List<Message> messages = messageService.findAllMessagesInTopic(topic);
        model.addAttribute("messages", messages);
        return "topic";
    }
}
