import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterForm extends JPanel {

    public static final String DB_NAME = "projeto_imt";
    public static final int PORT_NUMBER = 3306;
    public static final String URL = "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + DB_NAME;
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public RegisterForm() {
        setLayout(new BorderLayout());


        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30));

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(200, 30));

        JLabel birthdateLabel = new JLabel("Birthdate (YYYY-MM-DD):");
        JTextField birthdateField = new JTextField();
        birthdateField.setPreferredSize(new Dimension(200, 30));

        JLabel licenseLabel = new JLabel("Driver License:");
        JTextField licenseField = new JTextField();
        licenseField.setPreferredSize(new Dimension(200, 30));

        JLabel licenseTypeLabel = new JLabel("License Type (A/B/C/D):");
        JComboBox<String> licenseTypeComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D"});
        licenseTypeComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel startingDateLabel = new JLabel("Starting Date (YYYY-MM-DD):");
        JTextField startingDateField = new JTextField();
        startingDateField.setPreferredSize(new Dimension(200, 30));

        JLabel expirationDateLabel = new JLabel("Expiration Date (YYYY-MM-DD):");
        JTextField expirationDateField = new JTextField();
        expirationDateField.setPreferredSize(new Dimension(200, 30));

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(6, 65, 16));
        registerButton.setForeground(Color.white);
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String birthdate = birthdateField.getText();
                String driverLicense = licenseField.getText();
                String licenseType = (String) licenseTypeComboBox.getSelectedItem();
                String startingDate = startingDateField.getText();
                String expirationDate = expirationDateField.getText();

                if (insertUserData(name, address, birthdate, driverLicense, licenseType, startingDate, expirationDate)) {
                    showRegistrationSuccessMessage();
                } else {
                    showRegistrationErrorMessage();
                }
            }
        });

        gbc.gridwidth = 2;
        formPanel.add(nameLabel, gbc);
        gbc.gridy++;
        formPanel.add(nameField, gbc);
        gbc.gridy++;
        formPanel.add(addressLabel, gbc);
        gbc.gridy++;
        formPanel.add(addressField, gbc);
        gbc.gridy++;
        formPanel.add(birthdateLabel, gbc);
        gbc.gridy++;
        formPanel.add(birthdateField, gbc);
        gbc.gridy++;
        formPanel.add(licenseLabel, gbc);
        gbc.gridy++;
        formPanel.add(licenseField, gbc);
        gbc.gridy++;
        formPanel.add(licenseTypeLabel, gbc);
        gbc.gridy++;
        formPanel.add(licenseTypeComboBox, gbc);
        gbc.gridy++;
        formPanel.add(startingDateLabel, gbc);
        gbc.gridy++;
        formPanel.add(startingDateField, gbc);
        gbc.gridy++;
        formPanel.add(expirationDateLabel, gbc);
        gbc.gridy++;
        formPanel.add(expirationDateField, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(registerButton, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(32, 32, 32));
        goBackButton.setForeground(Color.white);
        goBackButton.setPreferredSize(new Dimension(120, 40));
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                handleGoBackButton();
            }
        });
        buttonPanel.add(goBackButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Register Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(this);
        frame.setVisible(true);
    }

    private boolean insertUserData(String nif, String name, String address, String birthdate,
                                   String driverLicense, String licenseType, String startingDate) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String hashedPassword = BCrypt.hashpw("yourPasswordHere", BCrypt.gensalt());

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO person (nif, name, address, birthdate, password) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, nif);
            statement.setString(2, name);
            statement.setString(3, address);
            statement.setString(4, birthdate);
            statement.setString(5, hashedPassword);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {

                PreparedStatement customerStatement = connection.prepareStatement(
                        "INSERT INTO customer (nif, driver_license, license_type, starting_date, expiration_date) " +
                                "VALUES (?, ?, ?, ?, ?)");
                customerStatement.setString(1, nif);
                customerStatement.setString(2, driverLicense);
                customerStatement.setString(3, licenseType);
                customerStatement.setString(4, startingDate);

                int customerRowsAffected = customerStatement.executeUpdate();
                return customerRowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void showRegistrationSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Registration successful. Please log in.",
                "Registration Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showRegistrationErrorMessage() {
        JOptionPane.showMessageDialog(this, "Registration failed. Please try again later.",
                "Registration Error", JOptionPane.ERROR_MESSAGE);
    }

    private void handleGoBackButton() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
        WelcomeMenuForm welcomeMenuForm = new WelcomeMenuForm();
        welcomeMenuForm.show();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterForm();
            }
        });
    }
}