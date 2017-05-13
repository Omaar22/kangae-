package App.repository;
import App.model.Comment;
import App.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationRepository, String> {
    ArrayList<Comment> findBycommentId(long commentId);
    ArrayList<User> findByuserId(long userId);
}
