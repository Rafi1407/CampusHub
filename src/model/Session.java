package model;

public class Session {

    private static String username;
    private static String role;

    public static void login(String username, String role) {
        Session.username = username;
        Session.role = role;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static void logout() {
        username = null;
        role = null;
    }

    public static boolean isLoggedIn() {
        return username != null;
    }

}