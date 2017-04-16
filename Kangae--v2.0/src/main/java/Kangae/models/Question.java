package Kangae.models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Question {

    String question;
    boolean answer;
    @ManyToOne
    @JoinColumn (name = "ID", table = "Game")

    int gameID;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }


}
