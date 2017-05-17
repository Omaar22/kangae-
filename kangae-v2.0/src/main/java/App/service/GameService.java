package App.service;

import App.model.Comment;
import App.model.Course;
import App.model.Game;
import App.repository.CourseRepository;
import App.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepo;

    public ArrayList<Game> getALLGame() {
        return (ArrayList<Game>) gameRepo.findAll();
    }

    public ArrayList<Game> getGamesInCourse(Course course) {
        return gameRepo.findByCourseId(course.getId());
    }

    public void addGame(Game game) {
        gameRepo.save(game);
    }

    public Game getGameByName(String name) {
        return gameRepo.findByName(name);
    }

    public Game getGameInCourse(String courseName, String gameName) {
        return gameRepo.findByCourseNameAndName(courseName, gameName);
    }

    public ArrayList<Game> getGamesByTeacherEmail(String teacherEmail) {
        return gameRepo.findByCourseTeacherEmail(teacherEmail);
    }

    public boolean isNewName(Game game) {
        return gameRepo.findByName(game.getName()) == null;
    }

    public Game copyGame(Game game, Course newCourse) {
        Game newGame = new Game(game.getName(), game.getDescription(), game.getInstruction(), game.getQuestion(),
                game.getAnswer(), newCourse);
        newGame.setName(newGame.getName() + "-copy " + Integer.toString(game.getNumOfCopies() + 1));
        //TODO:check if changes
        game.setNumOfCopies(game.getNumOfCopies() + 1);
        return newGame;
    }
}
