package clientmenu;

import model.TaskManagment;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;


//No Ticket Registration o createTask deve ser assim please(a ordem das strings):
//  createTask("Ticket Registration", nifNum, plate, date, reason, value, expirationDate);
public class InsertMenuForm extends JFrame {

    private int nifNum;

    public InsertMenuForm(int nifNum) {
        this.nifNum = nifNum;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Insert Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("Insert Menu");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(headerLabel, gbc);

        JButton insertVehicleButton = new JButton("Insert Vehicle");
        insertVehicleButton.setBackground(new Color(6, 65, 16));
        insertVehicleButton.setForeground(Color.white);
        insertVehicleButton.setPreferredSize(new Dimension(250, 40));
        insertVehicleButton.addActionListener(e -> showInsertVehicleDialog());
        gbc.gridy = 1;
        panel.add(insertVehicleButton, gbc);

        JButton insertInsuranceButton = new JButton("Insert Insurance");
        insertInsuranceButton.setBackground(new Color(6, 65, 16));
        insertInsuranceButton.setForeground(Color.white);
        insertInsuranceButton.setPreferredSize(new Dimension(250, 40));
        insertInsuranceButton.addActionListener(e -> showInsertInsuranceDialog());
        gbc.gridy = 2;
        panel.add(insertInsuranceButton, gbc);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(32, 32, 32));
        goBackButton.setForeground(Color.white);
        goBackButton.setPreferredSize(new Dimension(250, 40));
        goBackButton.addActionListener(e -> handleGoBackButton());
        gbc.gridy = 3;
        panel.add(goBackButton, gbc);

        add(panel);
    }

    private void showInsertVehicleDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JComboBox<String> brandField = new JComboBox<>(new String[]{" ", "Abarth", "Alfa Romeo", "Aston Martin", "Audi",
                "Bentley", "BMW", "Bugatti", "Cadillac", "Chevrolet", "Chrysler", "Citroen", "Dacia", "Daewoo",
                "Daihatsu", "Dodge", "Donkervoort", "DS", "Ferrari", "Fiat", "Fisker", "Ford", "Honda", "Hummer",
                "Hyundai", "Infiniti", "Iveco", "Jaguar", "Jeep", "Kia", "KTM", "Lada", "Lamborghini", "Lancia",
                "Land Rover", "Landwind", "Lexus", "Lotus", "Maserati", "Maybach", "Mazda", "McLaren", "Mercedes-Benz",
                "MG", "Mini", "Mitsubishi", "Morgan", "Nissan", "Opel", "Peugeot", "Porsche", "Renault", "Rolls-Royce",
                "Rover", "Saab", "Seat", "Skoda", "Smart", "SsangYong", "Subaru", "Suzuki", "Tesla", "Toyota",
                "Volkswagen", "Volvo"});
        JTextField modelField = new JTextField(15);
        JTextField plateField = new JTextField(15);
        JComboBox<String> colorField = new JComboBox<>(new String[]{" ", "Black", "White",
                "Red", "Blue", "Green", "Yellow", "Gray", "Silver", "Brown", "Orange"});
        JDateChooser registrationDateField = new JDateChooser();
        registrationDateField.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JTextField vinField = new JTextField(15);
        JTextField nifField = new JTextField(15);
        JComboBox<String> categoryField = new JComboBox<>(new String[]{" ", "Light Commercial Vehicle",
                "Light Passenger Vehicle",
                "Heavy-duty Commercial Vehicle",
                "Heavy-duty Passenger Vehicle",
                "Motorcycle", "Moped",
                "Heavy-duty Passenger Vehicle"});

        panel.add(new JLabel("Brand:"));
        panel.add(brandField);
        panel.add(new JLabel("Model:"));
        panel.add(modelField);
        panel.add(new JLabel("Plate: XX-XX-XX "));
        panel.add(plateField);
        panel.add(new JLabel("Color:"));
        panel.add(colorField);
        panel.add(new JLabel("Registration Date:"));
        panel.add(registrationDateField);
        panel.add(new JLabel("VIN: (17 characters)"));
        panel.add(vinField);
        panel.add(new JLabel("NIF: (9 digits)"));
        panel.add(nifField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Insert New Vehicle", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String brand = (String) brandField.getSelectedItem();
            String model = modelField.getText();
            String plate = plateField.getText();
            String color = (String) colorField.getSelectedItem();
            String registrationDate = sdf.format(registrationDateField.getDate());
            String vin = vinField.getText();
            String nif = nifField.getText();
            String category = (String) categoryField.getSelectedItem();

            TaskManagment taskManagment = new TaskManagment();
            taskManagment.createTask("Vehicle Registration", Integer.parseInt(nif), plate, vin, color, brand, model, registrationDate, category, nif);
            JOptionPane.showMessageDialog(this, "Insert request has been made.");
        }
    }

    private void showInsertInsuranceDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField plateField = new JTextField(15);
        JComboBox<String> insuranceCategoryField = new JComboBox<>(new String[]{" ", "Third Party",
                "Third Party Fire and Theft", "Third Party Fire and Auto-Liabitlity", "Comprehensive"});
        JTextField policyField = new JTextField(15);
        JDateChooser startDateField = new JDateChooser();
        startDateField.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JDateChooser endDateField = new JDateChooser();
        endDateField.setDateFormatString("yyyy-MM-dd");
        JTextField companyNameField = new JTextField(15);

        panel.add(new JLabel("Plate: "));
        panel.add(plateField);
        panel.add(new JLabel("Insurance Category: "));
        panel.add(insuranceCategoryField);
        panel.add(new JLabel("Policy: "));
        panel.add(policyField);
        panel.add(new JLabel("Start Date: "));
        panel.add(startDateField);
        panel.add(new JLabel("End Date: "));
        panel.add(endDateField);
        panel.add(new JLabel("Company Name: "));
        panel.add(companyNameField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Insert New Insurance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String plate = plateField.getText();
            String insuranceCategory = (String) insuranceCategoryField.getSelectedItem();
            String policy = policyField.getText();
            String startDate = sdf.format(startDateField.getDate());
            String endDate = sdf.format(endDateField.getDate());
            String companyName = companyNameField.getText();

            TaskManagment taskManagment = new TaskManagment();
            taskManagment.createTask("Insurance Registration", nifNum, policy, plate, startDate, insuranceCategory, endDate, companyName);
            JOptionPane.showMessageDialog(this, "Insert request has been made.");
        }
    }

    private void handleGoBackButton() {
        this.dispose();
        CustomerForm customerForm = new CustomerForm(nifNum);
        customerForm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InsertMenuForm insertMenuForm = new InsertMenuForm(-1);
            insertMenuForm.setVisible(true);
        });
    }
}
