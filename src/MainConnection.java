

import employeeacess.BackOffice;
import employeeacess.DataSource;
import model.Employee;

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
        BackOffice.startBackOffice(e1);
*/
//        MainConnection.initiate();

        /*int totalObjects = 37;
        int rowsPerPage = 8;

        int pages = (int) Math.ceil((double) totalObjects / rowsPerPage);*/

        /*List<String> stringList = List.of("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6",
                "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14",
                "Item 15", "Item 16", "Item 17", "Item 18", "Item 19", "Item 20", "Item 21", "Item 22",
                "Item 23", "Item 24", "Item 25", "Item 26", "Item 27", "Item 28", "Item 29", "Item 30");
        displayList(stringList,  8);*/

        LocalDate ld = LocalDate.now();
        LocalDate localDate = ld.plusYears(3);
        Date expDate = java.sql.Date.valueOf(localDate.toString());

        System.out.println("Today is: " + ld + "\n" + "Expiration date is: " + localDate + "\n" + "Expiration date is: " + expDate);
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
