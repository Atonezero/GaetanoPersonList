package ElencoPersone;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class SwingUserInterface_WIP {
    private ListOfPersone manager;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField dobField;
    private JTextField searchField;

    public SwingUserInterface_WIP(ListOfPersone manager) {
        this.manager = manager;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("List of People Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchAction());
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        frame.add(searchPanel, BorderLayout.NORTH);

        // Table for displaying people
        String[] columnNames = {"Name", "Surname", "Date of Birth"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Surname:"));
        surnameField = new JTextField();
        inputPanel.add(surnameField);

        inputPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        dobField = new JTextField();
        inputPanel.add(dobField);

        JButton addButton = new JButton("Add Person");
        addButton.addActionListener(new AddPersonAction());
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Person");
        deleteButton.addActionListener(new DeletePersonAction());
        inputPanel.add(deleteButton);

        // Sorting Panel
        JPanel sortPanel = new JPanel();
        JButton sortByNameButton = new JButton("Sort by Name");
        sortByNameButton.addActionListener(new SortByNameAction());
        JButton sortBySurnameButton = new JButton("Sort by Surname");
        sortBySurnameButton.addActionListener(new SortBySurnameAction());
        JButton sortByDobButton = new JButton("Sort by DOB");
        sortByDobButton.addActionListener(new SortByDobAction());
        sortPanel.add(sortByNameButton);
        sortPanel.add(sortBySurnameButton);
        sortPanel.add(sortByDobButton);

        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(sortPanel, BorderLayout.EAST);
        frame.setVisible(true);
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Persona> people = manager.getAll();
        for (Persona persona : people) {
            tableModel.addRow(new Object[]{persona.getNome(), persona.getCognome(), persona.getDoB()});
        }
    }

    private class AddPersonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String surname = surnameField.getText();
            String dob = dobField.getText();

            if (!name.isEmpty() && !surname.isEmpty() && !dob.isEmpty()) {
                Persona p = new Persona(name, surname, dob);
                if (manager.Add(p)) {
                    JOptionPane.showMessageDialog(frame, "Person added successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Unable to add person.");
                }
                updateTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            }
        }
    }

    private class DeletePersonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String surname = surnameField.getText();
            String dob = dobField.getText();

            if (!name.isEmpty() && !surname.isEmpty() && !dob.isEmpty()) {
                Persona p = new Persona(name, surname, dob);
                if (manager.Del(p)) {
                    JOptionPane.showMessageDialog(frame, "Person deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Person not found.");
                }
                updateTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            }
        }
    }

    private class SearchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = searchField.getText().toLowerCase();
            tableModel.setRowCount(0); // Clear existing rows
            List<Persona> filteredPeople = manager.getAll().stream()
                    .filter(p -> p.getNome().toLowerCase().contains(searchTerm) ||
                            p.getCognome().toLowerCase().contains(searchTerm))
                    .collect(Collectors.toList());
            for (Persona persona : filteredPeople) {
                tableModel.addRow(new Object[]{persona.getNome(), persona.getCognome(), persona.getDoB()});
            }
        }
    }

    private class SortByNameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            manager.sortByName();
            updateTable();
        }
    }

    private class SortBySurnameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            manager.sortBySurname();
            updateTable();
        }
    }

    private class SortByDobAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            manager.sortByDob();
            updateTable();
        }
    }
}