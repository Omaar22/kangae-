package company.kangae;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner input = new Scanner(System.in);
        int choice;
        Controller.start();

        while (true) {

            while (Controller.getLoggedInUser() == null) {
                System.out.println("Yo, stranger");
                System.out.println("1.sign up");
                System.out.println("2.log in");
                choice = input.nextInt();
                input.nextLine();
                if (choice == 1) signUp();
                else if (choice == 2) signIn();
            }

            System.out.println("1.view profile");
            if (Controller.getGames().size() > 0) {
                System.out.println("2.play game");
                System.out.println("3.view game");
            }
            if (Controller.getLoggedInUser() instanceof Teacher) {
                System.out.println("4.Create game");
                //delete game?
            }
            System.out.println("5.log out");
            System.out.println("6.EXIT");
            choice = input.nextInt();
            input.nextLine();


            if (choice == 1) System.out.println(Controller.getLoggedInUser().viewProfile());

            else if (choice == 2) {
                int gameNumber = viewGames();
                playGame(Controller.getGames().get(gameNumber));
                addCommentOrRate(Controller.getGames().get(gameNumber));
            } else if (choice == 3) {
                int gameNumber = viewGames();
                System.out.println(Controller.getGames().get(gameNumber).viewGame());
                addCommentOrRate(Controller.getGames().get(gameNumber));
                System.out.println();
                System.out.println("1.Play game");
                System.out.println("2.Back");
                choice = input.nextInt();
                if (choice == 1){
                    playGame(Controller.getGames().get(gameNumber));
                    addCommentOrRate(Controller.getGames().get(gameNumber));
                }
            } else if (choice == 4) createGame();
            else if (choice == 5) logOut();
            else if (choice == 6) break;

        }

        Controller.finish();

    }

    public static void signUp() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your first name");
        String firstName = input.nextLine();
        System.out.println("Enter your last name");
        String lastName = input.nextLine();
        System.out.println("Enter username");
        String userName = input.nextLine();
        System.out.println("Enter password");
        String password = input.nextLine();
        System.out.println("Enter email");
        String email = input.nextLine();
        System.out.println("Enter gender");
        String gender = input.nextLine();
        System.out.println("Enter biography");
        String biography = input.nextLine();
        System.out.println("Enter birth date");
        String birthDate = input.nextLine();
        System.out.println("Are you a \n1.Student \n2.Teacher");
        int choice = input.nextInt();
        input.nextLine();
        boolean type = false;
        if (choice == 2) type = true;

        int validationAnswer = Controller.signUp(firstName, lastName, userName, password, email, gender, biography, birthDate, type);

        while (validationAnswer != 2) {
            if (validationAnswer == 0) {
                System.out.println("email exists, please choose different email");
                email = input.nextLine();
            } else if (validationAnswer == 1) {
                System.out.println("username exists, please choose different username");
                userName = input.nextLine();
            }
            validationAnswer = (Controller.signUp(firstName, lastName, userName, password, email, gender, biography, birthDate, type));
        }

        System.out.println("Welcome, " + userName);
    }

    public static void signIn() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username or email");
        String userNameOrEmail = input.nextLine();
        System.out.println("Enter password");
        String password = input.nextLine();

        while (!Controller.signIn(userNameOrEmail, password)) {
            System.out.println("Wrong user name or password");
            System.out.println("Enter username or email");
            userNameOrEmail = input.nextLine();
            System.out.println("Enter password");
            password = input.nextLine();
        }
        System.out.println("Welcome, " + Controller.getLoggedInUser().getUserName());
    }

    public static void logOut() {
        Controller.resetLoggedInUser();
    }

    public static void createGame() {
        Scanner input = new Scanner(System.in);
        Teacher teacher = (Teacher) Controller.getLoggedInUser();
        int id = Controller.getGames().size() + 1;

        System.out.println("Enter name");
        String name = input.nextLine();
        System.out.println("Enter description");
        String description = input.nextLine();
        System.out.println("Enter instructions");
        String instructions = input.nextLine();
        System.out.println("Category");
        String category = input.nextLine();
        System.out.println("Enter game type");
        String gameType = input.nextLine();
        System.out.println("Enter difficulty rank");
        int difficultyRank = input.nextInt();
        input.nextLine();
        System.out.println("add question? YES or NO");
        boolean addQuestion = false;
        if (Objects.equals(input.nextLine(), "YES"))
            addQuestion = true;
        ArrayList<Question> questions = new ArrayList<>();
        while (addQuestion) {
            System.out.println("Enter question");
            String question = input.nextLine();
            System.out.println("Enter answer");
            String answer = input.nextLine();
            boolean isMCQ = false;
            if (Objects.equals(gameType, "MCQ")) isMCQ = true;
            Question q = new Question(question, answer, isMCQ);
            if (Objects.equals(gameType, "MCQ")) {
                ArrayList<String> options = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    System.out.println("Enter option " + i + 1);
                    String option = input.nextLine();
                    options.add(option);
                }
                q.setOptions(options);
            }
            questions.add(q);
            System.out.println("Enter another question? YES or NO");
            if (Objects.equals(input.nextLine(), "NO")) addQuestion = false;
        }

        while (!teacher.addGame(id, difficultyRank, name, description, instructions, category, questions)) {
            System.out.println("Game name exists, please choose another name");
            System.out.println("Enter game name");
            name = input.nextLine();
        }


    }

    public static void playGame(Game game) {

        Scanner input = new Scanner(System.in);
        int correctAnswers = 0;
        System.out.println(game.getName());
        System.out.println(game.getDescription());
        System.out.println(game.getInstructions());
        System.out.println(game.getCategory());
        System.out.println(game.getDifficultyRank());
        System.out.println();

        ArrayList<Question> questions = game.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getQuestion());
            ArrayList<String> options = questions.get(i).getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println(j + 1 + ". " + options.get(j));
            }

            System.out.println("Enter your choice");
            int userAnswer = input.nextInt();
            input.nextLine();

            if (Objects.equals(options.get(userAnswer - 1), questions.get(i).getAnswer())) {
                System.out.println("CORRECT!");
                ++correctAnswers;
            } else System.out.println("Not correct :( ");

        }

        System.out.println("Your score is " + correctAnswers);
        if (Controller.getLoggedInUser() instanceof Student) {
            ArrayList<Score> scores = ((Student) Controller.getLoggedInUser()).getScores();
            boolean found = false;
            for (int i = 0; i < scores.size(); i++)
                if (scores.get(i).getGameId() == game.getId() && scores.get(i).getScore() < correctAnswers) {
                    System.out.println("Congratulation! high score is achieved");
                    scores.get(i).setScore(correctAnswers);
                } else if (scores.get(i).getGameId() == game.getId()) found = true;

            if (!found) {
                Score score = new Score(game.getId(), correctAnswers);
                ((Student) Controller.getLoggedInUser()).setScore(score);
                System.out.println("Congratulation! high score is achieved");
            }
        }

        System.out.println(1 + ". view answers");
        System.out.println(2 + ". EXIT");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 2) return;

        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getQuestion());
            System.out.println(questions.get(i).getAnswer());
            System.out.println();
        }
        System.out.println();

    }

    public static int viewGames() {
        Scanner input = new Scanner(System.in);
        ArrayList<Game> games = Controller.getGames();
        if (games.size() == 0) {
            System.out.println("No available games yet, stay tuned!");
            return -1;
        }
        System.out.println("Available games");
        System.out.println();
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            System.out.println(i + 1 + ". name: " + game.getName() + "\nCategory: " + game.getCategory() + "\nRating: " + game.getRate());
        }
        System.out.println("Choose game number");
        int choice = input.nextInt();
        input.nextLine();
        System.out.println();
        return choice - 1;

    } //return game index

    public static void addCommentOrRate(Game game) {

        Scanner input = new Scanner(System.in);
        int choice;
        ArrayList<Comment> comments = game.getComments();

        if (comments.size() == 0)
            System.out.println("No available comments");


        for (int i = 0; i < comments.size(); i++) {
            System.out.println(comments.get(i).getUserName());
            System.out.println(comments.get(i).getComment());

            for (int j = 0; j < comments.get(i).getReplies().size(); j++)
                System.out.println("    " + comments.get(i).getReplies().get(j));

                System.out.println("1.Add reply");
                System.out.println("2.View more");
                choice = input.nextInt();
                input.nextLine();
                if (choice == 1) {
                    System.out.println("Enter reply");
                    String reply = input.nextLine();
                    comments.get(i).getReplies().add(Controller.getLoggedInUser().getUserName() + " " + reply);
                }
            System.out.println();
        }
        System.out.println("1.Add comment");
        System.out.println("2.back");
        choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            String comment;
            System.out.println("Enter comment");
            comment = input.nextLine();
            Comment c = new Comment();
            c.setComment(comment);
            c.setUserName(Controller.getLoggedInUser().getUserName());
            comments.add(c);
        }
        System.out.println();

        System.out.println("1.Rate game");
        System.out.println("2.back");
        choice = input.nextInt();
        input.nextLine();
        if (choice == 1){
            System.out.println("Enter your rating from 1 to 5");
            int rate = input.nextInt();
            game.rate(rate);
            System.out.println(game.getRate());
        }

    }


}

