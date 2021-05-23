package integratedTests;

import models.Message;
import models.Topic;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.MessageService;
import services.TopicService;
import services.UserService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class integratedTest {
    protected final String appURL = "http://localhost:8080/";
    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws SQLException, IOException {
        final String firefoxDriverPath = "/usr/bin/geckodriver";

        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 767));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        // Init database. Some tests require that the base is not empty
//        PrepareDatabase.initEmptyDB();
//        PrepareDatabase.fillDB();
    }

    void assertUserIsEqualToWebInfo(User user, String infoText){
        Assert.assertTrue(infoText.contains(user.getRights()));
        Assert.assertTrue(infoText.contains(user.getUserLogin()));
        Assert.assertTrue(infoText.contains(user.getRegDate().toString()));
    }

    @Test()
    public void userRegisterLoginSaveTopicMessageTest() {
        Date date = new Date(System.currentTimeMillis());
        User user = new User("testUser", "12345678", date, "Пользователь", null);

        driver.get(appURL);  // go to the root url
        Assert.assertEquals(driver.getTitle(), "Разделы");

        driver.findElement(By.id("register")).click();
        driver.findElement(By.id("login")).sendKeys(user.getUserLogin());
        driver.findElement(By.id("password")).sendKeys(user.getUserPassword());
        driver.findElement(By.id("submit_button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getTitle(), "Пользователь");

        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("login")).sendKeys(user.getUserLogin());
        driver.findElement(By.id("password")).sendKeys(user.getUserPassword());
        driver.findElement(By.id("submit_button")).click();
        Assert.assertEquals(driver.getTitle(), "Пользователь");

        String userInfoText = driver.findElement(By.id("user_info")).getText();
        assertUserIsEqualToWebInfo(user, userInfoText);

        driver.findElement(By.id("users")).click();
        Assert.assertEquals(driver.getTitle(), "Пользователи");

        String tableText = driver.findElement(By.id("users_table")).getText();
        Assert.assertTrue(tableText.contains(user.getUserLogin()));

        driver.findElement(By.id("home")).click();
        Assert.assertEquals(driver.getTitle(), "Разделы");

        driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/a")).click();
        Assert.assertEquals(driver.getTitle(), "Темы");
        driver.findElement(By.id("new_topic")).sendKeys("Тестовая тема");
        driver.findElement(By.id("submit_button")).click();
        Assert.assertEquals(driver.getTitle(), "Темы");
        String tableSectionText = driver.findElement(By.id("topics_table")).getText();
        Assert.assertTrue(tableSectionText.contains("Тестовая тема"));

        Message message = new Message(1, "Тестовое сообщение", user.getUserLogin(), new Timestamp(System.currentTimeMillis()));
        driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/a")).click();
        Assert.assertEquals(driver.getTitle(), "Сообщения");
        driver.findElement(By.id("new_message")).sendKeys(message.getMessageText());
        driver.findElement(By.id("submit_button")).click();
        Assert.assertEquals(driver.getTitle(), "Сообщения");
        String tableTopicText = driver.findElement(By.id("messages_table")).getText();
        Assert.assertTrue(tableTopicText.contains(message.getMessageText()));


        UserService userService = new UserService();
        TopicService topicService = new TopicService();
        MessageService messageService = new MessageService();
        messageService.deleteMessage(message);
        topicService.deleteTopic(topicService.findByName("Тестовая тема"));
        userService.deleteUser(user);
    }


    @AfterClass
    public void clear() {
        driver.quit();
    }
}
