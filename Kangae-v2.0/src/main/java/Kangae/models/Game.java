package Kangae.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Game {

    String name;
    String description;
    String instruction;
    @Id
    int ID;
    @ManyToOne
    @JoinColumn (name = "ID", table = "Course")
    int courseID;

    public Game(String name, String description, String instruction, int ID, int nubOfQuestions) {
        this.name = name;
        this.description = description;
        this.instruction = instruction;
        this.ID = ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}
