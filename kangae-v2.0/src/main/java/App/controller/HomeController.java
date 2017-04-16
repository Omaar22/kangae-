package App.controller;

import App.model.User;
import App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(Model model) {
        if (userService.getLoggedInUser() == null) { // not logged in
            return "/home";
        } else {
            model.addAttribute("user", userService.getLoggedInUser());
            return "/dashboard";
        }
    }

}
