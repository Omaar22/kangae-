package company.kangae;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Parcelable {

    private String question;
    private String answer;
    ArrayList <String> options = new ArrayList<>();
    private boolean isMCQ;

    public Question(String question, String answer, boolean isMCQ){
        this.question = question;
        this.answer = answer;
        this.isMCQ = isMCQ;
        if (!isMCQ){
            options.add("True");
            options.add("False");
        }
    }

    protected Question(Parcel in) {
        question = in.readString();
        answer = in.readString();
        options = in.createStringArrayList();
        isMCQ = in.readByte() != 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public boolean isMCQ() {
        return isMCQ;
    }

    public void setMCQ(boolean MCQ) {
        isMCQ = MCQ;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(answer);
        parcel.writeStringList(options);
        parcel.writeByte((byte) (isMCQ ? 1 : 0));
    }
}
