import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PaginationExample {
    private JFrame frame;
    private JTable table;
    private JButton prevButton;
    private JButton nextButton;
    private int currentPage = 1;
    private int rowsPerPage = 20;

    private List<String[]> data = new ArrayList<>(); // Sample data source

    public PaginationExample() {
        frame = new JFrame("Pagination Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 1; i <= 200; i++) {
            data.add(new String[]{"Row " + i, "Data " + i});
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Column 1");
        model.addColumn("Column 2");

        table = new JTable(model);

        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateTableData();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage < getTotalPages()) {
                    currentPage++;
                    updateTableData();
                }
            }
        });

        JPanel navPanel = new JPanel();
        navPanel.add(prevButton);
        navPanel.add(nextButton);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(navPanel, BorderLayout.SOUTH);

        frame.setSize(600, 400);
        frame.setVisible(true);

        updateTableData();
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) data.size() / rowsPerPage);
    }

    private void updateTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        int startIndex = (currentPage - 1) * rowsPerPage;
        int endIndex = Math.min(startIndex + rowsPerPage, data.size());

        for (int i = startIndex; i < endIndex; i++) {
            model.addRow(data.get(i));
        }

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
                new PaginationExample();
            }
        });
    }
}
