package company.kangae;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends User implements Parcelable{
    private ArrayList<Game> gamesCreated = new ArrayList<>();

    public Teacher(String firstName, String lastName, String userName, String password, String email, String gender, String biography, String birthDate) {
        super(firstName, lastName, userName, password, email, gender, biography, birthDate);
    }

    protected Teacher(Parcel in) {
        gamesCreated = in.createTypedArrayList(Game.CREATOR);
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    public static boolean validGame(Game newGame) {
        for (Game game : Controller.getGames()) {
            if (game.getName().equals(newGame.getName())) {
                return false;
            }
        }
        return true;
    }

    public boolean addGame(int id, int difficultyRank, String name, String description, String instructions,
                        String category, ArrayList<Question> questions) {
        Game newGame = new Game(id, difficultyRank, name, description, instructions, category, questions);
        if (validGame(newGame)) {
            gamesCreated.add(newGame);
            Controller.getGames().add(newGame);
            return true;
        }
        return false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(gamesCreated);
    }
}
