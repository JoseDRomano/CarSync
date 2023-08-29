package clientmenu;

import javax.swing.*;
import java.awt.*;

public class CustomerForm extends JFrame {

    private int nifNum;

    public CustomerForm(int nifNum) {
        this.nifNum = nifNum;

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Customer Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + nifNum);
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerForm customerForm = new CustomerForm(260856140);
            customerForm.setVisible(true);
        });
    }
}
