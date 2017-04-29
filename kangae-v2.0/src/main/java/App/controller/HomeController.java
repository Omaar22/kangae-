package App.controller;

import App.model.Teacher;
import App.model.User;
import App.service.CourseService;
import App.service.GameService;
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
    @Autowired
    CourseService courseService;
    @Autowired
    GameService gameService;

    @RequestMapping("/")
    public String home(Model model) {
        if (!userService.isLoggedIn()) {
            return "/home";
        } else {
            model.addAttribute("user", userService.getLoggedInUser());
            return "/dashboard";
        }
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("courseCount", courseService.getCoursesByTeacher(userService.getLoggedInUser().getEmail()).size());
            model.addAttribute("gamesCount", gameService.getGamesByTeacherEmail(userService.getLoggedInUser().getEmail()).size());
            return "/profile";
        }
    }


}
