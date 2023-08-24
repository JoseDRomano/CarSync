import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JTablePaginationExample {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel pageInfoLabel;
    private int currentPage = 1;
    private int rowsPerPage = 20;

    private List<String[]> data = new ArrayList<>(); // Sample data source

    public JTablePaginationExample() {
        frame = new JFrame("JTable Pagination Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 1; i <= 200; i++) {
            data.add(new String[]{String.valueOf(i), "Data " + i});
        }

        tableModel = new DefaultTableModel(new String[]{"ID", "Data"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        pageInfoLabel = new JLabel();

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateTable();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage < getTotalPages()) {
                    currentPage++;
                    updateTable();
                }
            }
        });

        JPanel navPanel = new JPanel();
        navPanel.add(prevButton);
        navPanel.add(nextButton);
        navPanel.add(pageInfoLabel);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(navPanel, BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setVisible(true);

        updateTable();
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) data.size() / rowsPerPage);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        int startIndex = (currentPage - 1) * rowsPerPage;
        int endIndex = Math.min(startIndex + rowsPerPage, data.size());

        for (int i = startIndex; i < endIndex; i++) {
            tableModel.addRow(data.get(i));
        }

        pageInfoLabel.setText("Page " + currentPage + " of " + getTotalPages());
        updateButtonState();
    }

    private void updateButtonState() {
        prevButton.setEnabled(currentPage > 1);
        nextButton.setEnabled(currentPage < getTotalPages());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JTablePaginationExample();
            }
        });
    }
}
