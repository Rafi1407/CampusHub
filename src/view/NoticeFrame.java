package view;

import controller.NoticeController;
import model.Notice;
import model.Session;
import util.Theme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NoticeFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    private NoticeController controller;

    public NoticeFrame() {

        controller = new NoticeController();

        setTitle("CampusHub - Notice Board");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        mainPanel.setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("Notice Board",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(Theme.PRIMARY);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Title");
        tableModel.addColumn("Description");
        tableModel.addColumn("Posted By");

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel();

        JButton addButton = new JButton("Add Notice");
        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        bottomPanel.add(addButton);
        bottomPanel.add(refreshButton);
        bottomPanel.add(backButton);

        // Student হলে Add Notice hide
        if(Session.getRole().equalsIgnoreCase("Student")){
            addButton.setVisible(false);
        }

        mainPanel.add(title,BorderLayout.NORTH);
        mainPanel.add(scrollPane,BorderLayout.CENTER);
        mainPanel.add(bottomPanel,BorderLayout.SOUTH);

        add(mainPanel);

        loadTable();

        refreshButton.addActionListener(e -> loadTable());

        addButton.addActionListener(e -> {

            String noticeTitle = JOptionPane.showInputDialog(
                    this,
                    "Enter Notice Title:"
            );

            if(noticeTitle == null || noticeTitle.trim().isEmpty()){
                return;
            }

            String description = JOptionPane.showInputDialog(
                    this,
                    "Enter Notice Description:"
            );

            if(description == null || description.trim().isEmpty()){
                return;
            }

            controller.addNotice(noticeTitle, description);

            loadTable();

            JOptionPane.showMessageDialog(
                    this,
                    "Notice Added Successfully!"
            );

        });

        backButton.addActionListener(e -> dispose());

        setVisible(true);

    }

    private void loadTable(){

        tableModel.setRowCount(0);

        for(Notice notice : controller.getAllNotices()){

            tableModel.addRow(new Object[]{

                    notice.getTitle(),
                    notice.getDescription(),
                    notice.getPostedBy()

            });

        }

    }

}