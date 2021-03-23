package services;

import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class UserServiceTest {

    @Test
    public void testFindUser() {
        UserService UserService = new UserService();

        User check_User = UserService.findUser("Aviz__");
        Assert.assertEquals(check_User.getRegDate(), java.sql.Date.valueOf("2014-02-17"));
    }

    @Test
    public void testSaveUser() {
        UserService UserService = new UserService();
        User new_User = new User("user1", "qwerty123", java.sql.Date.valueOf("2010-07-06"), "Пользователь", "Описание");
        UserService.saveUser(new_User);

        User check_User = UserService.findUser(new_User.getUserLogin());
        Assert.assertEquals(new_User.getDescription(), check_User.getDescription());

        UserService.deleteUser(new_User);
    }

    @Test
    public void testDeleteUser() {
        UserService UserService = new UserService();
        User new_User = new User("user1", "qwerty123", java.sql.Date.valueOf("2010-07-06"), "Пользователь", "Описание");
        UserService.saveUser(new_User);

        List<User> check_User1 = UserService.findAllUsers();
        UserService.deleteUser(new_User);

        List<User> check_User2 = UserService.findAllUsers();
        Assert.assertEquals(check_User1.size() - 1, check_User2.size());
    }

    @Test
    public void testUpdateUser() {
        UserService UserService = new UserService();
        User new_User = new User("user1", "qwerty123", java.sql.Date.valueOf("2010-07-06"), "Пользователь", "Описание");
        UserService.saveUser(new_User);

        new_User.setDescription("Измененное описание");
        UserService.updateUser(new_User);

        User check_User = UserService.findUser(new_User.getUserLogin());
        Assert.assertEquals(new_User.getDescription(), check_User.getDescription());

        UserService.deleteUser(new_User);
    }

    @Test
    public void testFindAllUsers() {
        UserService UserService = new UserService();
        Set<String> expected_list = Set.of("Aviz__", "Welemir1", "lifestyle", "InvalidCode", "iSmokeJC", "Programming");

        List<User> check_Users = UserService.findAllUsers();
        Assert.assertEquals(check_Users.size(), expected_list.size());
        Assert.assertTrue(expected_list.contains(check_Users.get(0).getUserLogin()));
        Assert.assertTrue(expected_list.contains(check_Users.get(1).getUserLogin()));
        Assert.assertTrue(expected_list.contains(check_Users.get(2).getUserLogin()));
        Assert.assertTrue(expected_list.contains(check_Users.get(3).getUserLogin()));
        Assert.assertTrue(expected_list.contains(check_Users.get(4).getUserLogin()));
        Assert.assertTrue(expected_list.contains(check_Users.get(5).getUserLogin()));
    }
}
