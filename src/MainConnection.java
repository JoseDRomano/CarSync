

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

    public static void main(String[] args) {

        PropertyConfigurator.configure("C:\\Users\\PedroOriakhi\\OneDrive - Polarising, Unipessoal, Lda\\Documentos\\GitHub\\IMTT-alike\\resources\\log4j.properties");
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setNif(296789012);
        employee.setAccess_level(2);
        BackOffice.startBackOffice(296789012);
//        MainConnection.initiate();

        //Password para Pedro Ribeiro - 123abc / NIF - 200000000
        // Licença - 10000000
        //Password para nif 200000001 (Diogo Amazonia) - Password é abc123
        //Password para nif 200000002 (Jose Sem Fim) - Password a1b2c3


//        WelcomeMenuForm welcomeMenuForm = new WelcomeMenuForm();
//        welcomeMenuForm.show();


        //Menu interativo
       /* Employee employee = new Employee();
        employee.setName("John Doe");
        BackOfficeAdminMenu backOfficeAdminMenu = new BackOfficeAdminMenu(employee);*/


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
