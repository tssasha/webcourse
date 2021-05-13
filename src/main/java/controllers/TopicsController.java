package controllers;

import models.Message;
import models.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.MessageService;
import services.TopicService;

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
    String section(@RequestParam(name="section", required=true) String section, Model model,
                   @CookieValue(value = "username", defaultValue = "") String username) {
        model.addAttribute("section", section);
        List<Topic> topics = topicService.findAllTopicsInSection(section);
        model.addAttribute("topics", topics);
        model.addAttribute("username", username);
        return "section";
    }

    @GetMapping("/topic")
    String topic(@RequestParam(name="topic", required=true) String topic, Model model,
                 @CookieValue(value = "username", defaultValue = "") String username) {
        model.addAttribute("topic", topic);
        List<Message> messages = messageService.findAllMessagesInTopic(topic);
        model.addAttribute("messages", messages);
        model.addAttribute("username", username);
        return "topic";
    }

}
