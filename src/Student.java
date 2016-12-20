import java.util.ArrayList;

public class Student extends User {

    private ArrayList<Score> scores = new ArrayList<>();

    public Student(String firstName, String lastName, String userName, String password, String email, String gender, String biography, String birthDate) {
        super(firstName, lastName, userName, password, email, gender, biography, birthDate);
    }

    public ArrayList<Score> getScores() {
        return scores;
    }
    public void setScore (Score score){
        scores.add(score);
    }
}

