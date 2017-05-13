package App.controller;

import App.model.*;
import App.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/test/Hi")
    public String get(@ModelAttribute(value = "game") Game game) {
        return game.getAnswer();
    }

    @RequestMapping("/test/show/{courseName}")
    public Course show(@PathVariable String courseName) {
        return courseService.getCourse(courseName);
    }

    @RequestMapping("/test/notification")
    public ArrayList<Notification> getNotifications() {
        User user = userService.getLoggedInUser();
        return notificationService.getAllNotificationsForUser(user);
    }
    @RequestMapping("/test/notification/unread")
    public ArrayList<Notification> getUnreadNotifications() {
        User user = userService.getLoggedInUser();
        return notificationService.getUnreadNotificationsForUser(user);
    }

}
