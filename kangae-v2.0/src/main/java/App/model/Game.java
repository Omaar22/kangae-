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
    @NotNull
    private String description;
    @NotNull
    private String instruction;
    @NotNull
    private String question;
    @NotNull
    private String answer;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Game(String name, String description, String instruction, String question, String answer, Course course) {
        this.name = name;
        this.description = description;
        this.instruction = instruction;
        this.question = question;
        this.answer = answer;
        this.course = course;
    }

    public Game() {
    }

}
