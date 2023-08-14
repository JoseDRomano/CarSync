

import employeeacess.BackOffice;
import employeeacess.DataSource;
import model.Employee;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainConnection {

    private static final Logger logger = Logger.getLogger(MainConnection.class);


    public static void main(String[] args) {

        PropertyConfigurator.configure("C:/Users/diogo/IdeaProjects/IMTT-alike/resources/log4j.properties");

        Employee e1 = new Employee();
        e1.setAccess_level(2);
        e1.setName("Gonçalo Ramos");
        BackOffice.startBackOffice(e1);

//        MainConnection.initiate();


    }

    private static void initiate() {
        try {
            WelcomeMenu wm = new WelcomeMenu();
            wm.run();
        }
        catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }
    }

}
