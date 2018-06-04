package App.service;

import App.model.Comment;
import App.model.Notification;
import App.model.User;
import App.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepo;

    public ArrayList<Notification> getAllNotificationsForUser(User user) {
        return notificationRepo.findByUserId(user.getId());
    }

    public ArrayList<Notification> getUnreadNotificationsForUser(User user) {
        return notificationRepo.findByUserIdAndIsRead(user.getId(), false);
    }

    public void notifyUser(Comment comment, User user) {
        Notification notification = new Notification(comment, user, comment.getUser().getName() + " Commented on your game", false);
        notificationRepo.save(notification);
    }

    public void setAllRead(User user) {
        ArrayList<Notification> unread = getUnreadNotificationsForUser(user);
        for (Notification notification : unread) {
            notification.setRead(true);
            notificationRepo.save(notification);
        }
    }
}
