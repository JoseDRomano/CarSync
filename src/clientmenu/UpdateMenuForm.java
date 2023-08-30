package clientmenu;

import javax.swing.*;
import java.awt.*;

public class UpdateMenuForm extends JFrame {

    private int nifNum;

    public UpdateMenuForm(int nifNum) {
        this.nifNum = nifNum;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Update Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("Update Menu");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(headerLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 1;
        JButton updateCarButton = new JButton("Update Car Details");
        updateCarButton.setBackground(new Color(6, 65, 16));
        updateCarButton.setPreferredSize(new Dimension(180, 40));
        updateCarButton.setForeground(Color.white);
        contentPanel.add(updateCarButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(32, 32, 32));
        goBackButton.setForeground(Color.white);
        goBackButton.setPreferredSize(new Dimension(120, 40));
        contentPanel.add(goBackButton, gbc);

        updateCarButton.addActionListener(e -> handleUpdateCarDetails());
        goBackButton.addActionListener(e -> handleGoBackButton());

        GridBagConstraints frameGbc = new GridBagConstraints();
        frameGbc.gridy = 0;
        add(new JPanel(), frameGbc);
        frameGbc.gridy = 1;
        add(contentPanel, frameGbc);
        frameGbc.gridy = 2;
        add(new JPanel(), frameGbc);
    }

    private void handleUpdateCarDetails() {
        JOptionPane.showMessageDialog(this, "Update Car Details functionality not implemented yet.");
    }

    private void handleGoBackButton() {
        this.dispose();
        CustomerForm customerForm = new CustomerForm(nifNum);
        customerForm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateMenuForm updateMenuForm = new UpdateMenuForm(-1);
            updateMenuForm.setVisible(true);
        });
    }
}
