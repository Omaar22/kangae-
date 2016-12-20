import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

    private String question;
    private String answer;
    ArrayList <String> options = new ArrayList<>();
    private boolean isMCQ;

    Question (String question, String answer, boolean isMCQ){
        this.question = question;
        this.answer = answer;
        this.isMCQ = isMCQ;
        if (!isMCQ){
            options.add("True");
            options.add("False");
        }
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


}
