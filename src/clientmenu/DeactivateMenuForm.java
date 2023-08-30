package clientmenu;

import model.TaskManagment;
import javax.swing.*;
import java.awt.*;

public class DeactivateMenuForm extends JFrame {

    private int nifNum;

    public DeactivateMenuForm(int nifNum) {
        this.nifNum = nifNum;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Deactivate Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("Deactivate Menu");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(headerLabel, gbc);

        JButton deactivateVehicleButton = new JButton("Deactivate Vehicle");
        deactivateVehicleButton.setBackground(new Color(65, 13, 11));
        deactivateVehicleButton.setForeground(Color.white);
        deactivateVehicleButton.setPreferredSize(new Dimension(250, 40));
        deactivateVehicleButton.addActionListener(e -> showDeactivateVehicleDialog());
        gbc.gridy = 1;
        panel.add(deactivateVehicleButton, gbc);

        JButton deactivateInsuranceButton = new JButton("Deactivate Insurance");
        deactivateInsuranceButton.setBackground(new Color(65, 13, 11));
        deactivateInsuranceButton.setForeground(Color.white);
        deactivateInsuranceButton.setPreferredSize(new Dimension(250, 40));
        deactivateInsuranceButton.addActionListener(e -> showDeactivateInsuranceDialog());
        gbc.gridy = 2;
        panel.add(deactivateInsuranceButton, gbc);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(32, 32, 32));
        goBackButton.setForeground(Color.white);
        goBackButton.setPreferredSize(new Dimension(250, 40));
        goBackButton.addActionListener(e -> handleGoBackButton());
        gbc.gridy = 3;
        panel.add(goBackButton, gbc);

        add(panel);
    }

    private void showDeactivateVehicleDialog() {
        String plate = JOptionPane.showInputDialog(this, "Enter Vehicle Plate to deactivate:");
        if (plate != null && !plate.trim().isEmpty()) {
            TaskManagment taskManagment = new TaskManagment();
            taskManagment.createTask("Vehicle Deactivation", nifNum, plate);
            JOptionPane.showMessageDialog(this, "Deactivation request has been made.");
        }
    }

    //Mano aqui é suposto ser insurance policy essa é a primary key da tabela insurance
    private void showDeactivateInsuranceDialog() {
        String insuranceID = JOptionPane.showInputDialog(this, "Enter Insurance ID to deactivate:");
        if (insuranceID != null && !insuranceID.trim().isEmpty()) {
            TaskManagment taskManagment = new TaskManagment();
            taskManagment.createTask("Insurance Deactivation", nifNum, insuranceID);
            JOptionPane.showMessageDialog(this, "Deactivation request has been made.");
        }
    }

    private void handleGoBackButton() {
        this.dispose();
        CustomerForm customerForm = new CustomerForm(nifNum);
        customerForm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeactivateMenuForm deactivateMenuForm = new DeactivateMenuForm(-1);
            deactivateMenuForm.setVisible(true);
        });
    }
}
