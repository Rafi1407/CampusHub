package model;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    @Override
    public void showDashboard() {
        System.out.println("Admin Dashboard Opened");
    }
}