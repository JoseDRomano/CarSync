

import employeeacess.BackOffice;
import employeeacess.BackOfficeAdminMenu;
import model.Employee;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;


public class MainConnection {

    private static final Logger logger = Logger.getLogger(MainConnection.class);


    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

//        PropertyConfigurator.configure("C:\\Users\\PedroOriakhi\\OneDrive - Polarising, Unipessoal, Lda\\Documentos\\GitHub\\IMTT-alike\\resources\\log4j.properties");
//        BackOffice.startBackOffice(296789012);
//        MainConnection.initiate();
//
        //Menu interativo
        Employee employee = new Employee();
        employee.setName("John Doe");
        BackOfficeAdminMenu backOfficeAdminMenu = new BackOfficeAdminMenu(employee);
/*
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel mainLabel = new JLabel("Back Office Menu");
        mainLabel.setFont(new Font("Arial", Font.BOLD, 80));
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setForeground(Color.BLACK);
        mainPanel.add(mainLabel, gbc);

        JButton[] buttons = {insertMenu, deleteMenu, deactivateMenu, updateMenu, searchMenu, exitButton};
        gbc.gridwidth = 2;
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(new Color(0, 100, 0));
            button.setSize(50, 50);
            button.setForeground(Color.WHITE);
            gbc.gridy++;
            mainPanel.add(button, gbc);
        }

        gbc.anchor = GridBagConstraints.CENTER;

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.pack();
        */


    }

   /* private static void initiate() {
        try {
            WelcomeMenu wm = new WelcomeMenu();
            wm.run();
        } catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }*/



}
