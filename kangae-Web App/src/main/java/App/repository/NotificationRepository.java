package App.repository;
import App.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {
    ArrayList<Notification> findByUserId(long userId);
    ArrayList<Notification> findByUserIdAndIsRead(long userId, boolean read);

}
