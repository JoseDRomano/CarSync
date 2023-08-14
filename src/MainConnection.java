

import employeeacess.BackOffice;
import employeeacess.DataSource;
import model.Employee;
import util.LogUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainConnection {

    public static void main(String[] args) {
       /* Employee e1 = new Employee();
        e1.setAccess_level(2);
        e1.setName("Gonçalo Ramos");
        BackOffice.startBackOffice(e1);*/

        LogUtil.info("Admin logged in");

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
