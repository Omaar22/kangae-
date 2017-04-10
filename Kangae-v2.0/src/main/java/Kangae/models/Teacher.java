package Kangae.models;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;

@Entity
public class Teacher extends User {

    int numOfCourses;
    int numOfGames;
    String colleagueSchool;


    public Teacher(int numOfCourses, int numOfGames, String colleagueSchool) {
        this.numOfCourses = numOfCourses;
        this.numOfGames = numOfGames;
        this.colleagueSchool = colleagueSchool;
    }

    public int getNumOfCourses() {
        return numOfCourses;
    }

    public void setNumOfCourses(int numOfCourses) {
        this.numOfCourses = numOfCourses;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

    public String getColleagueSchool() {
        return colleagueSchool;
    }

    public void setColleagueSchool(String colleagueSchool) {
        this.colleagueSchool = colleagueSchool;
    }


}
