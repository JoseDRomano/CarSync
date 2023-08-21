package employeeacess;

import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackOfficeMenu extends JFrame {

    private Employee employee;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton insertMenu = new JButton("Insert Menu");
    private JButton updateMenu = new JButton("Update Menu");
    private JButton deleteMenu = new JButton("Delete Menu");
    private final JButton searchMenu = new JButton("Search Menu");
    private final JButton deactivateMenu = new JButton("Deactivate Menu");


    public BackOfficeMenu(Employee employee) {
        this.employee = employee;
        mainFrame = new JFrame("Back Office Menu" + "\n" + "Welcome " + employee.getName());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WIDTH, HEIGHT);
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));

        mainPanel.add(insertMenu);
        mainPanel.add(deleteMenu);
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
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void buildDeactivateMenuPage() {
        JFrame deactivateMenuFrame = new JFrame("Deactivation Menu");
        deactivateMenuFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void buildDeleteMenuPage() {
        JFrame deleteMenuFrame = new JFrame("Delete Menu");
        deleteMenuFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
        exitButton.addActionListener(e -> System.exit(0));
        ActionListener goToPageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                mainFrame.setVisible(false);

                if (button == vehicleInsert) insertVehicle(insertMenuFrame);
            /*    else if (button == employeeInsert) insertEmployee();
                else if (button == customerInsert) insertCustomer();
                else if (button == ticketInsert) insertTicket();
                else if (button == insuranceInsert) insertInsurance();*/
            }
        };
    }

    private void insertVehicle(JFrame insertFrame) {
        /*JFrame insertVehicleFrame = new JFrame("Insert Vehicle");
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
        JComboBox<String> category = new JComboBox<>(new String[]{"Light Commercial Vehicle",
                "Light Passenger Vehicle",
                "Heavy-duty Commercial Vehicle",
                "Heavy-duty Passenger Vehicle",
                "Motorcycle", "Moped",
                "Heavy-duty Passenger Vehicle"});
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
        exitButton.addActionListener(e -> System.exit(0));*/

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
