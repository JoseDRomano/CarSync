package clientmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterForm extends JPanel {

    public static final String DB_NAME = "projeto_imt";
    public static final int PORT_NUMBER = 3306;
    public static final String URL = "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + DB_NAME;
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    private JTextField nifField, nameField, emailField, addressField, b_dateField, licenseField;
    private JComboBox<String> licenseTypeComboBox;
    private JTextField startingDateField, expirationDateField;
    private JPasswordField passwordField;

    public RegisterForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        initComponents();
        buildUI();
    }

    private void initComponents() {
        nifField = new JTextField(50);
        passwordField = new JPasswordField(20);
        nameField = new JTextField(50);
        emailField = new JTextField(20);
        addressField = new JTextField(20);
        b_dateField = new JTextField(20);
        licenseField = new JTextField(20);
        licenseTypeComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D"});
        startingDateField = new JTextField(20);
        expirationDateField = new JTextField(20);
    }

    private void buildUI() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JLabel("NIF:"), gbc);
        gbc.gridx++;
        formPanel.add(nifField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx++;
        formPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx++;
        formPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx++;
        formPanel.add(emailField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx++;
        formPanel.add(addressField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Birthdate (YYYY-MM-DD):"), gbc);
        gbc.gridx++;
        formPanel.add(b_dateField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Driver License:"), gbc);
        gbc.gridx++;
        formPanel.add(licenseField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("License Type (A/B/C/D):"), gbc);
        gbc.gridx++;
        formPanel.add(licenseTypeComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Starting Date (YYYY-MM-DD):"), gbc);
        gbc.gridx++;
        formPanel.add(startingDateField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Expiration Date (YYYY-MM-DD):"), gbc);
        gbc.gridx++;
        formPanel.add(expirationDateField, gbc);
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(6, 65, 16));
        registerButton.setForeground(Color.white);
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertUserData();
            }
        });
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(registerButton, gbc);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(32, 32, 32));
        goBackButton.setPreferredSize(new Dimension(120, 40));
        goBackButton.setForeground(Color.white);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGoBackButton();
            }
        });
        buttonPanel.add(goBackButton);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void insertUserData() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String insertIntoPerson = "INSERT INTO person(nif, name, address, b_date, password, email) VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertIntoPerson);
            pstmt.setInt(1, Integer.parseInt(nifField.getText()));
            pstmt.setString(2, nameField.getText());
            pstmt.setString(3, addressField.getText());
            pstmt.setString(4, b_dateField.getText());
            pstmt.setString(5, BCrypt.hashpw(new String(passwordField.getPassword()), BCrypt.gensalt()));
            pstmt.setString(6, emailField.getText());
            pstmt.executeUpdate();
            String insertIntoCustomer = "INSERT INTO customer(driver_license_number, license_type, start_date, expiration_date, nif) VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(insertIntoCustomer);
            pstmt.setString(1, licenseField.getText());
            pstmt.setString(2, (String) licenseTypeComboBox.getSelectedItem());
            pstmt.setString(3, startingDateField.getText());
            pstmt.setString(4, expirationDateField.getText());
            pstmt.setInt(5, Integer.parseInt(nifField.getText()));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registration Successful!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to Register. Please Try Again.");
            e.printStackTrace();
        }
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
                RegisterForm registerForm = new RegisterForm();
                JFrame frame = new JFrame("Register Form");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.add(registerForm);
                frame.setVisible(true);
            }
        });
    }
}
