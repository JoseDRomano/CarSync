import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageNavigationExample {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel page1;
    private JPanel page2;
    // Add more pages as needed

    public PageNavigationExample() {
        frame = new JFrame("Page Navigation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new CardLayout());

        createPage1();
        createPage2();
        // Create more pages here and add them to the mainPanel

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void createPage1() {
        page1 = new JPanel();
        JButton button1 = new JButton("Go to Page 1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "page1");
            }
        });
        page1.add(button1);
        mainPanel.add(page1, "page1");
    }

    private void createPage2() {
        page2 = new JPanel();
        JButton button2 = new JButton("Go to Page 2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "page2");
            }
        });
        page2.add(button2);
        mainPanel.add(page2, "page2");
    }

    // Add methods to create more pages here

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PageNavigationExample();
            }
        });
    }
}
