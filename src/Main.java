import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10); // Padding

        // Add label for page title at the top
        JLabel pageTitleLabel = new JLabel("Page Title");
        pageTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.PAGE_START;
        panel.add(pageTitleLabel, gbc);

        // Reset gridwidth for other components
        gbc.gridwidth = 1;

        // Add UI components to the panel
        for (int row = 1; row <= 10; row++) {
            JLabel label = new JLabel("Label " + row);
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(label, gbc);

            JTextField textField = new JTextField(15);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(textField, gbc);
        }

        // Add Exit and Back buttons at the bottom
        JButton exitButton = new JButton("Exit");
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10); // Padding
        panel.add(exitButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.insets = new Insets(20, 10, 5, 10); // Padding
        panel.add(backButton, gbc);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
