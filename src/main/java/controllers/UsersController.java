package controllers;

import models.Topic;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.UserService;

import java.util.List;

@Controller
public class UsersController {
    UserService userService = new UserService();

    @GetMapping("/users")
    String users(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user")
    String section(@RequestParam(name="user", required=true) String login, Model model) {
        User user = userService.findUser(login);
        model.addAttribute("user", user);
        return "user";
    }
}
