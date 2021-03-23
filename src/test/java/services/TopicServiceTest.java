package services;

import models.Topic;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class TopicServiceTest {
    @Test
    public void testFindByName() {
        TopicService TopicService = new TopicService();

        Topic check_Topic = TopicService.findByName("Про отладку программ");
        Assert.assertEquals(check_Topic.getSectionName(), "Java для начинающих");
    }

    @Test
    public void testSaveTopic() {
        TopicService TopicService = new TopicService();
        Topic new_Topic = new Topic("Новая тема20", "Новый раздел");
        TopicService.saveTopic(new_Topic);

        List<Topic> check_Topic = TopicService.findAllTopics();
        Assert.assertEquals(new_Topic.getTopicName(), check_Topic.get(check_Topic.size() - 1).getTopicName());

        TopicService.deleteTopic(new_Topic);
    }

    @Test
    public void testUpdateTopic() {
        TopicService TopicService = new TopicService();
        Topic new_Topic = new Topic("Новая тема50", "Новый раздел");
        TopicService.saveTopic(new_Topic);

        new_Topic.setSectionName("Измененный Раздел");
        TopicService.updateTopic(new_Topic);

        List<Topic> check_Topic = TopicService.findAllTopics();
        Assert.assertEquals(new_Topic.getTopicName(), check_Topic.get(check_Topic.size() - 1).getTopicName());

        TopicService.deleteTopic(new_Topic);
    }

    @Test
    public void testDeleteTopic() {
        TopicService TopicService = new TopicService();
        Topic new_Topic = new Topic("Новая тема40", "Новый раздел");
        TopicService.saveTopic(new_Topic);

        List<Topic> check_Topic1 = TopicService.findAllTopics();
        TopicService.deleteTopic(new_Topic);

        List<Topic> check_Topic2 = TopicService.findAllTopics();
        Assert.assertEquals(check_Topic1.size() - 1, check_Topic2.size());
    }

    @Test
    public void testFindAllTopics() {
    }

    @Test
    public void testFindAllTopicsInSection() {
        TopicService TopicService = new TopicService();
        Set<String> expected_list = Set.of("Про отладку программ", "Сравнение строк (String) в java. Мини гайд для начинающих");

        List<Topic> check_Topics = TopicService.findAllTopicsInSection("Java для начинающих");
        Assert.assertEquals(check_Topics.size(), expected_list.size());
        Assert.assertTrue(expected_list.contains(check_Topics.get(0).getTopicName()));
        Assert.assertTrue(expected_list.contains(check_Topics.get(1).getTopicName()));
    }
}
