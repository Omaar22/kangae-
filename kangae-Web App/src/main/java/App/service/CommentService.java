package App.service;

import App.model.Comment;
import App.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepo;

    public ArrayList<Comment> getCommentsInGame(String gameName) {
        return commentRepo.findByGameName(gameName);
    }
    public void addComment(Comment comment) {
        commentRepo.save(comment);
    }

}
