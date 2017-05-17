package App.controller;

import App.model.Notification;
import App.model.Teacher;
import App.model.User;
import App.service.CourseService;
import App.service.GameService;
import App.service.NotificationService;
import App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    GameService gameService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/")
    public String home(Model model) {
        if (!userService.isLoggedIn()) {
            return "/home";
        } else {
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            return "/dashboard";
        }
    }

    @RequestMapping("/notifications")
    public String notifications(Model model) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userService.getLoggedInUser());
            ArrayList<Notification> unread = notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser());
            ArrayList<Notification> notifications = notificationService.getAllNotificationsForUser(userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", unread.size());
            if (unread.size() != 0) {
                model.addAttribute("notifications", unread);
                notificationService.setAllRead(userService.getLoggedInUser());

            } else {
                model.addAttribute("notifications", notifications);
            }
            return "/notifications";
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
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            return "/profile";
        }
    }


}
