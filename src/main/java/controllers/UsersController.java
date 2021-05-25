package controllers;

import models.Topic;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

@Controller
public class UsersController {
    UserService userService = new UserService();

    @GetMapping("/users")
    String users(Model model,
                 @CookieValue(value = "username", defaultValue = "") String username) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("username", username);
        return "users";
    }

    @GetMapping("/user")
    String user(@RequestParam(name="user", required=true) String login, Model model,
                @CookieValue(value = "username", defaultValue = "") String username) {
        User user = userService.findUser(login);
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        return "user";
    }

    @GetMapping("/user_edit")
    String user_edit(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        model.addAttribute("username", username);
        return "user_edit";
    }

    @PostMapping("/userEdit")
    String userEdit(@RequestParam(name="username", required=true) String username,
                    @RequestParam(name="information", required=true) String information) {
        User user = userService.findUser(username);
        user.setDescription(information);
        userService.updateUser(user);
        return String.format("redirect:/user?user=%s", username);
    }

    @GetMapping("/user/new")
    String getSignUp(@RequestParam(name="er", required=false) String er, Model model) {
        model.addAttribute("type", "Регистрация");
        if (er != null) {
            if (er.equals("1")) {
                model.addAttribute("error", "Пользователь с таким логином уже существует.");
            }
            if (er.equals("2")) {
                model.addAttribute("error", "Поле логина не может быть пустым.");
            }
            if (er.equals("3")) {
                model.addAttribute("error", "Пароль должен состоять не менее чем из восьми символов.");
            }
        }
        return "user_form";
    }

    @PostMapping("/user/new")
    String signUp(@RequestParam("login") String login,
                  @RequestParam("password") String password) {
        User user = userService.findUser(login);
        if (user != null) {
            return "redirect:/user/new?er=1";
        }
        if (login.length() == 0 || password.length() == 0) {
            return "redirect:/user/new?er=2";
        }
        if (password.length() < 8) {
            return "redirect:/user/new?er=3";
        }
        Date date = new Date(System.currentTimeMillis());
        userService.saveUser(new User(login, password, date, "Пользователь", null));
        return String.format("redirect:/user?user=%s", login);
    }

    @GetMapping("/user/login")
    String getLogIn(@RequestParam(name="er", required=false) String er, Model model) {
        model.addAttribute("type", "Вход");
        if (er != null && er.equals("1")) {
            model.addAttribute("error", "Пользователя с таким логином и паролем не существует.");
        }
        return "user_form";
    }

    @PostMapping("/user/login")
    String logIn(@RequestParam("login") String login,
                 @RequestParam("password") String password,
                 HttpServletResponse response) {
        User user = userService.findUser(login);
        if (user == null || !user.getUserPassword().equals(password)) {
            return "redirect:/user/login?er=1";
        }
        Cookie cookie = new Cookie("username", login);
        cookie.setPath("/");
        response.addCookie(cookie);
        return String.format("redirect:/user?user=%s", login);
    }

    @GetMapping("/user/logout")
    String getLogOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}
