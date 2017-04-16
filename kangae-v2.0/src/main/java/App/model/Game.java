package App.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    private String description;
    private String instruction;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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



    public Game() {
    }

    public Game(long id, String name, String description, String instruction) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.instruction = instruction;
    }

}
