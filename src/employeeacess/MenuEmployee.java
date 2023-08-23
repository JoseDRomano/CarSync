package employeeacess;

import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEmployee {

    private Employee employee;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    private JFrame mainFrame;
    private JPanel mainPanel;

    private DataSource data;

    private JFrame deactivateMenuFrame;
    private JButton insertMenu = new JButton("Insert Menu");
    private JButton updateMenu = new JButton("Update Menu");
    private final JButton searchMenu = new JButton("Search Menu");
    private final JButton deactivateMenu = new JButton("Deactivate Menu");

    public MenuEmployee(Employee employee) {
        this.data = new DataSource();
        data.open();
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

    private void buildUpdateMenuPage() {
        JFrame updateMenuFrame = new JFrame("Update Menu");
        updateMenuFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        updateMenuFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton vehicleDisplay = new JButton("Vehicle Update Menu");
        JButton customerDisplay = new JButton("Customer Update Menu");
        JButton ticketDisplay = new JButton("Ticket Update Menu");
        JButton insuranceDisplay = new JButton("Insurance Update Menu");
        JPanel updateMenuPanel = new JPanel();
        updateMenuPanel.setLayout(new GridLayout(7, 1));
        updateMenuPanel.add(vehicleDisplay);
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
        exitButton.addActionListener(e -> System.exit(0));
    }

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
                    buildVehicleDeactivationPage();
                else if (button == customerDeactivation)
                    buildCustomerDeactivationPage();
                else if (button == ticketDeactivation)
                    buildTicketDeactivationPage();
                else if (button == insuranceDeactivation)
                    buildInsuranceDeactivationPage();
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
    private void buildInsuranceDeactivationPage() {
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
            data.deactivateInsurance(Integer.parseInt(insuranceNumber.getText()));
        });
        backButton.addActionListener(e -> {
            deactivateMenuFrame.setVisible(true);
            deactivateInsuranceFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //receives a ticket id and then proceeds to deactivate the ticket sending a success message
    private void buildTicketDeactivationPage() {
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
            data.deactivateTicket(Integer.parseInt(id.getText()));
        });
        backButton.addActionListener(e -> {
            deactivateMenuFrame.setVisible(true);
            deactivateTicketFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //receives a customer nif and then proceeds to deactivate the customer sending a success message
    private void buildCustomerDeactivationPage() {
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
            data.deactivateCustomer(Integer.parseInt(nif.getText()));
        });
        backButton.addActionListener(e -> {
            deactivateMenuFrame.setVisible(true);
            deactivateCustomerFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    //receives a license plate and then proceeds to deactivate the vehicle sending a success message
    private void buildVehicleDeactivationPage() {
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
            data.deactivateVehicle(plate.getText());
        });
        backButton.addActionListener(e -> {
            deactivateMenuFrame.setVisible(true);
            deactivateVehicleFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

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
        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            insertMenuFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
        ActionListener goToPageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                mainFrame.setVisible(false);

//                if (button == vehicleInsert) insertVehicle(insertMenuFrame);
//                else if (button == employeeInsert) insertEmployee();
//                else if (button == customerInsert) insertCustomer();
//                else if (button == ticketInsert) insertTicket();
//                else if (button == insuranceInsert) insertInsurance();
            }
        };
    }

    private void insertVehicle(JFrame insertFrame) {
        JFrame insertVehicleFrame = new JFrame("Insert Vehicle");
        insertVehicleFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        insertVehicleFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");
        JButton backButton = new JButton("Back");
        JButton insertButton = new JButton("Submit");
        JTextField plate = new JTextField("Plate");
        JTextField model = new JTextField("Model");
        JTextField color = new JTextField("Color");
        JTextField registrationDate = new JTextField("Regitration Date (YYYY-MM-DD)");
        JTextField vin = new JTextField("VIN");
        JTextField nif = new JTextField("NIF");
        JComboBox<String> category = new JComboBox<>(new String[]{"Light Commercial Vehicle", "Light Passenger Vehicle",
                "Heavy-duty Commercial Vehicle", "Heavy-duty Passenger Vehicle", "Motorcycle", "Moped", "Heavy-duty Passenger Vehicle"});
        JPanel insertVehiclePanel = new JPanel();
        insertVehiclePanel.setLayout(new GridLayout(7, 1));
        insertVehiclePanel.add(insertButton);
        insertVehiclePanel.add(backButton);
        insertVehiclePanel.add(exitButton);
        insertVehicleFrame.add(insertVehiclePanel);
        insertVehicleFrame.setVisible(true);
        backButton.addActionListener(e -> {
            insertFrame.setVisible(true);
            insertVehicleFrame.setVisible(false);
        });
        exitButton.addActionListener(e -> System.exit(0));

    }

   /* private void eventForButtons(JButton button) {
        switch (button.getName()) {
            case "insertMenu" -> button.addActionListener(e -> {
                insertMenuPage();
            });
            case "updateMenu" -> button.addActionListener(e -> {
                updateMenuPage();
            });
            case "searchMenu" -> button.addActionListener(e -> {
                searchMenuPage();
            });
            case "deactivateMenu" -> button.addActionListener(e -> {
                deactivateMenuPage();
            });
        }
    }*/
}