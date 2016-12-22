package company.kangae;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Controller implements Serializable{

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Game> games = new ArrayList<>();
    private static User loggedInUser = null;

    private static int validSignUp(User newUser) {
        for (User u : users) {
            if (u.getEmail().equals(newUser.getEmail()))
                return 0;

            if ( u.getUserName().equals(newUser.getUserName()))
                return 1;
        }

        return 2;
    }

    public static int signUp(String firstName, String lastName, String userName, String password, String email, String gender,
                   String biography, String birthDate, boolean isTeacher) {
        User newUser;
        if (isTeacher) {
            newUser = new Teacher(firstName, lastName, userName, password, email, gender, biography, birthDate);
        } else {
            newUser = new Student(firstName, lastName, userName, password, email, gender, biography, birthDate);
        }
        int validationAnswer = validSignUp(newUser);
        if (validSignUp(newUser) != 2) {
            return validationAnswer;
        }
        users.add(newUser);
        signIn(email, password);
        return 2;
    }

    public static boolean signIn(String userNameOrEmail, String password) {
        for (User u : users) {
            if ((u.getEmail().equals(userNameOrEmail) || u.getUserName().equals(userNameOrEmail)) && u.getPassword().equals(password)) {
                loggedInUser = u;
                return true;
            }
        }
        return false;

    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    public static void resetLoggedInUser(){
        loggedInUser = null;
    }
    public static ArrayList<Game> getGames() {
        return games;
    }
    public static ArrayList<User> getUsers() {
        return users;
    }
    public static void start () throws IOException, ClassNotFoundException {
        Database database = new Database();
        games = database.readSerializerGames("games.ser");
        users = database.readSerializerUsers("users.ser");
    }
    public static void finish () throws IOException {
        Database database = new Database();
        database.writeSerializeGames(games, "games.ser");
        database.writeSerializeUsers(users, "users.ser");
    }
}
