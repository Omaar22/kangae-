package App.service;

import App.model.Comment;
import App.model.User;
import App.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepo;

    public ArrayList<Comment> getComments(long commentId) {
        return (ArrayList<Comment>) notificationRepo.findBycommentId(commentId);
    }

    public ArrayList<User> getUsers(long userId) {
        return (ArrayList<User>) notificationRepo.findByuserId(userId);
    }

}
