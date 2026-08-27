package view;

import controller.ResourceController;
import model.Resource;
import util.ResourceFileManager;
import util.Theme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ResourceFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    private ResourceController controller;

    // Admin hole true hobe
    private boolean adminMode;

    //for Student
    public ResourceFrame() {
        this(false);
    }

    //  for Admin
    public ResourceFrame(boolean adminMode) {

        this.adminMode = adminMode;

        controller = new ResourceController();

        setTitle("CampusHub - Resource Sharing");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        mainPanel.setBackground(Theme.BACKGROUND);

        //  TITLE

        JLabel title =
                new JLabel(
                        "Resource Sharing",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(Theme.PRIMARY);

        // SEARCH PANEL

        JPanel searchPanel =
                new JPanel(new BorderLayout(10, 10));

        searchPanel.setBackground(Theme.BACKGROUND);

        searchField = new JTextField();

        JButton refreshButton =
                new JButton("Refresh");

        JButton searchButton =
                new JButton("Search");

        searchPanel.add(
                refreshButton,
                BorderLayout.WEST
        );

        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                searchButton,
                BorderLayout.EAST
        );

        //  TABLE

        tableModel =
                new DefaultTableModel();

        tableModel.addColumn("Title");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Type");
        tableModel.addColumn("Uploaded By");

        table =
                new JTable(tableModel);

        table.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(table);

        //  BOTTOM PANEL

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setBackground(
                Theme.BACKGROUND
        );

        JButton addButton =
                new JButton("Add Resource");

        JButton deleteButton =
                new JButton("Delete Resource");

        JButton backButton =
                new JButton("Back");

        bottomPanel.add(addButton);

        // just Admin er jonno Delete button
        if (adminMode) {
            bottomPanel.add(deleteButton);
        }

        bottomPanel.add(backButton);

        // NORTH PANEL

        JPanel northPanel =
                new JPanel(new BorderLayout());

        northPanel.setBackground(
                Theme.BACKGROUND
        );

        northPanel.add(
                title,
                BorderLayout.NORTH
        );

        northPanel.add(
                searchPanel,
                BorderLayout.SOUTH
        );

        // ADD COMPONENTS

        mainPanel.add(
                northPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        //  LOAD TABLE

        loadTable();

        //  REFRESH

        refreshButton.addActionListener(
                e -> loadTable()
        );

        // SEARCH

        searchButton.addActionListener(e -> {

            String keyword =
                    searchField.getText().trim();

            if (keyword.isEmpty()) {

                loadTable();

            } else {

                loadTable(
                        controller.searchResource(keyword)
                );

            }

        });

        //ADD RESOURCE

        addButton.addActionListener(e -> {

            String resourceTitle =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Resource Title:"
                    );

            if (resourceTitle == null ||
                    resourceTitle.trim().isEmpty()) {

                return;
            }

            String subject =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Subject:"
                    );

            if (subject == null ||
                    subject.trim().isEmpty()) {

                return;
            }

            String[] types = {

                    "PDF",
                    "DOCX",
                    "PPT",
                    "Image",
                    "Video",
                    "ZIP",
                    "Other"

            };

            String type =
                    (String) JOptionPane.showInputDialog(
                            this,
                            "Select Resource Type:",
                            "Resource Type",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            types,
                            types[0]
                    );

            if (type == null) {
                return;
            }

            controller.addResource(
                    resourceTitle,
                    subject,
                    type
            );

            loadTable();

            JOptionPane.showMessageDialog(
                    this,
                    "Resource Added Successfully!"
            );

        });

        //DELETE RESOURCE

        if (adminMode) {

            deleteButton.addActionListener(e -> {

                int selectedRow =
                        table.getSelectedRow();


                if (selectedRow == -1) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please select a resource first.",
                            "Delete Resource",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                // Selected resource'S title
                String resourceTitle =
                        table.getValueAt(
                                selectedRow,
                                0
                        ).toString();

                // Confirmation
                int confirm =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete \""
                                        + resourceTitle
                                        + "\"?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (confirm ==
                        JOptionPane.YES_OPTION) {

                    boolean deleted =
                            ResourceFileManager
                                    .deleteResource(
                                            resourceTitle
                                    );

                    if (deleted) {

                        loadTable();

                        JOptionPane.showMessageDialog(
                                this,
                                "Resource deleted successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Resource could not be deleted.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                    }

                }

            });

        }

        //  BACK

        backButton.addActionListener(
                e -> dispose()
        );

        setVisible(true);

    }

    // LOAD ALL RESOURCES

    public void loadTable() {

        loadTable(
                controller.getAllResources()
        );

    }

    // LOAD RESOURCES

    public void loadTable(
            List<Resource> resources) {

        tableModel.setRowCount(0);

        for (Resource r : resources) {

            tableModel.addRow(
                    new Object[]{

                            r.getTitle(),
                            r.getSubject(),
                            r.getType(),
                            r.getUploadedBy()

                    }
            );

        }

    }

}