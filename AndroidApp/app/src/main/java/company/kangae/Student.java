package company.kangae;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Parcelable{

    private ArrayList<Score> scores = new ArrayList<>();

    public Student(String firstName, String lastName, String userName, String password, String email, String gender, String biography, String birthDate) {
        super(firstName, lastName, userName, password, email, gender, biography, birthDate);
    }

    protected Student(Parcel in) {;
        scores = in.createTypedArrayList(Score.CREATOR);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public ArrayList<Score> getScores() {
        return scores;
    }
    public void setScore (Score score){
        scores.add(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(scores);
    }
}

