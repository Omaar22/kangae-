package Kangae.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Course {

    String name;
    @Id
    String ID;
    int numOfGames;
    @ManyToOne
    @JoinColumn (name = "userName", table = "Teacher")
    String teacherName;


    public Course(String name, String id, int numOfGames, String teacherName) {
        this.name = name;
        this.ID = id;
        this.numOfGames = numOfGames;
        this.teacherName = teacherName;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}
