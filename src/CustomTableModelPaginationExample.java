import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CustomTableModelPaginationExample {
    private JFrame frame;
    private JTable table;
    private CustomTableModel tableModel;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel pageInfoLabel;
    private int currentPage = 1;
    private int rowsPerPage = 20;

    private List<Object[]> data = new ArrayList<>(); // Sample data source

    public CustomTableModelPaginationExample() {
        frame = new JFrame("Custom TableModel Pagination Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 1; i <= 200; i++) {
            data.add(new Object[]{
                    i * 100000000, "Name " + i, "Address " + i,
                    "email" + i + "@example.com", new Date(System.currentTimeMillis()),
                    i * 10000000, "B", new Date(System.currentTimeMillis())
            });
        }

        tableModel = new CustomTableModel(new String[]{
                "NIF", "Name", "Address", "Email", "Birth Date",
                "Driver License", "License Category", "Registration Date"
        });

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        pageInfoLabel = new JLabel();

        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                updateTable();
            }
        });

        nextButton.addActionListener(e -> {
            if (currentPage < getTotalPages()) {
                currentPage++;
                updateTable();
            }
        });

        JPanel navPanel = new JPanel();
        navPanel.add(prevButton);
        navPanel.add(nextButton);
        navPanel.add(pageInfoLabel);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(navPanel, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setVisible(true);

        updateTable();
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) data.size() / rowsPerPage);
    }

    private void updateTable() {
        tableModel.updateData(currentPage, rowsPerPage);
        pageInfoLabel.setText("Page " + currentPage + " of " + getTotalPages());
        updateButtonState();
    }

    private void updateButtonState() {
        prevButton.setEnabled(currentPage > 1);
        nextButton.setEnabled(currentPage < getTotalPages());

    }

    class CustomTableModel extends AbstractTableModel {
        private String[] columnNames;
        private List<Object[]> rowData;

        public CustomTableModel(String[] columnNames) {
            this.columnNames = columnNames;
            rowData = new ArrayList<>();
        }

        public void updateData(int currentPage, int rowsPerPage) {
            rowData.clear();
            int startIndex = (currentPage - 1) * rowsPerPage;
            int endIndex = Math.min(startIndex + rowsPerPage, data.size());

            for (int i = startIndex; i < endIndex; i++) {
                rowData.add(data.get(i));
            }

            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return rowData.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rowData.get(rowIndex)[columnIndex];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomTableModelPaginationExample();
            }
        });
    }
}
