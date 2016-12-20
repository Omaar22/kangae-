import java.util.ArrayList;

public class Game {
    private int id, difficultyRank, numberOfRates;
    private String name, description, instructions, category;
    private float averageRate;
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDifficultyRank() {
        return difficultyRank;
    }
    public void setDifficultyRank(int difficultyRank) {
        this.difficultyRank = difficultyRank;
    }
    public int getNumberOfRates() {
        return numberOfRates;
    }
    public void setNumberOfRates(int numberOfRates) {
        this.numberOfRates = numberOfRates;
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
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public float getAverageRate() {
        return averageRate;
    }
    public void setAverageRate(float averageRate) {
        this.averageRate = averageRate;
    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    public String viewGame (){
        String ret = (name + "\n" + description + "\n" + instructions + "\n" + category +"\n" + "difficulty rank: " + difficultyRank +
                "\nRate: " + getRate());
        //TODO: view comments and replies
        return ret;

    }

    public Game(int id, int difficultyRank, String name, String description, String instructions,
                String category, ArrayList<Question> questions) {
        this.id = id;
        this.difficultyRank = difficultyRank;
        this.name = name;

        this.description = description;
        this.instructions = instructions;
        this.category = category;
        this.questions = questions;
        numberOfRates = 0;
        averageRate = 0;
    }

    String getRate(){
        if(averageRate < 1.5){
            return "Boring";
        }else if(averageRate < 2.5){
            return "Normal";

        }else{
            return "Interesting";
        }
    }
    void rate(int rate) {
        numberOfRates++;
        averageRate = (averageRate + rate) / numberOfRates;
    }

    void addComment(Comment comment){
        comments.add(comment);
    }
    void addQuestion(Question question){
        questions.add(question);
    }
}

