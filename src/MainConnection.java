

import employeeacess.BackOffice;
import employeeacess.DataSource;
import model.Employee;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainConnection {

    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.setAccess_level(2);
        e1.setName("Gonçalo Ramos");
        BackOffice.startBackOffice(e1);

//        MainConnection.initiate();

        /*int totalObjects = 37;
        int rowsPerPage = 8;

        int pages = (int) Math.ceil((double) totalObjects / rowsPerPage);*/

        /*List<String> stringList = List.of("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6",
                "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14",
                "Item 15", "Item 16", "Item 17", "Item 18", "Item 19", "Item 20", "Item 21", "Item 22",
                "Item 23", "Item 24", "Item 25", "Item 26", "Item 27", "Item 28", "Item 29", "Item 30");
        displayList(stringList,  8);*/


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


    public static void displayList(List<?> objects, int rowsPerPage) {
        Scanner scanner = new Scanner(System.in);
        int currentPosition = 0; // Always start from the first position
        int totalObjects = objects.size();

        int pages = (int) Math.ceil((double) totalObjects / rowsPerPage);
        int aux = 1;

        while (currentPosition < totalObjects) {
            System.out.println("Displaying from page " + aux + " of " + pages + ":");

            for (int i = currentPosition; i < Math.min(currentPosition + rowsPerPage, totalObjects); i++) {
                System.out.println(objects.get(i));
            }

            System.out.print("\nOptions: (C)ontinue, (P)revious, (B)ack to menu: ");
            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "C" -> {
                    aux++;
                    currentPosition += rowsPerPage;
                }
                case "P" -> {
                    aux--;
                    aux = Math.max(1, aux);
                    currentPosition = Math.max(0, currentPosition - rowsPerPage);
                }
                case "B" -> {
                    return;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }

}
