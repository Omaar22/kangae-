package App.service;

import App.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import App.repository.UserBaseRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserBaseRepository userBaseRepository;

    private User loggedInUser;

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public boolean signin(String email, String password) {
        User user = userBaseRepository.findByEmail(email);
        if (password != null && user != null && password.equals(user.getPassword())) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public void signOut() {
        loggedInUser = null;
    }

    public boolean signup(User user) {
        if (!isNewEmail(user)) {
            return false;
        }
        userBaseRepository.save(user);
        loggedInUser = user;
        return true;
    }

    boolean isNewEmail(User user) {
        return userBaseRepository.findByEmail(user.getEmail()) == null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
