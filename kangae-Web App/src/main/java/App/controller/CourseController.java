package App.controller;

import App.model.Course;
import App.model.Game;
import App.model.Teacher;
import App.service.CourseService;
import App.service.GameService;
import App.service.NotificationService;
import App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CourseController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseservice;
    @Autowired
    private GameService gameService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("user", userService.getLoggedInUser());
        model.addAttribute("courses", courseservice.getALLCourses());
        model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
        return "/courses";
    }

    @RequestMapping(value = "/course/create")
    public String addCourse(Model model) {
        if (userService.isLoggedIn() && userService.getLoggedInUser() instanceof Teacher) { // authorized
            if (!model.containsAttribute("course"))
                model.addAttribute("course", new Course());
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            return "/create_course";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/create")
    public String addCourse(@ModelAttribute(value = "course") Course course, Model model) {
        if (!courseservice.isNewName(course)) {
            model.addAttribute("errorMessage", "Name already taken.");
            return addCourse(model);
        } else {
            course.setTeacher((Teacher) userService.getLoggedInUser());
            courseservice.addCourse(course);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "course/{courseName}")
    public String showCourse(@PathVariable String courseName, Model model) {
        Course course = courseservice.getCourse(courseName);
        ArrayList<Game> games = gameService.getGamesInCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("user", userService.getLoggedInUser());
        model.addAttribute("games", games);
        model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
        return "show_games_in_course";
    }
}


