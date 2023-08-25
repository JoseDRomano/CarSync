package employeeacess;

import model.Employee;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import static java.awt.Color.BLACK;

public class MenuEmployee implements ValidateInput {

    private Employee employee;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    private JFrame mainFrame;
    private JPanel mainPanel;

    private DataSource dataSource;

    private final Color GREEN = new Color(0, 100, 0);
    private final Color RED = new Color(130, 0, 0);
    private final Color WHITE = new Color(255, 255, 255);

    private JFrame updateMenuFrame;
    private JFrame deactivateMenuFrame;
    private JButton insertMenu = new JButton("Insert Menu");
    private JButton updateMenu = new JButton("Update Menu");
    private final JButton searchMenu = new JButton("Search Menu");
    private final JButton deactivateMenu = new JButton("Deactivate Menu");

    public MenuEmployee(Employee employee) {
        this.dataSource = new DataSource();
        dataSource.open();
        this.employee = employee;
        mainFrame = new JFrame("Back Office Menu" + "\n" + "Welcome " + employee.getName());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));

        mainPanel.add(insertMenu);
        mainPanel.add(deactivateMenu);
        mainPanel.add(updateMenu);
        mainPanel.add(searchMenu);
        mainPanel.add(exitButton);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        ActionListener goToPageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                mainFrame.setVisible(false);

                if (button == insertMenu) buildInsertMenuPage();
                else if (button == deactivateMenu) buildDeactivateMenuPage();
                else if (button == updateMenu) buildUpdateMenuPage();
                else if (button == searchMenu) buildSearchMenuPage();
            }
        };

        insertMenu.addActionListener(goToPageListener);
        deactivateMenu.addActionListener(goToPageListener);
        updateMenu.addActionListener(goToPageListener);
        searchMenu.addActionListener(goToPageListener);
        exitButton.addActionListener(e -> System.exit(0));

    }

    //Search Menu
    private void buildSearchMenuPage() {
        JFrame searchMenuFrame = new JFrame("Search Menu");
        searchMenuFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
        exitButton.addActionListener(e -> System.exit(0));
    }

    //Update Menu
    private void buildUpdateMenuPage() {
        JFrame updateMenuFrame = new JFrame("Update Menu");
        updateMenuFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        updateMenuFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton vehicleButton = new JButton("Update Vehicle");
        JButton customerButton = new JButton("Update Customer");
        JButton ticketButton = new JButton("Update Ticket");
        JButton insuranceButton = new JButton("Update Insurance");
        JPanel updateMenuPanel = new JPanel();
        updateMenuPanel.setLayout(new GridLayout(7, 1));
        updateMenuPanel.add(vehicleButton);
        updateMenuPanel.add(customerButton);
        updateMenuPanel.add(ticketButton);
        updateMenuPanel.add(insuranceButton);
        updateMenuPanel.add(backButton);
        updateMenuPanel.add(exitButton);
        updateMenuFrame.add(updateMenuPanel);
        mainFrame.setVisible(false);
        updateMenuFrame.setVisible(true);
        vehicleButton.addActionListener(e -> {
            updateVehiclePage(updateMenuFrame);

        });
        customerButton.addActionListener(e -> {
            updateCustomerPage(updateMenuFrame);
        });
        ticketButton.addActionListener(e -> {
            payTicketPage(updateMenuFrame);
        });
        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            updateMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //DOING
    private void payTicketPage(JFrame updateMenuFrame) {
        JFrame updateTicketFrame = new JFrame("Update Ticket");
        updateTicketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateTicketFrame.setBackground(Color.WHITE);
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
            updateTicketFrame.setVisible(true);
            updateMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            updateTicketFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        //SET UP for exit buttons
        JPanel updateTicketPanel = new JPanel(new GridBagLayout());
        updateTicketPanel.setBackground(Color.WHITE);

        JLabel updateTicketLabel = new JLabel("Update Ticket");
        updateTicketLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.PAGE_START;
        updateTicketPanel.add(updateTicketLabel, gbc);

        JLabel idLabel = new JLabel("ID: ");
        JTextField idField = new JTextField(15);
    }

    //DOING
    private void updateCustomerPage(JFrame updateMenuFrame) {
        JFrame updatePersonFrame = new JFrame("Update Vehicle");
        updatePersonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updatePersonFrame.setBackground(Color.WHITE);
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
            updatePersonFrame.setVisible(true);
            updateMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> {
            updatePersonFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        //SET UP for exit buttons
        JPanel updatePersonPanel = new JPanel(new GridBagLayout());
        updatePersonPanel.setBackground(Color.WHITE);

        JLabel updatePersonLabel = new JLabel("Update Person");
        updatePersonLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.PAGE_START;
        updatePersonPanel.add(updatePersonPanel, gbc);

        JLabel nifLabel = new JLabel("NIF: ");
        JTextField nifField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(15);

        JButton submitButton1 = new JButton("Submit");
        submitButton1.setBackground(GREEN);
        submitButton1.setForeground(Color.WHITE);
        submitButton1.addActionListener(e -> {
            String nif = nifField.getText();
            String email = emailField.getText();
            if (!isNIF(nif))
                JOptionPane.showMessageDialog(updatePersonFrame, "Please insert a valid NIF");
            else if (!isEmail(email)) {
                JOptionPane.showMessageDialog(updatePersonFrame, "Wrong email format");
            }
            else {
                if (dataSource.updatePersonEmail(Integer.parseInt(nif), email)) {
                    JOptionPane.showMessageDialog(updatePersonFrame, "Person successfully updated",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(updatePersonFrame, "Error updating person", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JLabel passwordLabel = new JLabel("Password: ");
        JTextField passwordField = new JTextField(15);

        JButton submitButton2 = new JButton("Submit");
        submitButton2.setBackground(GREEN);
        submitButton2.setForeground(Color.WHITE);
        submitButton2.addActionListener(e -> {
            String nif = nifField.getText();
            String email = emailField.getText();
            if (!isNIF(nif))
                JOptionPane.showMessageDialog(updatePersonFrame, "Please insert a valid NIF");
            else if (!isPassword(passwordField.getText())) {
                JOptionPane.showMessageDialog(updatePersonFrame, "Wrong email format");
            }
            else {
                if (dataSource.updatePersonEmail(Integer.parseInt(nif), email)) {
                    JOptionPane.showMessageDialog(updatePersonFrame, "Person successfully updated",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(updatePersonFrame, "Error updating person", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });






    }

    //DONE
    private void updateVehiclePage(JFrame updateMenuFrame) {
        updateMenuFrame.setVisible(false);
        JFrame updateVehicleFrame = new JFrame("Update Vehicle");
        updateVehicleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateVehicleFrame.setBackground(Color.WHITE);
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
            updateVehicleFrame.setVisible(false);
            updateMenuFrame.setVisible(true);
        });
        exitButton.addActionListener(e -> {
            updateVehicleFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        //SET UP for exit buttons
        JPanel updateVehiclePanel = new JPanel(new GridBagLayout());
        updateVehiclePanel.setBackground(Color.WHITE);

        JLabel updateVehicleLabel = new JLabel("Update Vehicle");
        updateVehicleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.PAGE_START;
        updateVehiclePanel.add(updateVehicleLabel, gbc);

        JLabel plate = new JLabel("Plate: ");
        JTextField plateField = new JTextField(15);

        JLabel color = new JLabel("Color: ");
        JComboBox<String> colorField = new JComboBox<>(new String[]{" ", "Black", "White",
                "Red", "Blue", "Green", "Yellow", "Gray", "Silver", "Brown", "Orange"});

        submit.addActionListener(e -> {
            String plateText = plateField.getText();
            String colorText = (String) colorField.getSelectedItem();
            if (!isPlate(plateText))
                JOptionPane.showMessageDialog(updateVehicleFrame, "Please insert a valid plate with " +
                        "format XX-XX-XX");
            else {
                if (dataSource.updateVehicleColor(plateText, colorText)) {
                    JOptionPane.showMessageDialog(updateVehicleFrame, "Vehicle successfully updated",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(updateVehicleFrame, "Error updating vehicle", "Error", JOptionPane.ERROR_MESSAGE);

            }
            updateVehicleFrame.setVisible(false);
            updateVehiclePage(updateMenuFrame);
        });

        gbc.gridwidth = 1;
        JLabel[] labels = {plate, color};
        JTextField[] textFields = {plateField
        };

        for (int row = 0; row < labels.length; row++) {
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            updateVehiclePanel.add(labels[row], gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            if (row == 0) {
                updateVehiclePanel.add(textFields[row], gbc);
            } else {
                updateVehiclePanel.add(colorField, gbc);
            }

        }

        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 6, 10);
        updateVehiclePanel.add(submit, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(21, 10, 5, 10);
        updateVehiclePanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(21, 10, 5, 10);
        updateVehiclePanel.add(backButton, gbc);

        updateVehicleFrame.add(updateVehiclePanel);
        updateVehicleFrame.pack();
        updateVehicleFrame.setVisible(true);

        updateVehicleFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dataSource.close();
            }
        });
    }

    //Deactivate Menu
    private void buildDeactivateMenuPage() {
        deactivateMenuFrame = new JFrame("Deactivation Menu");
        deactivateMenuFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        deactivateMenuFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton vehicleDeactivation = new JButton("Vehicle Deactivation Menu");
        JButton customerDeactivation = new JButton("Customer Deactivation Menu");
        JButton ticketDeactivation = new JButton("Ticket Deactivation Menu");
        JButton insuranceDeactivation = new JButton("Insurance Deactivation Menu");
        JPanel deactivationMenuPanel = new JPanel();
        deactivationMenuPanel.setLayout(new GridLayout(7, 1));

        deactivationMenuPanel.add(vehicleDeactivation);
        deactivationMenuPanel.add(customerDeactivation);
        deactivationMenuPanel.add(ticketDeactivation);
        deactivationMenuPanel.add(insuranceDeactivation);
        deactivationMenuPanel.add(backButton);
        deactivationMenuPanel.add(exitButton);

        deactivateMenuFrame.add(deactivationMenuPanel);
        mainFrame.setVisible(false);
        deactivateMenuFrame.setVisible(true);

        ActionListener goToPageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                deactivateMenuFrame.setVisible(false);
                if (button == vehicleDeactivation)
                    deactivateVehiclePage();
                else if (button == customerDeactivation)
                    deactivateCustomerPage();
                else if (button == ticketDeactivation)
                    deactivateTicketPage();
                else if (button == insuranceDeactivation)
                    deactivateInsurancePage();
            }
        };

        vehicleDeactivation.addActionListener(goToPageListener);
        customerDeactivation.addActionListener(goToPageListener);
        ticketDeactivation.addActionListener(goToPageListener);
        insuranceDeactivation.addActionListener(goToPageListener);

        backButton.addActionListener(e -> {
            JButton button = (JButton) e.getSource();
            mainFrame.setVisible(true);
            deactivateMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //receives an insurance number and then proceeds to deactivate the insurance sending a success message
    private void deactivateInsurancePage() {
        JFrame deactivateInsuranceFrame = new JFrame("Deactivate Insurance");
        deactivateInsuranceFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        deactivateInsuranceFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton deactivateButton = new JButton("Deactivate");
        JTextField insuranceNumber = new JTextField("Insurance Number");
        JPanel deactivateInsurancePanel = new JPanel();
        deactivateInsurancePanel.setLayout(new GridLayout(7, 1));
        deactivateInsurancePanel.add(insuranceNumber);
        deactivateInsurancePanel.add(deactivateButton);
        deactivateInsurancePanel.add(backButton);
        deactivateInsurancePanel.add(exitButton);
        deactivateInsuranceFrame.add(deactivateInsurancePanel);
        mainFrame.setVisible(false);
        deactivateInsuranceFrame.setVisible(true);
        deactivateButton.addActionListener(e -> {
            dataSource.deactivateInsurance(Integer.parseInt(insuranceNumber.getText()));
        });
        backButton.addActionListener(e -> {
            deactivateMenuFrame.setVisible(true);
            deactivateInsuranceFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //receives a ticket id and then proceeds to deactivate the ticket sending a success message
    private void deactivateTicketPage() {
        JFrame deactivateTicketFrame = new JFrame("Deactivate Ticket");
        deactivateTicketFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        deactivateTicketFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton deactivateButton = new JButton("Deactivate");
        JTextField id = new JTextField("ID");
        JPanel deactivateTicketPanel = new JPanel();
        deactivateTicketPanel.setLayout(new GridLayout(7, 1));
        deactivateTicketPanel.add(id);
        deactivateTicketPanel.add(deactivateButton);
        deactivateTicketPanel.add(backButton);
        deactivateTicketPanel.add(exitButton);
        deactivateTicketFrame.add(deactivateTicketPanel);
        mainFrame.setVisible(false);
        deactivateTicketFrame.setVisible(true);
        deactivateButton.addActionListener(e -> {
            dataSource.deactivateTicket(Integer.parseInt(id.getText()));
        });
        backButton.addActionListener(e -> {
            deactivateMenuFrame.setVisible(true);
            deactivateTicketFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //receives a customer nif and then proceeds to deactivate the customer sending a success message
    private void deactivateCustomerPage() {
        JFrame deactivateCustomerFrame = new JFrame("Deactivate Customer");
        deactivateCustomerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        deactivateCustomerFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton deactivateButton = new JButton("Deactivate");
        JTextField nif = new JTextField("NIF");
        JPanel deactivateCustomerPanel = new JPanel();
        deactivateCustomerPanel.setLayout(new GridLayout(7, 1));
        deactivateCustomerPanel.add(nif);
        deactivateCustomerPanel.add(deactivateButton);
        deactivateCustomerPanel.add(backButton);
        deactivateCustomerPanel.add(exitButton);
        deactivateCustomerFrame.add(deactivateCustomerPanel);
        mainFrame.setVisible(false);
        deactivateCustomerFrame.setVisible(true);
        deactivateButton.addActionListener(e -> {
            dataSource.deactivateCustomer(Integer.parseInt(nif.getText()));
        });
        backButton.addActionListener(e -> {
            deactivateMenuFrame.setVisible(true);
            deactivateCustomerFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //receives a license plate and then proceeds to deactivate the vehicle sending a success message
    private void deactivateVehiclePage() {
        JFrame deactivateVehicleFrame = new JFrame("Deactivate Vehicle");
        deactivateVehicleFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        deactivateVehicleFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton deactivateButton = new JButton("Deactivate");
        JTextField plate = new JTextField("Plate");
        JPanel deactivateVehiclePanel = new JPanel();
        deactivateVehiclePanel.setLayout(new GridLayout(7, 1));
        deactivateVehiclePanel.add(plate);
        deactivateVehiclePanel.add(deactivateButton);
        deactivateVehiclePanel.add(backButton);
        deactivateVehiclePanel.add(exitButton);
        deactivateVehicleFrame.add(deactivateVehiclePanel);
        mainFrame.setVisible(false);
        deactivateVehicleFrame.setVisible(true);
        deactivateButton.addActionListener(e -> {
            dataSource.deactivateVehicle(plate.getText());
        });
        backButton.addActionListener(e -> {
            deactivateMenuFrame.setVisible(true);
            deactivateVehicleFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //Insert Menu
    private void buildInsertMenuPage() {
        JFrame insertMenuFrame = new JFrame("Insert Menu");
        insertMenuFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
        insertMenuPanel.add(customerInsert);
        insertMenuPanel.add(ticketInsert);
        insertMenuPanel.add(insuranceInsert);
        insertMenuPanel.add(backButton);
        insertMenuPanel.add(exitButton);
        insertMenuFrame.add(insertMenuPanel);
        mainFrame.setVisible(false);
        insertMenuFrame.setVisible(true);

        //Action listeners for the buttons
        vehicleInsert.addActionListener(e -> {
            insertVehicle(insertMenuFrame);
        });
        customerInsert.addActionListener(e -> {
            insertCustomer(insertMenuFrame);
        });
        ticketInsert.addActionListener(e -> {
            insertTicket(insertMenuFrame);
        });
        insuranceInsert.addActionListener(e -> {
            insertInsurance(insertMenuFrame);
        });
        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            insertMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));

    }

    //Methods that build the Little insert Windows
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
        JComboBox<String> insuranceCategoryField = new JComboBox<>(new String[]{" ", "Third Party", "Third Party Fire and Theft", "Third Party Fire and Auto-Liabitlity", "Comprehensive"});

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
            String plate = plateField.getText();
            String insuranceCategory = (String) insuranceCategoryField.getSelectedItem();
            String policy = policyField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            String companyName = companyNameField.getText();

            if (!isPlate(plate)) {
                JOptionPane.showMessageDialog(insertInsuranceFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isDate(startDate)) {
                JOptionPane.showMessageDialog(insertInsuranceFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (!isDate(endDate)) {
                JOptionPane.showMessageDialog(insertInsuranceFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (!isValidString(companyName)) {
                JOptionPane.showMessageDialog(insertInsuranceFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (!isPolicy(policy)) {
                JOptionPane.showMessageDialog(insertInsuranceFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (insuranceCategory.isEmpty() || insuranceCategory.isBlank()) {
                JOptionPane.showMessageDialog(insertInsuranceFrame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                if (dataSource.insertInsurance(Integer.parseInt(policy), plate, Date.valueOf(startDate),
                        insuranceCategory, Date.valueOf(endDate), companyName)) {
                    JOptionPane.showMessageDialog(insertInsuranceFrame, "Ticket successfully registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                    insertInsuranceFrame.setVisible(false);
                    insertMenuFrame.setVisible(true);
                }
                JOptionPane.showMessageDialog(insertInsuranceFrame, "Error registering ticket", "Error", JOptionPane.ERROR_MESSAGE);
            }
            insertInsuranceFrame.setVisible(false);
            insertInsurance(insertMenuFrame);
        });


        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        exitButton.setBackground(RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            insertInsuranceFrame.dispose();
            dataSource.close();
            System.exit(0);
        });

        backButton.setBackground(BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            insertMenuFrame.setVisible(true);
            insertInsuranceFrame.setVisible(false);
        });

        gbc.gridwidth = 1;
        JLabel[] labels = {plateLabel, policyLabel,
                startDateLabel, endDateLabel, companyNameLabel, insuranceCategoryLabel};
        JTextField[] fields = {plateField, policyField,
                startDateField, endDateField, companyNameField};

        for (int row = 0; row < labels.length; row++) {
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
        gbc.insets = new Insets(20, 10, 5, 10);
        insertInsurancePanel.add(submitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertInsurancePanel.add(exitButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(20, 10, 5, 10);
        insertInsurancePanel.add(backButton, gbc);

        insertInsuranceFrame.add(insertInsurancePanel);
        insertInsuranceFrame.pack();
        insertInsuranceFrame.setVisible(true);

        insertInsuranceFrame.addWindowListener(new WindowAdapter() {
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
        JComboBox<String> reasonFiel = new JComboBox<>(new String[]{" ", "Illegal parking",
                "Speeding", "Red light", "Reckless driving", "DUI"});

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            String plate = plateField.getText();
            String date = dateField.getText();
            String expirationDate = expirationDateField.getText();
            String value = valueField.getText();
            String nif = nifField.getText();
            String reason = (String) reasonFiel.getSelectedItem();
            if (!isPlate(plate)) {
                JOptionPane.showMessageDialog(insertTicketFrame, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isDate(date)) {
                JOptionPane.showMessageDialog(insertTicketFrame, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isDate(expirationDate)) {
                JOptionPane.showMessageDialog(insertTicketFrame, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isDouble(value)) {
                JOptionPane.showMessageDialog(insertTicketFrame, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isNIF(nif)) {
                JOptionPane.showMessageDialog(insertTicketFrame, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (reason.isBlank() || reason.isEmpty()) {
                JOptionPane.showMessageDialog(insertTicketFrame, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (dataSource.insertTicket(Integer.parseInt(nif), plate, Date.valueOf(date),
                        reason, Double.parseDouble(value), Date.valueOf(expirationDate))) {
                    JOptionPane.showMessageDialog(insertTicketFrame, "Ticket successfully registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                    insertTicketFrame.setVisible(false);
                    mainFrame.setVisible(true);
                }
                JOptionPane.showMessageDialog(insertTicketFrame, "Error registering ticket", "Error", JOptionPane.ERROR_MESSAGE);
            }
            insertTicketFrame.setVisible(false);
            insertCustomer(insertMenuFrame);
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
        JComboBox<String> licenseType = new JComboBox<>(new String[]{" ", "A", "B", "C", "D"});

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
            } else if (((String) licenseType.getSelectedItem()).isEmpty() ||
                    ((String) licenseType.getSelectedItem()).isBlank()) {
                JOptionPane.showMessageDialog(insertCustomerFrame, "Please choose an option from the license option box");
            } else {
                password = BCrypt.hashpw(password, BCrypt.gensalt());
                if (dataSource.insertCustomer(Integer.parseInt(nif), name, address, Date.valueOf(birthDate),
                        password, email, Integer.parseInt(driverLicense), selectedLicenseType, Date.valueOf(licenseDate), Date.valueOf(licenseExpiration))) {
                    JOptionPane.showMessageDialog(insertCustomerFrame, "Customer inserted successfully");
                }
                JOptionPane.showMessageDialog(insertCustomerFrame, "Error inserting customer please try again");
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

        JLabel brand = new JLabel("Brand: ");
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

        JLabel plate = new JLabel("Plate: ");
        JTextField plateField = new JTextField(15);

        JLabel color = new JLabel("Color: ");
        JComboBox<String> colorField = new JComboBox<>(new String[]{" ", "Black", "White",
                "Red", "Blue", "Green", "Yellow", "Gray", "Silver", "Brown", "Orange"});

        JLabel registrationDate = new JLabel("Registration Date: (YYYY-MM-DD)");
        JTextField registrationDateField = new JTextField(15);

        JLabel vin = new JLabel("VIN: ");
        JTextField vinField = new JTextField(15);

        JLabel nif = new JLabel("NIF: ");
        JTextField nifField = new JTextField(15);

        JLabel category = new JLabel("Category: ");
        JComboBox<String> categoryField = new JComboBox<>(new String[]{" ", "Light Commercial Vehicle",
                "Light Passenger Vehicle",
                "Heavy-duty Commercial Vehicle",
                "Heavy-duty Passenger Vehicle",
                "Motorcycle", "Moped",
                "Heavy-duty Passenger Vehicle"});

        submit.addActionListener(e -> {
            String plateText = plateField.getText();
            String modelText = modelField.getText();
            String colorText = (String) colorField.getSelectedItem();
            String registrationDateText = registrationDateField.getText();
            String brandText = (String) brandField.getSelectedItem();
            String vinText = vinField.getText();
            String nifText = nifField.getText();
            String categoryText = (String) categoryField.getSelectedItem();
            if (!isPlate(plateText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please insert a valid plate with format XX-XX-XX");
            } else if (!isValidString(modelText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill the model field");
            } else if (!isDate(registrationDateText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill the date field with a date in the forma YYYY-MM-DD");
            } else if (!isVIN(vinText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill the VIN field (must contain 17 caracters)");
            } else if (!isNIF(nifText)) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please fill all nif field (must contain 9 numbers)");
            } else if (categoryText.isEmpty() || categoryText.isBlank()) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please select an option for the category field");
            } else if (colorText.isEmpty() || colorText.isBlank()) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please select an option for the color field");
            } else if (brandText.isBlank() || brandText.isBlank()) {
                JOptionPane.showMessageDialog(insertVehicleFrame, "Please select an option for the brand field");
            } else {
                if (dataSource.insertVehicle(plateText, vinText, colorText, brandText, modelText, Date.valueOf(registrationDateText), categoryText, Integer.parseInt(nifText))) {
                    JOptionPane.showMessageDialog(insertVehicleFrame, "Vehicle succefully registered");
                }
                JOptionPane.showMessageDialog(insertVehicleFrame, "Vehicle wasn't registered please try again");
            }
            insertVehicleFrame.setVisible(false);
            insertVehicle(insertMenuFrame);

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
        Employee employee = new Employee();
        employee.setName("John Doe");
        MenuEmployee menuEmployee = new MenuEmployee(employee);
    }
}
