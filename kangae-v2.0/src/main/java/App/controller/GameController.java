package App.controller;

import App.model.Comment;
import App.model.Course;
import App.model.Game;
import App.model.Student;
import App.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private CommentService commentService;
    @Autowired
    private NotificationService notificationService;


    @RequestMapping(value = "/course/{courseName}/create/game")
    public String createGame(@PathVariable String courseName, @ModelAttribute(value = "game") Game game, Model model) {
        game.setCourse(courseService.getCourse(courseName));
        if (game.getCourse() == null || !userService.isLoggedIn()
                || !game.getCourse().getTeacher().getEmail().equals(userService.getLoggedInUser().getEmail())) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            model.addAttribute("game", game);
            model.addAttribute("url", "/course/" + courseName + "/create/game");
            model.addAttribute("title", "Create Game");
            return "/create_update_game";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/create/game")
    public String addGame(@ModelAttribute(value = "game") Game game, @PathVariable String courseName, Model model) {
        if (gameservice.isNewName(game)) {
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
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            return "/play_game";
        }
    }

    @RequestMapping(value = "/course/{courseName}/{gameName}/options")
    public String playAndEditGame(@PathVariable String courseName, @PathVariable String gameName, Model model) {
        Game game = gameservice.getGameInCourse(courseName, gameName);
        if (game == null) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            model.addAttribute("game", game);
            model.addAttribute("comments", commentService.getCommentsInGame(gameName));
            model.addAttribute("newComment", new Comment());
            return "/play_edit_game";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/course/{courseName}/update/{gameName}")
    public String update(@PathVariable String courseName, @PathVariable String gameName, Model model) {
        Game game = gameservice.getGameInCourse(courseName, gameName);

        if (userService.isLoggedIn() && game.getCourse().getTeacher().getEmail().equals(userService.getLoggedInUser().getEmail())) {
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            model.addAttribute("game", game);
            model.addAttribute("url", "/course/" + courseName + "/update/" + gameName);
            model.addAttribute("title", "Update Game " + gameName);
            return "/create_update_game";
        }

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/update/{gameName}")
    public String update(@ModelAttribute(value = "game") Game game, @PathVariable String courseName, @PathVariable String gameName, Model model) {
        Game oldGame = gameservice.getGameInCourse(courseName, gameName);
        if (game.getName().equals(oldGame.getName()) || gameservice.isNewName(game)) {
            oldGame.setName(game.getName());
            oldGame.setDescription(game.getDescription())   ;
            oldGame.setInstruction(game.getInstruction());
            oldGame.setAnswer(game.getAnswer());
            oldGame.setQuestion(game.getQuestion());
            gameservice.addGame(oldGame);
            return "redirect:/course/" + courseName;
        } else {
            model.addAttribute("errorMessage", "Name is already taken.");
            return update(courseName, oldGame.getName(), model);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/{gameName}")
    public String judge(@ModelAttribute(value = "game") Game game, Model model,
                        @PathVariable("courseName") String courseName, @PathVariable("gameName") String gameName) {
        Game originalGame = gameservice.getGameInCourse(courseName, gameName);
        model.addAttribute("game", originalGame);
        model.addAttribute("user", userService.getLoggedInUser());
        model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
        if (game.getAnswer().equals(originalGame.getAnswer())) {
            // todo: only increment if not played before
            if (userService.getLoggedInUser() instanceof Student) {
                studentService.incrementScore(userService.getLoggedInUser().getId());
                ((Student) userService.getLoggedInUser()).incrementScore();
            }
            model.addAttribute("verdict", "ACCEPTED");
            model.addAttribute("color", "green");
            model.addAttribute("url", "/course/" + originalGame.getCourse().getName());
            model.addAttribute("next", "Next");
        } else {
            model.addAttribute("verdict", "WRONG!");
            model.addAttribute("color", "red");
            model.addAttribute("url", "/course/" + originalGame.getCourse().getName() + "/" + originalGame.getName());
            model.addAttribute("next", "Try Again");
        }
        return "/verdict";
    }

    @RequestMapping(value = "/course/{targetCourse}/copy/game")
    public String copyGame(@PathVariable("targetCourse") String targetCourse, Model model) {
        if (!userService.isLoggedIn() || userService.getLoggedInUser() instanceof Student) {
            return "redirect:/";
        } else {
            model.addAttribute("games", gameservice.getALLGame());
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            model.addAttribute("targetCourse", targetCourse);
            return "/show_games";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/course/{targetCourse}/{gameName}/copy")
    public String copy(Model model, @PathVariable("targetCourse") String targetCourse, @PathVariable("gameName") String gameName) {
        Game game = gameservice.getGameByName(gameName);
        Course newCourse = courseService.getCourse(targetCourse);
        if (userService.isLoggedIn() && newCourse.getTeacher().getEmail().equals(userService.getLoggedInUser().getEmail())) {
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("unreadNotificationsCount", notificationService.getUnreadNotificationsForUser(userService.getLoggedInUser()).size());
            model.addAttribute("game", gameservice.copyGame(game, newCourse));
            model.addAttribute("url", "/course/" + targetCourse + "/create/game");
            model.addAttribute("title", "Create Game");
            return "/create_update_game";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/course/{courseName}/{gameName}/comment")
    public String addComment(@ModelAttribute(value = "comment") Comment comment, Model model,
                             @PathVariable("courseName") String courseName, @PathVariable("gameName") String gameName) {
        if (userService.isLoggedIn()) {
            comment.setGame(gameservice.getGameByName(gameName));
            comment.setUser(userService.getLoggedInUser());
            commentService.addComment(comment);
            notificationService.notifyUser(comment, comment.getGame().getCourse().getTeacher());
            return playAndEditGame(courseName, gameName, model);
        } else {
            return "redirect:/";
        }
    }


}
