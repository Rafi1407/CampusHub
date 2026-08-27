package view;

import controller.LostController;
import model.LostItem;
import util.Theme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LostFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    private LostController controller;

    public LostFrame() {

        controller = new LostController();

        setTitle("CampusHub - Lost & Found");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        mainPanel.setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("Lost & Found",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(Theme.PRIMARY);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Item");
        tableModel.addColumn("Location");
        tableModel.addColumn("Status");
        tableModel.addColumn("Posted By");

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottom = new JPanel();

        JButton reportBtn = new JButton("Report Item");
        JButton refreshBtn = new JButton("Refresh");
        JButton backBtn = new JButton("Back");

        bottom.add(reportBtn);
        bottom.add(refreshBtn);
        bottom.add(backBtn);

        mainPanel.add(title,BorderLayout.NORTH);
        mainPanel.add(scrollPane,BorderLayout.CENTER);
        mainPanel.add(bottom,BorderLayout.SOUTH);

        add(mainPanel);

        loadTable();

        reportBtn.addActionListener(e -> reportItem());

        refreshBtn.addActionListener(e -> loadTable());

        backBtn.addActionListener(e -> dispose());

        setVisible(true);

    }

    private void reportItem(){

        String item = JOptionPane.showInputDialog(
                this,
                "Item Name:"
        );

        if(item==null || item.trim().isEmpty()) return;

        String location = JOptionPane.showInputDialog(
                this,
                "Location:"
        );

        if(location==null || location.trim().isEmpty()) return;

        String[] statusList={
                "Lost",
                "Found"
        };

        String status=(String)JOptionPane.showInputDialog(
                this,
                "Select Status",
                "Status",
                JOptionPane.PLAIN_MESSAGE,
                null,
                statusList,
                statusList[0]
        );

        if(status==null) return;

        controller.addItem(item,location,status);

        loadTable();

        JOptionPane.showMessageDialog(
                this,
                "Item Reported Successfully!"
        );

    }

    private void loadTable(){

        tableModel.setRowCount(0);

        List<LostItem> list=controller.getAllItems();

        for(LostItem item:list){

            tableModel.addRow(new Object[]{

                    item.getItemName(),
                    item.getLocation(),
                    item.getStatus(),
                    item.getPostedBy()

            });

        }

    }

}