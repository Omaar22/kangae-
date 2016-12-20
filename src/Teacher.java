import java.util.ArrayList;

public class Teacher extends User {
    private ArrayList<Game> gamesCreated = new ArrayList<>();

    public Teacher(String firstName, String lastName, String userName, String password, String email, String gender, String biography, String birthDate) {
        super(firstName, lastName, userName, password, email, gender, biography, birthDate);
    }

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


}
