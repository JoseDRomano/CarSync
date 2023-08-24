import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Database Table Example with TableModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a custom TableModel with sample data
        TableModel tableModel = new CustomerTableModel();

        // Create a JTable using the custom TableModel
        JTable table = new JTable(tableModel);

        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the JScrollPane to the JFrame
        frame.add(scrollPane);

        frame.pack();
        frame.setVisible(true);
    }

    // Custom TableModel that extends AbstractTableModel
    private static class CustomerTableModel extends AbstractTableModel {
        private final String[] columnNames = {"ID", "Name", "Email"};
        private final List<Customer> customers = new ArrayList<>(); // Sample data

        // Constructor to populate sample data
        public CustomerTableModel() {
            customers.add(new Customer(1, "John Doe", "john@example.com"));
            customers.add(new Customer(2, "Alice Smith", "alice@example.com"));
            customers.add(new Customer(3, "Bob Johnson", "bob@example.com"));
        }

        @Override
        public int getRowCount() {
            return customers.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Customer customer = customers.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return customer.getId();
                case 1:
                    return customer.getName();
                case 2:
                    return customer.getEmail();
                default:
                    return null;
            }
        }
    }

    // Sample Customer class
    private static class Customer {
        private int id;
        private String name;
        private String email;

        public Customer(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
