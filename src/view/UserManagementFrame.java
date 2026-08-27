package view;

import util.FileManager;
import util.Theme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public UserManagementFrame() {

        setTitle("CampusHub - User Management");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panel.setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("User Management",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(Theme.PRIMARY);

        model = new DefaultTableModel();

        model.addColumn("Username");
        model.addColumn("Role");

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        JButton refreshBtn = new JButton("Refresh");
        JButton backBtn = new JButton("Back");

        JPanel bottom = new JPanel();

        bottom.add(refreshBtn);
        bottom.add(backBtn);

        panel.add(title,BorderLayout.NORTH);
        panel.add(scroll,BorderLayout.CENTER);
        panel.add(bottom,BorderLayout.SOUTH);

        add(panel);

        loadUsers();

        refreshBtn.addActionListener(e->loadUsers());

        backBtn.addActionListener(e->dispose());

        setVisible(true);

    }

    private void loadUsers(){

        model.setRowCount(0);

        List<String> users = FileManager.readUsers();

        for(String line : users){

            String[] data = line.split(",");

            if(data.length>=3){

                model.addRow(new Object[]{

                        data[0],
                        data[2]

                });

            }

        }

    }

}