package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_PATH = "data/users.txt";

    // Read all users
    public static List<String> readUsers() {

        List<String> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {
                users.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Check username already exists
    public static boolean userExists(String username) {

        List<String> users = readUsers();

        for (String user : users) {

            String[] data = user.split(",");

            if (data[0].equalsIgnoreCase(username)) {
                return true;
            }

        }

        return false;
    }

    // Register user
    public static boolean registerUser(String username, String password, String role) {

        if (userExists(username)) {
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            bw.write(username + "," + password + "," + role);
            bw.newLine();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Login user
    public static String loginUser(String username, String password) {

        List<String> users = readUsers();

        for (String user : users) {

            String[] data = user.split(",");

            if (data[0].equals(username) && data[1].equals(password)) {
                return data[2];
            }

        }

        return null;
    }

}