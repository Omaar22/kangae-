package company.kangae;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by maryam on 12/21/16.
 */
public class Database {



    public void writeSerializeUsers (ArrayList <User> users, String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(users);
        objectOutputStream.close();
    }
    public void writeSerializeGames (ArrayList <Game> games, String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(games);
        objectOutputStream.close();
    }
    public ArrayList <User> readSerializerUsers (String path) throws IOException, ClassNotFoundException {
        ArrayList <User> users = new ArrayList<>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            return users;
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        users = (ArrayList <User>) objectInputStream.readObject();
        return users;
    }

    public ArrayList <Game> readSerializerGames (String path) throws IOException, ClassNotFoundException {
        ArrayList <Game> games = new ArrayList<>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            return games;
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        games = (ArrayList <Game>) objectInputStream.readObject();
        return games;
    }


}
