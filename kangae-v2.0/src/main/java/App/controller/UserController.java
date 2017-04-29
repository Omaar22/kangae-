package App.controller;

import App.model.Course;
import App.model.Game;
import App.service.CourseService;
import App.service.GameService;
import App.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GameService gameService;

    @RequestMapping("/Hi")
    public String get(@ModelAttribute(value = "game") Game game) {
        return game.getAnswer();
    }

    @RequestMapping("/show/{courseName}")
    public Course show(@PathVariable String courseName) {
        return courseService.getCourse(courseName);
    }

}
