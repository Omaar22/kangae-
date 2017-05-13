package App.controller;

import App.model.Comment;
import App.model.Course;
import App.model.Game;
import App.model.User;
import App.service.CourseService;
import App.service.GameService;
import App.service.NotificationService;
import App.service.StudentService;
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
    private NotificationService notificationService;

    @RequestMapping("/Hi")
    public String get(@ModelAttribute(value = "game") Game game) {
        return game.getAnswer();
    }

    @RequestMapping("/show/{courseName}")
    public Course show(@PathVariable String courseName) {
        return courseService.getCourse(courseName);
    }

    @RequestMapping("/user/{userName}/notification")
    public String getNotfications(@ModelAttribute(value = "userName") User user, @ModelAttribute(value = "gameName") Game game , @ModelAttribute(value = "commentContent") Comment comment){
        ArrayList<Comment> comments = notificationService.getComments(comment.getId());
        ArrayList<User> users = notificationService.getUsers(user.getId());
        return "/notification";
    }

}
