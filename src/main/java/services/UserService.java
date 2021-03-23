package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import models.User;

import java.util.List;

public class UserService {

    private UserDAO usersDao = new UserDAOImpl();

    public User findUser(String login) { return usersDao.findByLogin(login); }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    List<User> findAllUsers() {
        return usersDao.findAll();
    }

}
