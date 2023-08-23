import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel {

    private JTextField nifField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("Login");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(headerLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("NIF:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        nifField = new JTextField();
        nifField.setPreferredSize(new Dimension(250, 40));
        contentPanel.add(nifField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 0;
        contentPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 40));
        contentPanel.add(passwordField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.weightx = 0;
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(6, 65, 16));
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setForeground(Color.white);
        contentPanel.add(loginButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(32, 32, 32));
        goBackButton.setForeground(Color.white);
        goBackButton.setPreferredSize(new Dimension(120, 40));
        contentPanel.add(goBackButton, gbc);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGoBackButton();
            }
        });

        Action performLoginAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        };
        nifField.addActionListener(performLoginAction);
        passwordField.addActionListener(performLoginAction);

        gbc.gridy = 0;
        gbc.weighty = 1;
        add(new JPanel(), gbc);
        gbc.gridy = 1;
        add(contentPanel, gbc);
        gbc.gridy = 2;
        add(new JPanel(), gbc);
    }

    private void performLogin() {
        String nif = nifField.getText();
        String password = new String(passwordField.getPassword());

        // Assuming you have the DataSource class and its methods here
        // DataSource dataSource = new DataSource();
        // String result = dataSource.authenticateUser(nif, password);

        // Your authentication logic and action code here

        // Example code for different results
        /*
        switch (result) {
            case DataSource.SUCCESSFUL_LOGIN:
                // Your login success action here
                break;
            case DataSource.WRONG_PASSWORD:
                showLoginErrorMessage("Wrong password. Please try again.");
                break;
            case DataSource.NIF_NOT_REGISTERED:
                showLoginErrorMessage("NIF is not registered. Please register first.");
                break;
            default:
                showLoginErrorMessage("An error occurred. Please try again later.");
                break;
        }
        */
    }

    private void showLoginErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
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
                JFrame frame = new JFrame("Login Form");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.add(new LoginForm());
                frame.setVisible(true);
            }
        });
    }
}
