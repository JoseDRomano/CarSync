package employeeacess;

import model.Customer;
import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.awt.Color.BLACK;

public class BackOfficeAdminMenu extends JFrame implements ValidateInput {

    private Employee employee;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    private DataSource dataSource;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton insertMenu = new JButton("Insert Menu");
    private JButton updateMenu = new JButton("Update Menu");
    private JButton deleteMenu = new JButton("Delete Menu");
    private final JButton searchMenu = new JButton("Search Menu");
    private final JButton deactivateMenu = new JButton("Deactivate Menu");
    private final Color GREEN = new Color(0, 100, 0);
    private final Color RED = new Color(130, 0, 0);
    private final Color WHITE = new Color(255, 255, 255);


    public BackOfficeAdminMenu(Employee employee) {
        dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }
        this.employee = employee;
        mainFrame = new JFrame("Back Office Menu" + "\n" + "Welcome " + employee.getName());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setBackground(Color.WHITE);
        mainFrame.setLayout(new BorderLayout());
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(130, 0, 0));
        exitButton.addActionListener(e -> System.exit(0));

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel mainLabel = new JLabel("Back Office Menu");
        mainLabel.setFont(new Font("Arial", Font.BOLD, 80));
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setForeground(BLACK);
        mainPanel.add(mainLabel, gbc);

        JButton[] buttons = {insertMenu, deleteMenu, deactivateMenu, updateMenu, searchMenu, exitButton};
        gbc.gridwidth = 2;
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(GREEN);
            button.setSize(50, 50);
            button.setForeground(Color.WHITE);
            gbc.gridy++;
            mainPanel.add(button, gbc);
        }

        gbc.anchor = GridBagConstraints.CENTER;

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.pack();

        ActionListener goToPageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                mainFrame.setVisible(false);

                if (button == insertMenu) buildInsertMenuPage();
                else if (button == deleteMenu) buildDeleteMenuPage();
                else if (button == deactivateMenu) buildDeactivateMenuPage();
                else if (button == updateMenu) buildUpdateMenuPage();
                else if (button == searchMenu) buildSearchMenuPage();
            }
        };

        insertMenu.addActionListener(goToPageListener);
        deactivateMenu.addActionListener(goToPageListener);
        deleteMenu.addActionListener(goToPageListener);
        updateMenu.addActionListener(goToPageListener);
        searchMenu.addActionListener(goToPageListener);
        exitButton.addActionListener(e -> {
            dataSource.close();
            System.exit(0);
        });
    }

    private void buildSearchMenuPage() {
        JFrame searchMenuFrame = new JFrame("Search Menu");
        searchMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchMenuFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton vehicleDisplay = new JButton("Vehicle Display Menu");
        JButton employeeDisplay = new JButton("Employee Display Menu");
        JButton customerDisplay = new JButton("Customer Display Menu");
        JButton ticketDisplay = new JButton("Ticket Display Menu");
        JButton insuranceDisplay = new JButton("Insurance Display Menu");
        JPanel searchMenuPanel = new JPanel();
        searchMenuPanel.setLayout(new GridLayout(7, 1));
        searchMenuPanel.add(vehicleDisplay);
        searchMenuPanel.add(employeeDisplay);
        searchMenuPanel.add(customerDisplay);
        searchMenuPanel.add(ticketDisplay);
        searchMenuPanel.add(insuranceDisplay);
        searchMenuPanel.add(backButton);
        searchMenuPanel.add(exitButton);
        searchMenuFrame.add(searchMenuPanel);
        mainFrame.setVisible(false);
        searchMenuFrame.setVisible(true);
        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            searchMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            dataSource.close();
            System.exit(0);
        });

        ActionListener goToPageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                searchMenuFrame.setVisible(false);

                if (button == vehicleDisplay) vehicleDisplayPage(searchMenuFrame);
                else if (button == employeeDisplay) employeeDisplayPage(searchMenuFrame);
                else if (button == customerDisplay) customerDisplayPage(searchMenuFrame);
                else if (button == ticketDisplay) ticketDisplayPage(searchMenuFrame);
                else if (button == insuranceDisplay) insuranceDisplayPage(searchMenuFrame);
            }
        };

        vehicleDisplay.addActionListener(goToPageListener);
        employeeDisplay.addActionListener(goToPageListener);
        customerDisplay.addActionListener(goToPageListener);
        ticketDisplay.addActionListener(goToPageListener);
        insuranceDisplay.addActionListener(goToPageListener);

    }

    private void insuranceDisplayPage(JFrame searchMenuFrame) {
        JFrame insuranceDisplayFrame = new JFrame("Insurance Display Menu");
        insuranceDisplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel insuranceDisplayPanel = new JPanel(new GridBagLayout());

        JLabel insuranceDisplayLabel = new JLabel("Insurance Display Menu");
        insuranceDisplayLabel.setFont(new Font("Arial", Font.BOLD, 50));
        insuranceDisplayLabel.setForeground(new Color(0, 0, 90));
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        insuranceDisplayPanel.add(insuranceDisplayLabel, gbc);

        JComboBox<String> insuranceDisplaySearchOptions = new JComboBox<>(new String[]{" ", "Search by Policy Number",
                "Search by Customer ID", "Search by Vehicle ID",
                "Search by Name"});

        JButton backButton = new JButton("Back");
        JButton exitButton = new JButton("Exit");
        JButton searchButton = new JButton("Search");
        exitButton.setBackground(RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            insuranceDisplayFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        backButton.setBackground(BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            searchMenuFrame.setVisible(true);
            insuranceDisplayFrame.setVisible(false);
        });

    }

    private void ticketDisplayPage(JFrame searchMenuFrame) {

    }

    private void customerDisplayPage(JFrame searchMenuFrame) {
        JFrame customerDisplayFrame = new JFrame("Customer Search Menu");
        customerDisplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel customerDisplayPanel = new JPanel(new GridBagLayout());

        JLabel customerDisplayLabel = new JLabel("Customer Search Menu");
        customerDisplayLabel.setFont(new Font("Arial", Font.BOLD, 50));
        customerDisplayLabel.setForeground(new Color(0, 0, 90));
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        customerDisplayPanel.add(customerDisplayLabel, gbc);

        JLabel rowsPerPageLabel = new JLabel("Rows per page:");
        JComboBox<String> rowsPerPageOptions = new JComboBox<>(new String[]{"20", "30", "50", "100"});

        JLabel customerDisplaySearchLabel = new JLabel("Search by:");
        JComboBox<String> customerDisplaySearchOptions = new JComboBox<>(new String[]{" ", "General Search",
                "Search by NIF", "Search by Name", "Search by Address", "Search by License Type",
                "Search by License Number"});

        JLabel inputForSearch = new JLabel("Input for search:");
        JTextField inputForSearchField = new JTextField(20);

        JLabel customerDisplayOrderLabel = new JLabel("Order by:");
        JComboBox<String> customerDisplayOrderOptions = new JComboBox<>(new String[]{" ", "Order by NIF",
                "Order by Name", "Order by Address", "Order by License Type", "Order by License Number",
                "Order by Date of Birth", "Order by Date of License Issue", "Order by Date of License Expiration"});

        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(GREEN);
        searchButton.addActionListener(e -> {
            String searchOption = (String) customerDisplaySearchOptions.getSelectedItem();
            String ordertType = (String) customerDisplayOrderOptions.getSelectedItem();
            String rowsPerPage = (String) rowsPerPageOptions.getSelectedItem();
            String input = inputForSearchField.getText();

            if (searchOption.isBlank() || searchOption.isBlank() || ordertType.isEmpty() || ordertType.isBlank()) {
                JOptionPane.showMessageDialog(customerDisplayFrame, "Please select a search option and an order option");
            } else if (!isInteger(rowsPerPage)) {
                JOptionPane.showMessageDialog(customerDisplayFrame, "Please select the number of rows per page");
            } else {
                boolean result = true;
                while (result) {
                    switch (searchOption) {
                        case "General Search" -> {
                            displaySearchByOrderCustomer(rowsPerPage,
                                    dataSource.queryCustomers(), customerDisplayFrame, ordertType);
                            result = false;
                        }
                        case "Search by NIF" -> {
                            if (!isNIF(input)) {
                                JOptionPane.showMessageDialog(customerDisplayFrame, "Please enter a valid NIF");
                            } else {
                                List<Customer> customerList = new ArrayList<>();
                                customerList.add(dataSource.getCustomerByNIF(Integer.parseInt(input)));
                                CustomerTableNavigation customerTableNavigation = new CustomerTableNavigation(
                                        Integer.parseInt(rowsPerPage), customerDisplayFrame, customerList);
                                result = false;
                            }
                        }

                        case "Search by Name" -> {
                            if (!isValidString(input)) {
                                JOptionPane.showMessageDialog(customerDisplayFrame, "Please enter a valid name");
                            } else {
                                List<Customer> customerList = new ArrayList<>();
                                dataSource.queryCustomers().forEach(customer -> {
                                    if (customer.getName().contains(input)) {
                                        customerList.add(customer);
                                    }
                                });
                                displaySearchByOrderCustomer(rowsPerPage, customerList, customerDisplayFrame, ordertType);
                                result = false;
                            }
                        }

                        case "Search by Address" -> {
                            if (!isValidString(input)) {
                                JOptionPane.showMessageDialog(customerDisplayFrame, "Please enter a valid name");
                            } else {
                                List<Customer> customerList = new ArrayList<>();
                                dataSource.queryCustomers().forEach(customer -> {
                                    if (customer.getAddress().contains(input)) {
                                        customerList.add(customer);
                                    }
                                });
                                displaySearchByOrderCustomer(rowsPerPage, customerList, customerDisplayFrame, ordertType);
                                result = false;
                            }
                        }

                        case "Search by License Type" -> {
                            if (!isValidString(input)) {
                                JOptionPane.showMessageDialog(customerDisplayFrame, "Please enter a valid license type");
                            } else {
                                List<Customer> customerList = new ArrayList<>();
                                dataSource.queryCustomers().forEach(customer -> {
                                    if (customer.getLicenseType().equals(input)) {
                                        customerList.add(customer);
                                    }
                                });
                                displaySearchByOrderCustomer(rowsPerPage, customerList, customerDisplayFrame, ordertType);
                                result = false;
                            }
                        }

                        case "Search by License Number" -> {
                            if (!isDriverLicense(input)) {
                                JOptionPane.showMessageDialog(customerDisplayFrame, "Please enter a valid NIF");
                            } else {
                                List<Customer> customerList = new ArrayList<>();
                                customerList.add(dataSource.getCustomerByNIF(Integer.parseInt(input)));
                                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                                        customerDisplayFrame, customerList);
                                result = false;
                            }
                        }
                        default -> JOptionPane.showMessageDialog(customerDisplayFrame, "Please select a search option");
                    }
                    //Method will get here when switch is exited.
                }
            }
        });


        gbc.gridwidth = 1;
        JLabel[] labels = {rowsPerPageLabel, customerDisplaySearchLabel, customerDisplayOrderLabel, inputForSearch};
        JComboBox[] comboBoxes = {rowsPerPageOptions, customerDisplaySearchOptions, customerDisplayOrderOptions};

        for (int i = 0; i < labels.length; i++) {
           /* gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.gridy++;
            insuranceDisplayPanel.add(labels[i], gbc);
            gbc.gridx++;
            insuranceDisplayPanel.add(comboBoxes[i], gbc);*/

            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            customerDisplayPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            if (i == 3) {
                customerDisplayPanel.add(inputForSearchField, gbc);
            } else {
                customerDisplayPanel.add(comboBoxes[i], gbc);
            }
        }

        JButton backButton = new JButton("Back");
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            customerDisplayFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        backButton.setBackground(BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            searchMenuFrame.setVisible(true);
            customerDisplayFrame.setVisible(false);
        });

        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        customerDisplayPanel.add(searchButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        customerDisplayPanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(20, 10, 5, 10);
        customerDisplayPanel.add(backButton, gbc);


        customerDisplayFrame.add(customerDisplayPanel);
        customerDisplayFrame.pack();
        customerDisplayFrame.setVisible(true);

        customerDisplayFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataSource.close();
            }
        });

    }

    private void displaySearchByOrderCustomer(String rowsPerPage, List<Customer> customerList,
                                              JFrame customerDisplayFrame, String orderType) {
        switch (orderType) {
            case "Order by NIF":
                customerList.sort(Comparator.comparing(Customer::getNif));
                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                        customerDisplayFrame, customerList);
                break;
            case "Order by Name":
                customerList.sort(Comparator.comparing(Customer::getName));
                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                        customerDisplayFrame, customerList);
                break;
            case "Order by Address":
                customerList.sort(Comparator.comparing(Customer::getAddress));
                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                        customerDisplayFrame, customerList);
                break;
            case "Order by License Type":
                customerList.sort(Comparator.comparing(Customer::getLicenseType));
                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                        customerDisplayFrame, customerList);
                break;
            case "Order by License Number":
                customerList.sort(Comparator.comparing(Customer::getDriverLicenseNum));
                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                        customerDisplayFrame, customerList);
                break;
            case "Order by Date of Birth":
                customerList.sort(Comparator.comparing(Customer::getBirht_date));
                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                        customerDisplayFrame, customerList);
                break;
            case "Order by Date of License Issue":
                customerList.sort(Comparator.comparing(Customer::getStartingDate));
                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                        customerDisplayFrame, customerList);
                break;
            case "Order by Date of License Expiration":
                customerList.sort(Comparator.comparing(Customer::getExpDate));
                new CustomerTableNavigation(Integer.parseInt(rowsPerPage),
                        customerDisplayFrame, customerList);
                break;
        }

    }

    private void employeeDisplayPage(JFrame searchMenuFrame) {
        JFrame employeeDisplayFrame = new JFrame("Employee Search Menu");
        employeeDisplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel employeeDisplayPanel = new JPanel(new GridBagLayout());

        JLabel customerDisplayLabel = new JLabel("Employee Search Menu");
        customerDisplayLabel.setFont(new Font("Arial", Font.BOLD, 50));
        customerDisplayLabel.setForeground(new Color(0, 0, 90));
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        employeeDisplayPanel.add(customerDisplayLabel, gbc);

        JLabel rowsPerPageLabel = new JLabel("Rows per page:");
        JComboBox<String> rowsPerPageOptions = new JComboBox<>(new String[]{"20", "30", "50", "100"});

        JLabel employeeDisplaySearchLabel = new JLabel("Search by:");
        JComboBox<String> employeeDisplaySearchOptions = new JComboBox<>(new String[]{" ", "General Search",
                "Search by NIF", "Search by Name", "Search by Address", "Search by Access Level"});

        JLabel inputForSearch = new JLabel("Input for search:");
        JTextField inputForSearchField = new JTextField(20);

        JLabel employeeDisplayOrderLabel = new JLabel("Order by:");
        JComboBox<String> employeeDisplayOrderOptions = new JComboBox<>(new String[]{" ", "Order by NIF",
                "Order by Name", "Order by Address", "Order by Access Level", "Order by Date of Birth"});

        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(GREEN);
        searchButton.addActionListener(e -> {
            String searchOption = (String) employeeDisplaySearchOptions.getSelectedItem();
            String orderType = (String) employeeDisplayOrderOptions.getSelectedItem();
            String rowsPerPage = (String) rowsPerPageOptions.getSelectedItem();
            String input = inputForSearchField.getText();

            if (searchOption.isBlank() || searchOption.isBlank() || orderType.isEmpty() || orderType.isBlank()) {
                JOptionPane.showMessageDialog(employeeDisplayFrame, "Please select a search option and an order option");
            } else if (!isInteger(rowsPerPage)) {
                JOptionPane.showMessageDialog(employeeDisplayFrame, "Please select a search option and an order option");
            } else {
                boolean result = true;
                while (result) {
                    switch (searchOption) {
                        case "General Search" -> {
                            displaySearchByOrderEmployee(rowsPerPage, dataSource.queryEmployees(),
                                    employeeDisplayFrame, orderType);
                            result = false;
                        }
                        case "Search NIF" -> {
                            if (!isNIF(input)) {
                                JOptionPane.showMessageDialog(employeeDisplayFrame, "Please enter a valid NIF");
                            } else {
                                List<Employee> employees = new ArrayList<>();
                                for (Employee employee1 : dataSource.queryEmployees()) {
                                    if (employee1.getNif() == Integer.parseInt(input)) {
                                        employees.add(employee1);
                                        result = false;
                                        break;
                                    }
                                }
                            }
                        }
                        case "Search by Name" -> {
                            if (!isValidString(input)) {
                                JOptionPane.showMessageDialog(employeeDisplayFrame, "Please enter a valid name");
                            } else {
                                List<Employee> employees = new ArrayList<>();
                                for (Employee employee1 : dataSource.queryEmployees()) {
                                    if (employee1.getName().equals(input)) {
                                        employees.add(employee1);
                                        result = false;
                                        break;
                                    }
                                }
                            }
                        }
                        case "Search by Address" -> {
                            if (!isValidString(input)) {
                                JOptionPane.showMessageDialog(employeeDisplayFrame, "Please enter a valid address");
                            } else {
                                List<Employee> employees = new ArrayList<>();
                                for (Employee employee1 : dataSource.queryEmployees()) {
                                    if (employee1.getAddress().equals(input)) {
                                        employees.add(employee1);
                                        result = false;
                                        break;
                                    }
                                }
                            }
                        }
                        case "Search by Access Level" -> {
                            if (!isInteger(input)) {
                                JOptionPane.showMessageDialog(employeeDisplayFrame, "Please enter a valid access level");
                            } else {
                                List<Employee> employees = new ArrayList<>();
                                for (Employee employee1 : dataSource.queryEmployees()) {
                                    if (employee1.getAccess_level() == Integer.parseInt(input)) {
                                        employees.add(employee1);
                                        result = false;
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        });

        gbc.gridwidth = 1;
        JLabel[] labels = {rowsPerPageLabel, employeeDisplaySearchLabel, employeeDisplayOrderLabel, inputForSearch};
        JComboBox[] comboBoxes = {rowsPerPageOptions, employeeDisplaySearchOptions, employeeDisplayOrderOptions};

        for (int i = 0; i < labels.length; i++) {
            /*gbc.gridx = 0;
            gbc.gridy++;
            employeeDisplayPanel.add(labels[i], gbc);
            gbc.gridx = 1;
            employeeDisplayPanel.add(comboBoxes[i], gbc);*/

            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            employeeDisplayPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            if (i == 3) {
//                gbc.fill = GridBagConstraints.HORIZONTAL;
                employeeDisplayPanel.add(inputForSearchField, gbc);
            } else {
//                gbc.fill = GridBagConstraints.NONE;
                employeeDisplayPanel.add(comboBoxes[i], gbc);
            }
        }

        JButton backButton = new JButton("Back");
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            employeeDisplayFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        backButton.setBackground(BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            searchMenuFrame.setVisible(true);
            employeeDisplayFrame.setVisible(false);
        });

        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        employeeDisplayPanel.add(searchButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        employeeDisplayPanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(20, 10, 5, 10);
        employeeDisplayPanel.add(backButton, gbc);

        employeeDisplayFrame.add(employeeDisplayPanel);
        employeeDisplayFrame.pack();
        employeeDisplayFrame.setVisible(true);

        employeeDisplayFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataSource.close();
            }
        });
    }

    private void displaySearchByOrderEmployee(String rowsPerPage, List<Employee> employees, JFrame employeeDisplayFrame, String orderType) {
        switch (orderType) {
            case "Order by NIF":
                employees.sort(Comparator.comparing(Employee::getNif));
                new EmployeeTableNavigation(Integer.parseInt(rowsPerPage),
                        employeeDisplayFrame, employees);
                break;
            case "Order by Name":
                employees.sort(Comparator.comparing(Employee::getName));
                new EmployeeTableNavigation(Integer.parseInt(rowsPerPage),
                        employeeDisplayFrame, employees);
                break;
            case "Order by Address":
                employees.sort(Comparator.comparing(Employee::getAddress));
                new EmployeeTableNavigation(Integer.parseInt(rowsPerPage),
                        employeeDisplayFrame, employees);
                break;
            case "Order by Access Level":
                employees.sort(Comparator.comparing(Employee::getAccess_level));
                new EmployeeTableNavigation(Integer.parseInt(rowsPerPage),
                        employeeDisplayFrame, employees);
                break;
            case "Order by Date of Birth":
                employees.sort(Comparator.comparing(Employee::getBirht_date));
                new EmployeeTableNavigation(Integer.parseInt(rowsPerPage),
                        employeeDisplayFrame, employees);
                break;
        }
    }

    private void vehicleDisplayPage(JFrame searchMenuFrame) {
    }

    private void buildUpdateMenuPage() {
        JFrame updateMenuFrame = new JFrame("Update Menu");
        updateMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateMenuFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton vehicleDisplay = new JButton("Vehicle Update Menu");
        JButton employeeDisplay = new JButton("Employee Update Menu");
        JButton customerDisplay = new JButton("Customer Update Menu");
        JButton ticketDisplay = new JButton("Ticket Update Menu");
        JButton insuranceDisplay = new JButton("Insurance Update Menu");
        JPanel updateMenuPanel = new JPanel();
        updateMenuPanel.setLayout(new GridLayout(7, 1));
        updateMenuPanel.add(vehicleDisplay);
        updateMenuPanel.add(employeeDisplay);
        updateMenuPanel.add(customerDisplay);
        updateMenuPanel.add(ticketDisplay);
        updateMenuPanel.add(insuranceDisplay);
        updateMenuPanel.add(backButton);
        updateMenuPanel.add(exitButton);
        updateMenuFrame.add(updateMenuPanel);
        mainFrame.setVisible(false);
        updateMenuFrame.setVisible(true);
        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            updateMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            dataSource.close();
            System.exit(0);
        });
    }

    private void buildDeactivateMenuPage() {
        JFrame deactivateMenuFrame = new JFrame("Deactivation Menu");
        deactivateMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deactivateMenuFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton vehicleDeactivation = new JButton("Vehicle Deactivation Menu");
        JButton employeeDeactivation = new JButton("Employee Deactivation Menu");
        JButton customerDeactivation = new JButton("Customer Deactivation Menu");
        JButton ticketDeactivation = new JButton("Ticket Deactivation Menu");
        JButton insuranceDeactivation = new JButton("Insurance Deactivation Menu");
        JPanel deactivationMenuPanel = new JPanel();
        deactivationMenuPanel.setLayout(new GridLayout(7, 1));
        deactivationMenuPanel.add(vehicleDeactivation);
        deactivationMenuPanel.add(employeeDeactivation);
        deactivationMenuPanel.add(customerDeactivation);
        deactivationMenuPanel.add(ticketDeactivation);
        deactivationMenuPanel.add(insuranceDeactivation);
        deactivationMenuPanel.add(backButton);
        deactivationMenuPanel.add(exitButton);
        deactivateMenuFrame.add(deactivationMenuPanel);
        mainFrame.setVisible(false);
        deactivateMenuFrame.setVisible(true);
        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            deactivateMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            dataSource.close();
            System.exit(0);
        });
    }

    private void buildDeleteMenuPage() {
        JFrame deleteMenuFrame = new JFrame("Delete Menu");
        deleteMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteMenuFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton vehicleDelete = new JButton("Delete Vehicle Menu");
        JButton employeeDelete = new JButton("Delete Employee Menu");
        JButton customerDelete = new JButton("Delete Customer Menu");
        JButton ticketDelete = new JButton("Delete Ticket Menu");
        JButton insuranceDelete = new JButton("Delete Insurance Menu");
        JPanel deleteMenuPanel = new JPanel();
        deleteMenuPanel.setLayout(new GridLayout(7, 1));
        deleteMenuPanel.add(vehicleDelete);
        deleteMenuPanel.add(employeeDelete);
        deleteMenuPanel.add(customerDelete);
        deleteMenuPanel.add(ticketDelete);
        deleteMenuPanel.add(insuranceDelete);
        deleteMenuPanel.add(backButton);
        deleteMenuPanel.add(exitButton);
        deleteMenuFrame.add(deleteMenuPanel);
        mainFrame.setVisible(false);
        deleteMenuFrame.setVisible(true);
        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            deleteMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            dataSource.close();
            System.exit(0);
        });
    }

    private void buildInsertMenuPage() {
        JFrame insertMenuFrame = new JFrame("Insert Menu");
        insertMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertMenuFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton vehicleInsert = new JButton("Insert Vehicle Menu");
        JButton employeeInsert = new JButton("Insert Employee Menu");
        JButton customerInsert = new JButton("Insert Customer Menu");
        JButton ticketInsert = new JButton("Insert Ticket Menu");
        JButton insuranceInsert = new JButton("Insert Insurance Menu");
        JPanel insertMenuPanel = new JPanel();
        insertMenuPanel.setLayout(new GridLayout(7, 1));
        insertMenuPanel.add(vehicleInsert);
        insertMenuPanel.add(employeeInsert);
        insertMenuPanel.add(customerInsert);
        insertMenuPanel.add(ticketInsert);
        insertMenuPanel.add(insuranceInsert);
        insertMenuPanel.add(backButton);
        insertMenuPanel.add(exitButton);
        insertMenuFrame.add(insertMenuPanel);
        mainFrame.setVisible(false);
        insertMenuFrame.setVisible(true);
        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            insertMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            dataSource.close();
            System.exit(0);
        });
        ActionListener goToPageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                insertMenuFrame.setVisible(false);

                if (button == vehicleInsert) insertVehicle(insertMenuFrame);
                else if (button == employeeInsert) insertEmployee(insertMenuFrame);
                else if (button == customerInsert) insertCustomer(insertMenuFrame);
                else if (button == ticketInsert) insertTicket(insertMenuFrame);
                else if (button == insuranceInsert) insertInsurance(insertMenuFrame);
            }
        };

        vehicleInsert.addActionListener(goToPageListener);
        customerInsert.addActionListener(goToPageListener);
        employeeInsert.addActionListener(goToPageListener);
        ticketInsert.addActionListener(goToPageListener);
        insuranceInsert.addActionListener(goToPageListener);
    }

    private void insertInsurance(JFrame insertMenuFrame) {
        JFrame insertInsuranceFrame = new JFrame("Register Insurance");
        insertInsuranceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertInsuranceFrame.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel insertInsurancePanel = new JPanel(new GridBagLayout());
        insertInsurancePanel.setBackground(Color.WHITE);

        JLabel insertInsuranceLabel = new JLabel("Insert Insurance Page");
        insertInsuranceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        insertInsuranceLabel.setForeground(BLACK);
        gbc.gridwidth = 3;
//      gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        insertInsurancePanel.add(insertInsuranceLabel, gbc);

        JLabel plateLabel = new JLabel("Plate: ");
        JTextField plateField = new JTextField(15);

        JLabel insuranceCategoryLabel = new JLabel("Insurance Category: ");
        JComboBox<String> insuranceCategoryField = new JComboBox<>(new String[]{" ", "Third Party",
                "Third Party Fire and Theft", "Third Party Fire and Auto-Liabitlity", "Comprehensive"});

        JLabel policyLabel = new JLabel("Policy: ");
        JTextField policyField = new JTextField(15);

        JLabel startDateLabel = new JLabel("Start Date: ");
        JTextField startDateField = new JTextField(15);

        JLabel endDateLabel = new JLabel("End Date: ");
        JTextField endDateField = new JTextField(15);

        JLabel companyNameLabel = new JLabel("Company Name: ");
        JTextField companyNameField = new JTextField(15);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {

            while (true) {
                String plate = plateField.getText();
                String insuranceCategory = (String) insuranceCategoryField.getSelectedItem();
                String policy = policyField.getText();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String companyName = companyNameField.getText();

                if (isPlate(plate) && isDate(startDate) && isDate(endDate) && isValidString(companyName) && isPolicy(policy)) {
                    if (dataSource.insertInsurance(Integer.parseInt(policy), plate, Date.valueOf(startDate),
                            insuranceCategory, Date.valueOf(endDate), companyName)) {
                        JOptionPane.showMessageDialog(insertInsuranceFrame, "Ticket successfully registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                        insertInsuranceFrame.setVisible(false);
                        insertInsuranceFrame.dispose();
                        insertInsurance(insertMenuFrame);
//                        break;
                    } else {
                        JOptionPane.showMessageDialog(insertInsuranceFrame, "Error registering ticket", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(insertInsuranceFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        exitButton.setBackground(RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e ->

        {
            insertInsuranceFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        backButton.setBackground(BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e ->

        {
            insertMenuFrame.setVisible(true);
            insertInsuranceFrame.setVisible(false);
        });

        gbc.gridwidth = 1;
        JLabel[] labels = {plateLabel, policyLabel,
                startDateLabel, endDateLabel, companyNameLabel, insuranceCategoryLabel};
        JTextField[] fields = {plateField, policyField,
                startDateField, endDateField, companyNameField};

        for (
                int row = 0;
                row < labels.length; row++) {
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            insertInsurancePanel.add(labels[row], gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            if (row == 5) {
                insertInsurancePanel.add(insuranceCategoryField, gbc);
            } else {
                insertInsurancePanel.add(fields[row], gbc);
            }
        }

        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new

                Insets(20, 10, 5, 10);
        insertInsurancePanel.add(submitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new

                Insets(20, 10, 5, 10);
        insertInsurancePanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new

                Insets(20, 10, 5, 10);
        insertInsurancePanel.add(backButton, gbc);

        insertInsuranceFrame.add(insertInsurancePanel);
        insertInsuranceFrame.pack();
        insertInsuranceFrame.setVisible(true);

        insertInsuranceFrame.addWindowListener(new

                                                       WindowAdapter() {
                                                           @Override
                                                           public void windowClosing(WindowEvent e) {
                                                               dataSource.close();
                                                           }
                                                       });

    }

    private void insertTicket(JFrame insertMenuFrame) {
        JFrame insertTicketFrame = new JFrame("Register Ticket");
        insertTicketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertTicketFrame.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel insertTicketPanel = new JPanel(new GridBagLayout());
        insertTicketPanel.setBackground(Color.WHITE);

        JLabel insertTicketLabel = new JLabel("Insert Ticket Page");
        insertTicketLabel.setFont(new Font("Arial", Font.BOLD, 20));
        insertTicketLabel.setForeground(BLACK);
        gbc.gridwidth = 3;
//      gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        insertTicketPanel.add(insertTicketLabel, gbc);

        JLabel plateLabel = new JLabel("Plate: ");
        JTextField plateField = new JTextField(15);

        JLabel dateLabel = new JLabel("Date: ");
        JTextField dateField = new JTextField(15);

        JLabel expirationDateLabel = new JLabel("Expiration Date: ");
        JTextField expirationDateField = new JTextField(15);

        JLabel valueLabel = new JLabel("Reason: ");
        JTextField valueField = new JTextField(15);

        JLabel nifLabel = new JLabel("NIF: ");
        JTextField nifField = new JTextField(15);

        JLabel reasonLabel = new JLabel("Reason: ");
        JComboBox<String> reasonFiel = new JComboBox<>(new String[]{" ", "Illegal arking",
                "Speeding", "Red light", "Reckless driving", "DUI"});

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            while (true) {
                String plate = plateField.getText();
                String date = dateField.getText();
                String expirationDate = expirationDateField.getText();
                String value = valueField.getText();
                String nif = nifField.getText();
                String reason = (String) reasonFiel.getSelectedItem();

                if (isPlate(plate) && isDate(date) && isDate(expirationDate) && isDouble(value) && isNIF(nif) && reason != " ") {
                    if (dataSource.insertTicket(Integer.parseInt(nif), plate, Date.valueOf(date),
                            reason, Double.parseDouble(value), Date.valueOf(expirationDate))) {
                        JOptionPane.showMessageDialog(insertTicketFrame, "Ticket successfully registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                        insertTicketFrame.setVisible(false);
                        insertTicketFrame.dispose();
                        insertTicket(insertMenuFrame);
//                        break;
                    } else {
                        JOptionPane.showMessageDialog(insertTicketFrame, "Error registering ticket", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(insertTicketFrame, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        exitButton.setBackground(RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            insertTicketFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        backButton.setBackground(BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            insertMenuFrame.setVisible(true);
            insertTicketFrame.setVisible(false);
        });

        gbc.gridwidth = 1;
        JLabel[] labels = new JLabel[]{plateLabel, dateLabel, expirationDateLabel, valueLabel, nifLabel, reasonLabel};
        JTextField[] fields = new JTextField[]{plateField, dateField, expirationDateField, valueField, nifField};

        for (int row = 0; row < 6; row++) {
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            insertTicketPanel.add(labels[row], gbc);
            gbc.gridx = 1;
            gbc.gridy = row + 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            if (row == 5) {
                insertTicketPanel.add(reasonFiel, gbc);
            } else {
                insertTicketPanel.add(fields[row], gbc);
            }
        }

        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertTicketPanel.add(submitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertTicketPanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertTicketPanel.add(backButton, gbc);

        insertTicketFrame.add(insertTicketPanel);
        insertTicketFrame.pack();
        insertTicketFrame.setVisible(true);

        insertTicketFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataSource.close();
            }
        });
    }

    private void insertEmployee(JFrame insertMenuFrame) {
        JFrame insertEmployeeFrame = new JFrame("Insert Employee");
        insertEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertEmployeeFrame.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel insertEmployeePanel = new JPanel(new GridBagLayout());
        insertEmployeePanel.setBackground(Color.WHITE);

        JLabel insertEmployeeLabel = new JLabel("Insert Employee Page");
        insertEmployeeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        insertEmployeeLabel.setForeground(BLACK);
        gbc.gridwidth = 3;
//      gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        insertEmployeePanel.add(insertEmployeeLabel, gbc);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField(15);

        JLabel addressLabel = new JLabel("Address: ");
        JTextField addressField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(15);

        JLabel birthDateLabel = new JLabel("Birth Date: (YYYY-MM-DD)");
        JTextField birthDateField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password: ");
        JTextField passwordField = new JTextField(15);

        JLabel nifLabel = new JLabel("NIF: ");
        JTextField nifField = new JTextField(15);

        JLabel accessLevelLabel = new JLabel("Access Level: ");
        JComboBox<String> accessLevelField = new JComboBox<>(
                new String[]{" ", "0", "1", "2"});

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {

            while (true) {
                String name = nameField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                String birthDate = birthDateField.getText();
                String password = passwordField.getText();
                String nif = nifField.getText();
                String accessLevel = (String) accessLevelField.getSelectedItem();

                if (isValidString(name) && isValidString(address) && isEmail(email) && isValidBirthDate(birthDate) && isValidString(password) && isNIF(nif) && !accessLevel.equals(" ")) {
                    if (dataSource.insertEmployee(Integer.parseInt(nif),
                            name, address, Date.valueOf(birthDate), password,
                            email, Integer.parseInt(accessLevel))) {
                        JOptionPane.showMessageDialog(insertEmployeeFrame, "Employee registered successfully");
                        insertEmployeeFrame.dispose();
                        insertEmployeeFrame.setVisible(false);
                        insertEmployee(insertMenuFrame);
                        //break;
                    } else {
                        JOptionPane.showMessageDialog(insertEmployeeFrame, "Error registering employee");
                    }
                } else {
                    JOptionPane.showMessageDialog(insertEmployeeFrame, "Please fill all the fields");
                    break;
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        exitButton.setBackground(RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            insertEmployeeFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        backButton.setBackground(BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            insertMenuFrame.setVisible(true);
            insertEmployeeFrame.setVisible(false);
        });

        gbc.gridwidth = 1;
        JLabel[] labels = {nameLabel, addressLabel, emailLabel, birthDateLabel,
                passwordLabel, nifLabel, accessLevelLabel};
        JTextField[] fields = {nameField, addressField, emailField, birthDateField,
                passwordField, nifField};

        for (int row = 0; row < 7; row++) {
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            insertEmployeePanel.add(labels[row], gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            if (row == 6) {
                insertEmployeePanel.add(accessLevelField, gbc);
            } else {
                insertEmployeePanel.add(fields[row], gbc);
            }
        }

        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertEmployeePanel.add(submitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertEmployeePanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertEmployeePanel.add(backButton, gbc);

        insertEmployeeFrame.add(insertEmployeePanel);
        insertEmployeeFrame.pack();
        insertEmployeeFrame.setVisible(true);

        insertEmployeeFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataSource.close();
            }
        });
    }

    private void insertCustomer(JFrame insertMenuFrame) {
        JFrame insertCustomerFrame = new JFrame("Insert Customer");
        insertCustomerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertCustomerFrame.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel insertCustomerPanel = new JPanel(new GridBagLayout());
        insertCustomerPanel.setBackground(Color.WHITE);

        JLabel insertCustomerLabel = new JLabel("Insert Customer Page");
        insertCustomerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        insertCustomerLabel.setForeground(BLACK);
        gbc.gridwidth = 3;
//      gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        insertCustomerPanel.add(insertCustomerLabel, gbc);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField(15);

        JLabel addressLabel = new JLabel("Address: ");
        JTextField addressField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email: ([...]@[...].com or [...]@[...].pt");
        JTextField emailField = new JTextField(15);

        JLabel birthDateLabel = new JLabel("Birth Date: (YYYY-MM-DD)");
        JTextField birthDateField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password: ");
        JTextField passwordField = new JTextField(15);

        JLabel nifLabel = new JLabel("NIF: (9 digits)");
        JTextField nifField = new JTextField(15);

        JLabel driverLicenseLabel = new JLabel("Driver License: (8 digits)");
        JTextField driverLicenseField = new JTextField(15);

        JLabel licenseTypeLabel = new JLabel("License Type: (Select an option");
        JComboBox<String> licenseType = new JComboBox<>(new String[]{" ", "A", "B", "C", "D"});

        JLabel licenseDateLabel = new JLabel("License Date: (YYYY-MM-DD)");
        JTextField licenseDateField = new JTextField(15);

        JLabel licenseExpirationLabel = new JLabel("License Expiration: (YYYY-MM-DD)");
        JTextField licenseExpirationField = new JTextField(15);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {

            while (true) {
                String name = nameField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                String birthDate = birthDateField.getText();
                String password = passwordField.getText();
                String nif = nifField.getText();
                String driverLicense = driverLicenseField.getText();
                String selectedLicenseType = (String) licenseType.getSelectedItem();
                String licenseDate = licenseDateField.getText();
                String licenseExpiration = licenseExpirationField.getText();

                if (isValidString(name) && isValidString(address) && isNIF(nif) && isDate(licenseDate)
                        && isDate(licenseExpiration) && isEmail(email) && isValidBirthDate(birthDate)
                        && isPassword(password) && isDriverLicense(driverLicense) && selectedLicenseType != (" ")) {
                    if (dataSource.insertCustomer(Integer.parseInt(nif), name, address, Date.valueOf(birthDate),
                            password, email, Integer.parseInt(driverLicense), selectedLicenseType, Date.valueOf(licenseDate), Date.valueOf(licenseExpiration))) {

                        JOptionPane.showMessageDialog(insertCustomerFrame, "Customer inserted successfully");
                        insertCustomerFrame.setVisible(false);
                        insertCustomerFrame.dispose();
                        insertVehicle(insertMenuFrame);
//                        break;
                    } else {
                        JOptionPane.showMessageDialog(insertCustomerFrame, "Error inserting customer");
                    }
                } else {
                    JOptionPane.showMessageDialog(insertCustomerFrame, "Please fill all the fields");
                    break;
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        exitButton.setBackground(RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            insertCustomerFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        backButton.setBackground(BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            insertMenuFrame.setVisible(true);
            insertCustomerFrame.setVisible(false);
        });

        gbc.gridwidth = 1;
        JLabel[] labels = {nameLabel, addressLabel, emailLabel, birthDateLabel,
                passwordLabel, nifLabel, driverLicenseLabel, licenseDateLabel,
                licenseExpirationLabel, licenseTypeLabel};
        JTextField[] fields = {nameField, addressField, emailField, birthDateField,
                passwordField, nifField, driverLicenseField,
                licenseDateField, licenseExpirationField};

        for (int row = 0; row < 10; row++) {
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            insertCustomerPanel.add(labels[row], gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            if (row == 9) {
                insertCustomerPanel.add(licenseType, gbc);
            } else {
                insertCustomerPanel.add(fields[row], gbc);
            }
        }

        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertCustomerPanel.add(submitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertCustomerPanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertCustomerPanel.add(backButton, gbc);

        insertCustomerFrame.add(insertCustomerPanel);
        insertCustomerFrame.pack();
        insertCustomerFrame.setVisible(true);

        insertCustomerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataSource.close();
            }
        });
    }

    private void insertVehicle(JFrame insertMenuFrame) {
        JFrame insertVehicleFrame = new JFrame("Insert Vehicle");
        insertVehicleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertVehicleFrame.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(RED);
        exitButton.setForeground(WHITE);
        JButton backButton = new JButton("Back");
        backButton.setBackground(BLACK);
        backButton.setForeground(WHITE);
        JButton submit = new JButton("Submit");
        submit.setBackground(GREEN);
        submit.setForeground(WHITE);
        backButton.addActionListener(e -> {
            insertMenuFrame.setVisible(true);
            insertVehicleFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            insertVehicleFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        //SET UP para botoes de exit
        JPanel insertVehiclePanel = new JPanel(new GridBagLayout());
        insertVehiclePanel.setBackground(Color.WHITE);

        JLabel insertVehicleLabel = new JLabel("Insert Vehicle");
        insertVehicleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.PAGE_START;
        insertVehiclePanel.add(insertVehicleLabel, gbc);

        JLabel brand = new JLabel("Brand: (Select an option)");
        JComboBox<String> brandField = new JComboBox<>(new String[]{" ", "Abarth", "Alfa Romeo", "Aston Martin", "Audi",
                "Bentley", "BMW", "Bugatti", "Cadillac", "Chevrolet", "Chrysler", "Citroen", "Dacia", "Daewoo",
                "Daihatsu", "Dodge", "Donkervoort", "DS", "Ferrari", "Fiat", "Fisker", "Ford", "Honda", "Hummer",
                "Hyundai", "Infiniti", "Iveco", "Jaguar", "Jeep", "Kia", "KTM", "Lada", "Lamborghini", "Lancia",
                "Land Rover", "Landwind", "Lexus", "Lotus", "Maserati", "Maybach", "Mazda", "McLaren", "Mercedes-Benz",
                "MG", "Mini", "Mitsubishi", "Morgan", "Nissan", "Opel", "Peugeot", "Porsche", "Renault", "Rolls-Royce",
                "Rover", "Saab", "Seat", "Skoda", "Smart", "SsangYong", "Subaru", "Suzuki", "Tesla", "Toyota",
                "Volkswagen", "Volvo"});

        JLabel model = new JLabel("Model");
        JTextField modelField = new JTextField(15);

        JLabel plate = new JLabel("Plate: XX-XX-XX ");
        JTextField plateField = new JTextField(15);

        JLabel color = new JLabel("Color: (Select an option)");
        JComboBox<String> colorField = new JComboBox<>(new String[]{" ", "Black", "White",
                "Red", "Blue", "Green", "Yellow", "Gray", "Silver", "Brown", "Orange"});

        JLabel registrationDate = new JLabel("Registration Date: (YYYY-MM-DD)");
        JTextField registrationDateField = new JTextField(15);

        JLabel vin = new JLabel("VIN: (17 characters)");
        JTextField vinField = new JTextField(15);

        JLabel nif = new JLabel("NIF: (9 digits)");
        JTextField nifField = new JTextField(15);

        JLabel category = new JLabel("Category: (Select an option)");
        JComboBox<String> categoryField = new JComboBox<>(new String[]{" ", "Light Commercial Vehicle",
                "Light Passenger Vehicle",
                "Heavy-duty Commercial Vehicle",
                "Heavy-duty Passenger Vehicle",
                "Motorcycle", "Moped",
                "Heavy-duty Passenger Vehicle"});

        submit.addActionListener(e -> {
            while (true) {

                String plateText = plateField.getText();
                String modelText = modelField.getText();
                String colorText = (String) colorField.getSelectedItem();
                String registrationDateText = registrationDateField.getText();
                String brandText = (String) brandField.getSelectedItem();
                String vinText = vinField.getText();
                String nifText = nifField.getText();
                String categoryText = (String) categoryField.getSelectedItem();

                if (isPlate(plateText) && isValidString(modelText) && isDate(registrationDateText) && isVIN(vinText) && isNIF(nifText) && !categoryText.equals(" ") && !colorText.equals(" ") && !brandText.equals(" ")) {

                    if (dataSource.insertVehicle(plateText, vinText, colorText, brandText,
                            modelText, Date.valueOf(registrationDateText), categoryText, Integer.parseInt(nifText))) {
//                        break;
                        JOptionPane.showMessageDialog(insertVehicleFrame, "Vehicle succefully registered");
                        insertVehicleFrame.setVisible(false);
                        insertVehicleFrame.dispose();
                        insertVehicle(insertMenuFrame);

                    } else {
                        JOptionPane.showMessageDialog(insertVehicleFrame, "Error inserting vehicle");
                    }
                } else {
                    JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill all the fields properly");
                    break;
                }
            }
        });

        gbc.gridwidth = 1;
        JLabel[] labels = {model, plate, registrationDate,
                vin, nif, category, brand, color};
        JTextField[] textFields = {modelField, plateField,
                registrationDateField, vinField, nifField};

        for (int row = 0; row < 8; row++) {
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            insertVehiclePanel.add(labels[row], gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            if (row == 5) {
                insertVehiclePanel.add(categoryField, gbc);
            } else if (row == 6) {
                insertVehiclePanel.add(brandField, gbc);
            } else if (row == 7) {
                insertVehiclePanel.add(colorField, gbc);
            } else {
                insertVehiclePanel.add(textFields[row], gbc);
            }
        }

        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 6, 10);
        insertVehiclePanel.add(submit, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(21, 10, 5, 10);
        insertVehiclePanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(21, 10, 5, 10);
        insertVehiclePanel.add(backButton, gbc);

        insertVehicleFrame.add(insertVehiclePanel);
        insertVehicleFrame.pack();
        insertVehicleFrame.setVisible(true);

        insertVehicleFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataSource.close();
            }
        });
    }

    public static void main(String[] args) {
        //Menu interativo
        Employee employee = new Employee();
        employee.setName("John Doe");
        BackOfficeAdminMenu backOfficeAdminMenu = new BackOfficeAdminMenu(employee);
    }

}