package company.kangae;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Score implements Parcelable{


    private int gameId;
    private int score;


    protected Score(Parcel in) {
        gameId = in.readInt();
        score = in.readInt();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(gameId);
        parcel.writeInt(score);
    }
}
