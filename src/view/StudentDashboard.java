package view;

import controller.LoginController;
import model.Session;
import util.Theme;

import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {

    private LoginController controller;

    public StudentDashboard() {

        controller = new LoginController();

        setTitle("CampusHub - Student Dashboard");
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

        // Welcome Label
        JLabel welcome = new JLabel(
                "Welcome, " + Session.getUsername(),
                SwingConstants.CENTER);

        welcome.setFont(new Font("Segoe UI", Font.BOLD, 22));

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(Theme.BACKGROUND);

        JButton resourceBtn = createButton("Resource Sharing");
        JButton lostBtn = createButton("Lost & Found");
        JButton noticeBtn = createButton("Notices");
        JButton logoutBtn = createButton("Logout");

        buttonPanel.add(resourceBtn);
        buttonPanel.add(lostBtn);
        buttonPanel.add(noticeBtn);
        buttonPanel.add(logoutBtn);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(welcome, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Resource Module
        resourceBtn.addActionListener(e -> new ResourceFrame());

        // Notice Module
        noticeBtn.addActionListener(e -> new NoticeFrame());

        // Lost & Found (Temporary)
        lostBtn.addActionListener(e -> new LostFrame());

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
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));

        return button;

    }

}