import java.io.Serializable;
import java.util.ArrayList;

public class Comment implements Serializable{

    private String userName;
    private String comment;
    private ArrayList <String> replies = new ArrayList<>();

    public ArrayList<String> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<String> replies) {
        this.replies = replies;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
