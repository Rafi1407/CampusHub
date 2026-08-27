package view;

import controller.LoginController;
import model.Session;
import util.Theme;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private LoginController controller;

    public AdminDashboard() {

        controller = new LoginController();

        setTitle("CampusHub - Admin Dashboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Theme.BACKGROUND);

        // Header
        JPanel header = new JPanel();
        header.setBackground(Theme.PRIMARY);

        JLabel title = new JLabel("CampusHub");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        header.add(title);

        // Welcome
        JLabel welcome = new JLabel(
                "Welcome, " + Session.getUsername(),
                SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 22));

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(Theme.BACKGROUND);

        JButton manageUsersBtn = createButton("Manage Users");
        JButton manageResourceBtn = createButton("Manage Resources");
        JButton manageNoticeBtn = createButton("Manage Notices");
        JButton logoutBtn = createButton("Logout");

        buttonPanel.add(manageUsersBtn);
        buttonPanel.add(manageResourceBtn);
        buttonPanel.add(manageNoticeBtn);
        buttonPanel.add(logoutBtn);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(welcome, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Manage Users
        manageUsersBtn.addActionListener(e -> new UserManagementFrame());

        // Manage Resources
        manageResourceBtn.addActionListener(e ->
                new ResourceFrame(true)
        );

        // Manage Notices
        manageNoticeBtn.addActionListener(e ->
                new NoticeFrame()
        );

        // Logout
        logoutBtn.addActionListener(e -> {

            dispose();
            new LoginFrame();

        });

        setVisible(true);

    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setBackground(Theme.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setFocusPainted(false);

        return button;
    }

}