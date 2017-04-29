package App.repository;

import App.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GameRepository extends CrudRepository<Game, String>{
    ArrayList<Game> findByCourseId(long id);

    ArrayList<Game> findByCourseTeacherEmail(String email);

    Game findByCourseNameAndName(String courseName, String name);
}
