package company.kangae;

import java.io.Serializable;

public class Score implements Serializable{


    private int gameId;
    private int score;


    public int getGameId() {
        return gameId;
    }

    public int getScore() {
        return score;
    }

    public Score(int gameId, int score){
        this.gameId = gameId;
        this.score = score;
    }

    public void setScore(int score){
        this.score = score;
    }

}
