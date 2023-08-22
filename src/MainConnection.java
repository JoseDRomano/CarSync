

import employeeacess.BackOfficeMenu;
import employeeacess.MenuEmployee;
import model.Employee;
import model.TaskManagment;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MainConnection {

    private static final Logger logger = Logger.getLogger(MainConnection.class);


    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

//        PropertyConfigurator.configure("C:\\Users\\PedroOriakhi\\OneDrive - Polarising, Unipessoal, Lda\\Documentos\\GitHub\\IMTT-alike\\resources\\log4j.properties");
//        BackOffice.startBackOffice(296789012);
//        MainConnection.initiate();

        //Menu interativo
        Employee employee = new Employee();
        employee.setName("John Doe");
        //BackOfficeMenu backOfficeMenu = new BackOfficeMenu(employee);

        MenuEmployee menuEmployee = new MenuEmployee(employee);
    }

  /*  private static void initiate() {
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
