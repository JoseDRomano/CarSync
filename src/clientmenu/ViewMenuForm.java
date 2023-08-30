package clientmenu;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewMenuForm extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/projeto_imt";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private int nifNum;

    public ViewMenuForm(int nifNum) {
        this.nifNum = nifNum;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeUI();
    }

    private void initializeUI() {
        setTitle("View Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel headerLabel = new JLabel("View Menu");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(headerLabel, gbc);
        gbc.gridy = 1;
        JButton viewVehiclesButton = new JButton("View Vehicles");
        viewVehiclesButton.setBackground(new Color(6, 65, 16));
        viewVehiclesButton.setForeground(Color.white);
        viewVehiclesButton.setPreferredSize(new Dimension(120, 40));
        viewVehiclesButton.addActionListener(e -> viewCustomerVehicles());
        panel.add(viewVehiclesButton, gbc);
        gbc.gridy = 2;
        JButton viewInsurancesButton = new JButton("View Insurances");
        viewInsurancesButton.setBackground(new Color(6, 65, 16));
        viewInsurancesButton.setForeground(Color.white);
        viewInsurancesButton.setPreferredSize(new Dimension(120, 40));
        viewInsurancesButton.addActionListener(e -> viewCustomerInsurances());
        panel.add(viewInsurancesButton, gbc);
        gbc.gridy = 3;
        JButton viewTicketsButton = new JButton("View Tickets");
        viewTicketsButton.setBackground(new Color(6, 65, 16));
        viewTicketsButton.setForeground(Color.white);
        viewTicketsButton.setPreferredSize(new Dimension(120, 40));
        viewTicketsButton.addActionListener(e -> viewCustomerTickets());
        panel.add(viewTicketsButton, gbc);
        add(panel);
        gbc.gridy = 4;
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(32, 32, 32));
        goBackButton.setForeground(Color.white);
        goBackButton.setPreferredSize(new Dimension(120, 40));
        goBackButton.addActionListener(e -> handleGoBackButton());
        panel.add(goBackButton, gbc);
        add(panel);
    }

    private void viewCustomerVehicles() {
        List<String> vehicles = getVehiclesForCustomer(nifNum);
        if (vehicles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No vehicles found for the customer.");
        } else {
            StringBuilder message = new StringBuilder("Here are your vehicles customer " + nifNum + ":\n");
            for (String vehicle : vehicles) {
                message.append(vehicle).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    private List<String> getVehiclesForCustomer(int nif) {
        List<String> vehicles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicle WHERE nif = ?")) {
            statement.setInt(1, nif);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                String color = resultSet.getString("color");
                String regDate = resultSet.getString("registration_date");
                String plate = resultSet.getString("plate");
                String vin = resultSet.getString("vin");
                String category = resultSet.getString("category");
                String vehicleDetails = String.format("Brand: %s, Model: %s, Color: %s, Registration Date: %s, Plate: %s, VIN: %s, Category: %s", brand, model, color, regDate, plate, vin, category);
                vehicles.add(vehicleDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    private void viewCustomerInsurances() {
        List<String> insurances = getInsurancesForCustomer(nifNum);
        if (insurances.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No insurances found for the customer.");
        } else {
            StringBuilder message = new StringBuilder("Here are your insurances customer " + nifNum + ":\n");
            for (String insurance : insurances) {
                message.append(insurance).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    private List<String> getInsurancesForCustomer(int nif) {
        List<String> insurances = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM insurance WHERE nif = ?")) {
            statement.setInt(1, nif);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String policy = resultSet.getString("policy");
                String expiryDate = resultSet.getString("expiry_date");
                String company = resultSet.getString("company");
                String startDate = resultSet.getString("start_date");
                String extraCategory = resultSet.getString("extra_category");
                String insuranceDetails = String.format("Policy: %s, Expiry Date: %s, Company: %s, Start Date: %s, Extra Category: %s", policy, expiryDate, company, startDate, extraCategory);
                insurances.add(insuranceDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insurances;
    }

    private void viewCustomerTickets() {
        List<String> tickets = getTicketsForCustomer(nifNum);
        if (tickets.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tickets found for the customer.");
        } else {
            StringBuilder message = new StringBuilder("Here are your tickets customer " + nifNum + ":\n");
            for (String ticket : tickets) {
                message.append(ticket).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    private List<String> getTicketsForCustomer(int nif) {
        List<String> tickets = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket WHERE nif = ?")) {
            statement.setInt(1, nif);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String value = resultSet.getString("value");
                String expiry_date = resultSet.getString("expiry_date");
                String ticketDetails = String.format("Date: %s, value: %s, Expiry Date : %s", date, value, expiry_date);
                tickets.add(ticketDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    private void handleGoBackButton() {
        this.dispose();
        CustomerForm customerForm = new CustomerForm(nifNum);
        customerForm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewMenuForm viewMenuForm = new ViewMenuForm(-1);
            viewMenuForm.setVisible(true);
        });
    }
}
