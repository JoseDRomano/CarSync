package employeeacess;

import model.Employee;

import java.util.Scanner;

public class BackOfficeEmployee extends BackOffice{
    Scanner scan;
    BackOfficeEmployee(DataSource dataSource, Employee employee) {
        super(dataSource, employee);
        start();
    }

    @Override
    void start() {
        scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {

            System.out.println("Welcome, " + getEmployee().getName() + "!");
            System.out.println("Please choose an option:");
            System.out.println("To insert information -> 1");
            System.out.println("To update information -> 2");
            System.out.println("To view information -> 3");
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank()) {
                choice = Integer.parseInt(s);
            }
            else {
                choice = -1;
            }

            switch (choice) {
                case 1 -> insertMenu() ;
                case 2 -> updateMenu();
                case 3 -> viewMenu();
                case 0 -> {
                    System.out.println("Exiting...");
                    getDataSource().close();
                }
                default -> System.out.println("Invalid option");
            }
        }
        scan.close();
    }

    @Override
    void insertMenu() {
        scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("====================INSERT MENU====================");
            System.out.println("Please choose an option: ");

            System.out.println("""
            1 - Insert a new customer
            2 - Insert a new vehicle
            3 - Insert a new insurance
            4 - Insert a new ticket""");

            System.out.println("0 - Exit");
            System.out.print("Option: ");


            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank()) {
                choice = Integer.parseInt(s);
            }
            else {
                choice = -1;
            }
            switch (choice) {
//              case 1 -> insertCustomer();
                case 2 -> insertVehicle();
                case 3 -> insertInsurance();
                case 4 -> insertTicket();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    @Override
    void viewMenu() {
        scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("====================INSERT MENU====================");
            System.out.println("Please choose an option: ");

            System.out.println("""
            1 - View customers
            2 - View vehicles
            3 - View insurances
            4 - View tickets""");

            System.out.println("0 - Exit");
            System.out.print("Option: ");


            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank()) {
                choice = Integer.parseInt(s);
            }
            else {
                choice = -1;
            }
            switch (choice) {
                case 1 -> menuViewCustomer();
                case 2 -> menuViewVehicle();
                case 3 -> menuViewInsurance();
                case 4 -> menuViewTicket();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }

        }

    }

    @Override
    void updateMenu() {

    }
}
