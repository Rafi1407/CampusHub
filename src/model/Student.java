package model;

public class Student extends User {

    public Student(String username, String password) {
        super(username, password, "Student");
    }

    @Override
    public void showDashboard() {
        System.out.println("Student Dashboard Opened");
    }
}