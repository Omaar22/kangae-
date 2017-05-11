package App.controller;

import App.model.Game;
import App.model.Student;
import App.service.CourseService;
import App.service.GameService;
import App.service.StudentService;
import App.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
    public String createGame(@PathVariable String courseName, @ModelAttribute(value = "game") Game game, Model model) {
        game.setCourse(courseService.getCourse(courseName));
        if (game.getCourse() == null || !userService.isLoggedIn()
                || !game.getCourse().getTeacher().getEmail().equals(userService.getLoggedInUser().getEmail())) {
            return "redirect:/";
        } else {
            model.addAttribute("game", game);
            return "/create_game_in_course";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/create/game")
    public String addGame(@ModelAttribute(value = "game") Game game, @PathVariable String courseName, Model model) {
        if (gameservice.isValid(game)) {
            game.setCourse(courseService.getCourse(courseName)); // for some reason this MUST be done again!
            gameservice.addGame(game);
            return "redirect:/course/" + courseName;
        } else {
            model.addAttribute("errorMessage", "Name is already taken.");
            return createGame(courseName, game, model);
        }
    }

    @RequestMapping(value = "/course/{courseName}/{gameName}")
    public String playGame(@PathVariable String courseName, @PathVariable String gameName, Model model) {
        Game game = gameservice.getGameInCourse(courseName, gameName);
        if (game == null) {
            return "redirect:/";
        } else {
            game.setAnswer("");
            model.addAttribute("game", game);
            return "/play_game";
        }
    }



    @RequestMapping(value = "/course/{courseName}/{gameName}/options")
    public String playandeditGame(@PathVariable String courseName, @PathVariable String gameName, Model model) {
        Game game = gameservice.getGameInCourse(courseName, gameName);
        if (game == null) {
            return "redirect:/";
        } else {
            model.addAttribute("game",game);
            return "/play_edit_game";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/course/{courseName}/updategame/{gameName}")
    public String update(@PathVariable String courseName, @PathVariable String gameName, Model model) {
        Game game = gameservice.getGameInCourse(courseName, gameName);

        if( userService.isLoggedIn() && game.getCourse().getTeacher().getEmail().equals(userService.getLoggedInUser().getEmail())){
            model.addAttribute("game", game);
            System.out.println(game.getId());
            return "/update_game"; //has form
        }

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/updategame/{gameName}")
    public String update (@ModelAttribute(value = "game") Game game, @PathVariable String courseName, @PathVariable String gameName, Model model) {

        Game oldGame = gameservice.getGameInCourse(courseName, gameName);

        if (gameservice.isValid(game)) {

            oldGame.setName(game.getName());
            oldGame.setDescription(game.getDescription());
            oldGame.setInstruction(game.getInstruction());
            oldGame.setAnswer(game.getAnswer());
            oldGame.setQuestion(game.getQuestion());
            System.out.println(oldGame.getId());
            gameservice.addGame(oldGame);

            return "redirect:/course/" + courseName;
        } else {
            model.addAttribute("errorMessage", "Name is already taken.");
            return createGame(courseName, game, model);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/{gameName}")
    public String judge(@ModelAttribute(value = "game") Game game, Model model,
                        @PathVariable("courseName") String courseName, @PathVariable("gameName") String gameName) {
        Game originalGame = gameservice.getGameInCourse(courseName, gameName);
        model.addAttribute("game", originalGame);
        if (game.getAnswer().equals(originalGame.getAnswer())) {
            // todo: only increment if not played before
            if (userService.getLoggedInUser() instanceof Student) {
                studentService.incrementScore(userService.getLoggedInUser().getId());
                ((Student) userService.getLoggedInUser()).incrementScore();
            }
            return "/accepted";
        } else {
            return "/wrong_answer";
        }
    }

    @RequestMapping (value = "/course/{courseName}/copy/game")
    public String copyGame(Model model){
        if (!userService.isLoggedIn() || userService.getLoggedInUser() instanceof Student) {
            return "redirect:/";
        } else {
            ArrayList<Game> games = gameservice.getALLGame();
            model.addAttribute("games", games);
            model.addAttribute("user", userService.getLoggedInUser());
            return "/show_games";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/course/{courseName}/{gameId}/copy")
    public String copy(Model model, @PathVariable("courseName") String courseName, @PathVariable ("gameId") int gameId){


    return "";
    }


}
