package controllers;

import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.TopicService;
import services.UserService;

@Controller
public class HelloController {
    UserService userService = new UserService();
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @PostMapping("/superSecureLogin")
    public String superSecureLoginPage(@RequestParam(name="login", required=true) String login,
                                       @RequestParam(name="password", required=true) String password, Model model) {
        User user = userService.findUser(login);
        if ((user != null) && (password.equals(user.getUserPassword()))) {
            return  String.format("redirect:/hello?name=%s", user.getUserLogin());
        }
        return "superSecureLogin";
    }

    @GetMapping("/superSecureLogin")
    public String superSecureLoginPage() {
        return "superSecureLogin";
    }
}