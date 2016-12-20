public class Score {


    private int gameId;
    private int score;


    public int getGameId() {
        return gameId;
    }

    public int getScore() {
        return score;
    }

    Score (int gameId, int score){
        this.gameId = gameId;
        this.score = score;
    }

    public void setScore(int score){
        this.score = score;
    }

}
