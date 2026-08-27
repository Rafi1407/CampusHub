package view;

import controller.LoginController;
import util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private LoginController controller;

    public LoginFrame() {

        controller = new LoginController();

        setTitle("CampusHub - Login");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Theme.BACKGROUND);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("CampusHub");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.PRIMARY_DARK);

        JLabel subtitle = new JLabel("Smart Student Collaboration Platform");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(Theme.NORMAL_FONT);
        subtitle.setForeground(Color.GRAY);

        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(40));

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(Theme.LABEL_FONT);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        usernameField.setFont(Theme.NORMAL_FONT);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(Theme.LABEL_FONT);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        passwordField.setFont(Theme.NORMAL_FONT);

        panel.add(userLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(usernameField);

        panel.add(Box.createVerticalStrut(20));

        panel.add(passLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(passwordField);

        panel.add(Box.createVerticalStrut(35));

        JButton loginButton = createButton("Login");
        JButton registerButton = createButton("Register");

        // Center Button
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(registerButton);

        add(panel);

        // Login
        loginButton.addActionListener(e -> {

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            String role = controller.login(username, password);

            if (role == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            dispose();

            if (role.equalsIgnoreCase("Student")) {

                new StudentDashboard();

            } else {

                new AdminDashboard();

            }

        });

        // Register
        registerButton.addActionListener(e -> new RegisterFrame());

        setVisible(true);

    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(200, 42));
        button.setMaximumSize(new Dimension(200, 42));
        button.setMinimumSize(new Dimension(200, 42));

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setBackground(Theme.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFont(Theme.BUTTON_FONT);

        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

}