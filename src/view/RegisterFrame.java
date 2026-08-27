package view;

import controller.LoginController;
import util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    private LoginController controller;

    public RegisterFrame() {

        controller = new LoginController();

        setTitle("CampusHub - Register");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBackground(Theme.BACKGROUND);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // ================= TITLE =================

        JLabel title = new JLabel("Create Account");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.PRIMARY);

        JLabel subtitle = new JLabel("CampusHub Registration");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(Theme.NORMAL_FONT);
        subtitle.setForeground(Color.GRAY);

        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitle);

        panel.add(Box.createVerticalStrut(40));

        // ================= USERNAME =================

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(Theme.LABEL_FONT);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField();
        usernameField.setFont(Theme.NORMAL_FONT);
        usernameField.setPreferredSize(new Dimension(400, 35));
        usernameField.setMaximumSize(new Dimension(400, 35));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(userLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(usernameField);

        panel.add(Box.createVerticalStrut(20));

        // ================= PASSWORD =================

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(Theme.LABEL_FONT);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setFont(Theme.NORMAL_FONT);
        passwordField.setPreferredSize(new Dimension(400, 35));
        passwordField.setMaximumSize(new Dimension(400, 35));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(passLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(passwordField);

        panel.add(Box.createVerticalStrut(20));

        // ================= ROLE =================

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setFont(Theme.LABEL_FONT);
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        roleBox = new JComboBox<>(
                new String[]{
                        "Student",
                        "Admin"
                }
        );

        roleBox.setFont(Theme.NORMAL_FONT);
        roleBox.setPreferredSize(new Dimension(400, 35));
        roleBox.setMaximumSize(new Dimension(400, 35));
        roleBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(roleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(roleBox);

        panel.add(Box.createVerticalStrut(35));

        // ================= BUTTONS =================

        JButton registerButton = createButton("Register");
        JButton cancelButton = createButton("Cancel");

        panel.add(registerButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(cancelButton);

        add(panel);

        // ================= REGISTER =================

        registerButton.addActionListener(e -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(passwordField.getPassword());

            String role =
                    (String) roleBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Registration",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            boolean success =
                    controller.register(
                            username,
                            password,
                            role
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Username already exists!",
                        "Registration Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        });

        // ================= CANCEL =================

        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    // ================= BUTTON DESIGN =================

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setPreferredSize(
                new Dimension(200, 40)
        );

        button.setMaximumSize(
                new Dimension(200, 40)
        );

        button.setMinimumSize(
                new Dimension(200, 40)
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setBackground(Theme.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFont(Theme.BUTTON_FONT);
        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }
}