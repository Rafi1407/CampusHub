package controller;

import model.Session;
import util.FileManager;

public class LoginController {

    // Register
    public boolean register(String username, String password, String role) {

        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        return FileManager.registerUser(username.trim(), password.trim(), role);
    }

    // Login
    public String login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        String role = FileManager.loginUser(username.trim(), password.trim());

        if (role != null) {
            Session.login(username.trim(), role);
        }

        return role;
    }

    // Logout
    public void logout() {
        Session.logout();
    }
}