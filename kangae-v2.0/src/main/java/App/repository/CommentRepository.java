package App.repository;

import App.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
    ArrayList<Comment> findByGameName(String gameName);

}
