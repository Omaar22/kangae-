package App.controller;

import App.model.Course;
import App.model.Game;
import App.model.Student;
import App.model.Teacher;
import App.service.CourseService;
import App.service.GameService;
import App.service.StudentService;
import App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class GameController {
    @Autowired
    private GameService gameservice;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/course/{courseName}/create/game")
    public String showCourse(@PathVariable String courseName, Model model) {
        Game game = new Game();
        game.setCourse(courseService.getCourse(courseName));
        if (game.getCourse() == null || game.getCourse().getTeacher().equals(userService.getLoggedInUser())) {
            return "redirect:/"; // todo: return error message
        } else {
            model.addAttribute("game", game);
            return "/create_game_in_course";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/create/game")
    public String addGame(@ModelAttribute(value = "game") Game game, @PathVariable String courseName) {
        // todo: check
        game.setCourse(courseService.getCourse(courseName));
        gameservice.addGame(game);
        return "redirect:/course/" + courseName;
    }

    @RequestMapping(value = "/course/{courseName}/{gameName}")
    public String playGame(@PathVariable String courseName, @PathVariable String gameName, Model model) {
        // todo: check
        Game game = gameservice.getGameInCourse(courseName, gameName);
        game.setAnswer("");
        model.addAttribute("game", game);

        return "/play_game";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/{gameName}")
    public String judge(@ModelAttribute(value = "game") Game game, Model model,
                        @PathVariable("courseName") String courseName, @PathVariable("gameName") String gameName) {
        // todo: check

        Game originalGame = gameservice.getGameInCourse(courseName, gameName);
        model.addAttribute("game", originalGame);
        if (game.getAnswer().equals(originalGame.getAnswer())) {
//             todo: only increment if not played before
            if (userService.getLoggedInUser() instanceof Student) {
                studentService.incrementScore(userService.getLoggedInUser().getId());
                ((Student) userService.getLoggedInUser()).incrementScore();
            }
            return "/accepted";
        } else {
            return "/wrong_answer";
        }
    }

}
