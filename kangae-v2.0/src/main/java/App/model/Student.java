package App.model;

import javax.persistence.Entity;

@Entity
public class Student extends User {
    private int score = 0;
    private String collegeSchool;

    public String getCollegeSchool() {
        return collegeSchool;
    }

    public void setCollegeSchool(String collegeSchool) {
        this.collegeSchool = collegeSchool;
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
