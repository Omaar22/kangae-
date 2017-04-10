package Kangae.models;

import javax.persistence.Entity;

@Entity
public class Student extends User {

    int score;
    String colleagueSchool;


    public Student(int score, String colleagueSchool) {
        this.score = score;
        this.colleagueSchool = colleagueSchool;
    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getColleagueSchool() {
        return colleagueSchool;
    }

    public void setColleagueSchool(String colleagueSchool) {
        this.colleagueSchool = colleagueSchool;
    }



}
