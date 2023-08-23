package employeeacess;

import model.Employee;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import static java.awt.Color.RED;

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
        mainLabel.setForeground(Color.BLACK);
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
//                else if (button == employeeInsert) insertEmployee(insertMenuFrame);
                else if (button == customerInsert) insertCustomer(insertMenuFrame);
//                else if (button == ticketInsert) insertTicket();
//                else if (button == insuranceInsert) insertInsurance();*/
            }
        };
        vehicleInsert.addActionListener(goToPageListener);
        customerInsert.addActionListener(goToPageListener);
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
        insertCustomerLabel.setForeground(Color.BLACK);
        gbc.gridwidth = 3;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        insertCustomerPanel.add(insertCustomerLabel, gbc);

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

        JLabel driverLicenseLabel = new JLabel("Driver License: ");
        JTextField driverLicenseField = new JTextField(15);

        JLabel licenseTypeLabel = new JLabel("License Type: ");
        JComboBox<String> licenseType = new JComboBox<>(new String[]{"A", "B", "C", "D"});

        JLabel licenseDateLabel = new JLabel("License Date: (YYYY-MM-DD)");
        JTextField licenseDateField = new JTextField(15);

        JLabel licenseExpirationLabel = new JLabel("License Expiration: (YYYY-MM-DD)");
        JTextField licenseExpirationField = new JTextField(15);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
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


            if (!isValidString(name)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Please fill the name field");
            } else if (!isValidString(address)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Please fill the address field");
            } else if (!isEmail(email)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Wrong email format");
            } else if (!isValidBirthDate(birthDate)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Wrong birth date format");
            } else if (!isValidString(password)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Please fill the password field");
            } else if (!isNIF(nif)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Wrong NIF format");
            } else if (!isDriverLicense(driverLicense)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Please fill the driver license field");
            } else if (!isDate(licenseDate)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Wrong license date format");
            } else if (!isDate(licenseExpiration)) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Wrong license expiration format");
            } else {
                password = BCrypt.hashpw(password, BCrypt.gensalt());
                dataSource.insertCustomer(Integer.parseInt(nif), name, address, Date.valueOf(birthDate),
                        password, email, Integer.parseInt(driverLicense), selectedLicenseType, Date.valueOf(licenseDate), Date.valueOf(licenseExpiration));
                JOptionPane.showMessageDialog(insertCustomerFrame, "Customer inserted successfully");
            }
            insertCustomerFrame.setVisible(false);
            insertCustomer(insertMenuFrame);
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

        backButton.setBackground(GREEN);
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
            gbc.anchor = GridBagConstraints.EAST;
            insertCustomerPanel.add(labels[row], gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
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
        gbc.insets = new Insets(20, 10, 6, 10);
        insertCustomerPanel.add(submitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(21, 10, 5, 10);
        insertCustomerPanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(21, 10, 5, 10);
        insertCustomerPanel.add(backButton, gbc);

        insertCustomerFrame.add(insertCustomerPanel);
        insertCustomerFrame.pack();
        insertCustomerFrame.setVisible(true);
    }

    private void insertVehicle(JFrame insertFrame) {
        JFrame insertVehicleFrame = new JFrame("Insert Vehicle");
        insertVehicleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertVehicleFrame.setSize(WIDTH, HEIGHT);
        insertVehicleFrame.setLayout(new BorderLayout());
        insertVehicleFrame.setBackground(Color.WHITE);

        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton insertButton = new JButton("Submit");

        //SET UP para botoes de exit
        JPanel insertVehiclePanel = new JPanel(new GridLayout());
        JLabel insertVehicleLabel = new JLabel("Insert Vehicle");
        insertVehicleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        insertVehicleLabel.setHorizontalAlignment(JLabel.CENTER);
        insertVehicleLabel.setVerticalAlignment(JLabel.CENTER);
        insertVehiclePanel.add(insertVehicleLabel);
        insertVehiclePanel.setSize(150, 100);
        insertVehiclePanel.setBounds(0, 400, 200, 100);
        insertVehiclePanel.setLayout(new GridLayout());
        insertVehiclePanel.add(insertButton);
        insertVehiclePanel.add(backButton);
        insertVehiclePanel.add(exitButton);


        JPanel insertVehiclePanel2 = new JPanel();
        JTextField plate = new JTextField("Plate");
        plate.setSize(80, 50);
        plate.setAlignmentX(Component.CENTER_ALIGNMENT);
        plate.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        JTextField model = new JTextField("Model");
        model.setSize(100, 50);
        JComboBox<String> brand = new JComboBox<>(new String[]{"Abarth", "Alfa Romeo", "Aston Martin", "Audi",
                "Bentley", "BMW", "Bugatti", "Cadillac", "Chevrolet", "Chrysler", "Citroen", "Dacia", "Daewoo",
                "Daihatsu", "Dodge", "Donkervoort", "DS", "Ferrari", "Fiat", "Fisker", "Ford", "Honda", "Hummer",
                "Hyundai", "Infiniti", "Iveco", "Jaguar", "Jeep", "Kia", "KTM", "Lada", "Lamborghini", "Lancia",
                "Land Rover", "Landwind", "Lexus", "Lotus", "Maserati", "Maybach", "Mazda", "McLaren", "Mercedes-Benz",
                "MG", "Mini", "Mitsubishi", "Morgan", "Nissan", "Opel", "Peugeot", "Porsche", "Renault", "Rolls-Royce",
                "Rover", "Saab", "Seat", "Skoda", "Smart", "SsangYong", "Subaru", "Suzuki", "Tesla", "Toyota",
                "Volkswagen", "Volvo"});
        brand.setSize(100, 50);
        JComboBox<String> color = new JComboBox<>(new String[]{"Black", "White",
                "Red", "Blue", "Green", "Yellow", "Gray", "Silver", "Brown", "Orange"});
        brand.setSize(100, 50);
        JTextField registrationDate = new JTextField("Regitration Date (YYYY-MM-DD)");
        registrationDate.setSize(100, 50);
        JTextField vin = new JTextField("VIN");
        vin.setSize(100, 50);
        JTextField nif = new JTextField("NIF");
        nif.setSize(100, 50);
        JComboBox<String> category = new JComboBox<>(new String[]{"Light Commercial Vehicle",
                "Light Passenger Vehicle",
                "Heavy-duty Commercial Vehicle",
                "Heavy-duty Passenger Vehicle",
                "Motorcycle", "Moped",
                "Heavy-duty Passenger Vehicle"});
        category.setSize(100, 50);
        JButton submit = new JButton("Submit");
        submit.setSize(100, 50);

        submit.addActionListener(e -> {
            String plateText = plate.getText();
            String modelText = model.getText();
            String colorText = (String) color.getSelectedItem();
            String registrationDateText = registrationDate.getText();
            String brandText = (String) brand.getSelectedItem();
            String vinText = vin.getText();
            String nifText = nif.getText();
            String categoryText = (String) category.getSelectedItem();
            if (!isPlate(plateText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please insert a valid plate with format XX-XX-XX");
            } else if (!isValidString(modelText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill a valid model");
            } else if (!isDate(registrationDateText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill all the fields");
            } else if (!isVIN(vinText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill all the fields");
            } else if (!isNIF(nifText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill all the fields");
            } else {
                dataSource.insertVehicle(plateText, vinText, colorText, brandText, modelText, Date.valueOf(registrationDateText), categoryText, Integer.parseInt(nifText));
            }

        });


        insertVehiclePanel2.setLayout(new GridLayout());
        insertVehiclePanel2.add(plate);
        insertVehiclePanel2.add(model);
        insertVehiclePanel2.add(color);
        insertVehiclePanel2.add(registrationDate);
        insertVehiclePanel2.add(vin);
        insertVehiclePanel2.add(nif);
        insertVehiclePanel2.add(category);
        insertVehiclePanel2.add(submit);
        insertVehiclePanel2.add(brand);


        insertVehicleFrame.add(insertVehiclePanel);
        insertVehicleFrame.add(insertVehiclePanel2);
        insertVehicleFrame.setVisible(true);
        backButton.addActionListener(e -> {
            insertFrame.setVisible(true);
            insertVehicleFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            dataSource.close();
            System.exit(0);
        });

    }


   /* private void eventForButtons(JButton button) {
        switch (button.getName()) {
            case "insertMenu" -> button.addActionListener(e -> {
                insertMenuPage();
            });
            case "updateMenu" -> button.addActionListener(e -> {
                updateMenuPage();
            });
            case "deleteMenu" -> button.addActionListener(e -> {
                deleteMenuPage();
            });
            case "searchMenu" -> button.addActionListener(e -> {
                searchMenuPage();
            });
            case "deactivateMenu" -> button.addActionListener(e -> {
                deactivateMenuPage();
            });
        }
    }*/

    /*private void deactivateMenuPage() {
        deactivateMenuFrame = new JFrame("Deactivate Menu");
    }

    private void searchMenuPage() {
        searchMenuFrame = new JFrame("Search Menu");
    }

    private void deleteMenuPage() {
        deleteMenuFrame = new JFrame("Delete Menu");
    }

    private void updateMenuPage() {
        updateMenuFrame = new JFrame("Update Menu");
    }

    private void insertMenuPage() {
        insertMenuFrame = new JFrame("Insert Menu");
    }*/
}
