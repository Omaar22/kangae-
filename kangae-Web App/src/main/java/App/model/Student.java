package App.model;

import javax.persistence.Entity;

@Entity
public class Student extends User {
    private int score = 0;

    public Student() {}

    public Student(User user) {
        super(user);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        ++score;
    }
}
