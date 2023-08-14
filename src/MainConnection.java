

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

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.setAccess_level(2);
        e1.setName("Gonçalo Ramos");
        BackOffice.startBackOffice(e1);


//        MainConnection.initiate();

    }

    private static String getVIN() {
        String str = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^[A-Z0-9]{17}$")) {
                str = s;
                validInput = true;
            } else {
                System.out.println("Invalid VIN. Please enter a 17 digit value.");
            }
        } while (!validInput);

        return str;
    }

   /* private static void initiate() {
        try {
            WelcomeMenu wm = new WelcomeMenu();
            wm.run();
        }
        catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }
    }*/

}
