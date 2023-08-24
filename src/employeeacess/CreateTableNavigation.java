package employeeacess;

import model.Customer;
import model.Person;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CreateTableNavigation {
    private JFrame frame;
    private JTable table;
    private CustomerTableModel tableModel;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel pageInfoLabel;
    private int currentPage = 1;
    private int rowsPerPage = 30;
    private DataSource dataSource = new DataSource();

    private List<Customer> data; // Sample data source

    public CreateTableNavigation(int rowsPerPage) {
        if (rowsPerPage > 0) {
            this.rowsPerPage = rowsPerPage;
        }

        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        tableModel = new CustomerTableModel(new String[]{"NIF", "Name", "Birth Date", "Address", "License number", "License Category",
                "Registration Date", "Expiration Date", "Email"});

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        frame = new JFrame("Customer Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        data = dataSource.queryCustomers();
//        data.forEach(System.out::println);

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
        navPanel.add(pageInfoLabel);
        navPanel.add(nextButton);


        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(navPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 800);
        frame.setVisible(true);

        updateTable();
    }

    private void updateTable() {
        tableModel.updateData(currentPage, rowsPerPage);
        pageInfoLabel.setText(String.format("Page %d of %d", currentPage, getTotalPages()));
        updateButtonState();
    }

    private void updateButtonState() {
        prevButton.setEnabled(currentPage > 1);
        nextButton.setEnabled(currentPage < getTotalPages());
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) data.size() / rowsPerPage);
    }

    public void dataSort(int order) {
        if (order == 1) {
            data.sort(Comparator.comparing(Customer::getNif));
        } else if (order == 2) {
            data.sort(Comparator.comparing(Person::getName));
        } else if (order == 3) {
            data.sort(Comparator.comparing(Person::getBirht_date));
        } else if (order == 4) {
            data.sort(Comparator.comparing(Person::getAddress));
        } else if (order == 5) {
            data.sort(Comparator.comparing(Customer::getDriverLicenseNum));
        } else if (order == 6) {
            data.sort(Comparator.comparing(Customer::getLicenseType));
        } else if (order == 7) {
            data.sort(Comparator.comparing(Customer::getStartingDate));
        } else if (order == 8) {
            data.sort(Comparator.comparing(Customer::getExpDate));
        } else if (order == 9) {
            data.sort(Comparator.comparing(Customer::getEmail));
        }
    }

    public void dataFilter(int numericFilter, int order) {
        List<Customer> customerList = new ArrayList<>();
        for (Customer customer : data) {
            if (customer.getNif() == numericFilter) {
                customerList.add(customer);
            } else if (customer.getDriverLicenseNum() == numericFilter) {
                customerList.add(customer);
            }
        }
        data = customerList;
        dataSort(order);
    }

    public void dataStringFilter(int order, String stringFilter, String filterType) {
        List<Customer> customerList = new ArrayList<>();

        switch (filterType) {
            case "Name" -> {
                for (Customer customer : data) {
                    if (customer.getName().contains(stringFilter)) {
                        customerList.add(customer);
                    }
                }
            }
            case "Address" -> {
                for (Customer customer : data) {
                    if (customer.getAddress().equals(stringFilter)) {
                        customerList.add(customer);
                    }
                }
            }
            case "License Type" -> {
                for (Customer customer : data) {
                    if (customer.getLicenseType().equals(stringFilter)) {
                        customerList.add(customer);
                    }
                }
            }
            case "Email" -> {
                for (Customer customer : data) {
                    if (customer.getEmail().equals(stringFilter)) {
                        customerList.add(customer);
                    }
                }
            }
        }
        data = customerList;
        dataSort(order);
    }

    public void dataDateFilter(int order, Date dateFilter, String filterType) {
        List<Customer> customerList = new ArrayList<>();

        switch (filterType) {
            case "Birth Date" -> {
                for (Customer customer : data) {
                    if (customer.getBirht_date().equals(dateFilter)) {
                        customerList.add(customer);
                    }
                }
            }
            case "Registration Date" -> {
                for (Customer customer : data) {
                    if (customer.getStartingDate().equals(dateFilter)) {
                        customerList.add(customer);
                    }
                }
            }
            case "Expiration Date" -> {
                for (Customer customer : data) {
                    if (customer.getExpDate().equals(dateFilter)) {
                        customerList.add(customer);
                    }
                }
            }
        }
        data = customerList;
        dataSort(order);
    }


    class CustomerTableModel extends AbstractTableModel {
        private final String[] columnNames;
        private List<Customer> customersRow;

        public CustomerTableModel(String[] columnNames) {
            customersRow = new ArrayList<>();
            this.columnNames = columnNames;
        }

        @Override
        public int getRowCount() {
            return customersRow.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Customer customer = customersRow.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> customer.getNif();
                case 1 -> customer.getName();
                case 2 -> customer.getBirht_date();
                case 3 -> customer.getAddress();
                case 4 -> customer.getDriverLicenseNum();
                case 5 -> customer.getLicenseType();
                case 6 -> customer.getStartingDate();
                case 7 -> customer.getExpDate();
                case 8 -> customer.getEmail();
                default -> null;
            };
        }

        private void updateData(int currentPage, int rowsPerPage) {
            customersRow.clear();
            int start = (currentPage - 1) * rowsPerPage;


            int end = Math.min(start + rowsPerPage, data.size());
            for (int i = start; i < end; i++) {

                customersRow.add(data.get(i));
            }
            fireTableDataChanged();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateTableNavigation(1);
            }
        });
    }
}