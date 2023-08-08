package employeeacess;

import model.Employee;

import java.util.Scanner;

public class BackOfficeEmployeeManager extends BackOffice {
    Scanner scan;
    BackOfficeEmployeeManager(DataSource dataSource, Employee employee) {
        super(dataSource, employee);
        start();
    }

    @Override
    void start() {
        scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("Welcome, " + getEmployee().getName() + "!");
            System.out.println("Please choose an option: ");
            System.out.println("To insert information -> 1");
            System.out.println("To update information -> 2");
            System.out.println("To view information -> 3");
            System.out.println("0 - Exit");
            System.out.print("Option: " + "\n");

            String s = scan.nextLine().trim();
            if (!s.isEmpty() || !s.isBlank()) {
                choice = Integer.parseInt(s);
            } else {
                choice = -1;
            }
            switch (choice) {
                case 1 -> insertMenu();
                case 2 -> updateMenu();
                case 3 -> viewMenu();
                case 0 -> {
                    System.out.println("Exiting...");
                    getDataSource().close();
                }
                default -> System.out.println("Invalid option, please try again");
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
            2 - Insert a new employee
            3 - Insert a new vehicle
            4 - Insert a new insurance
            5 - Insert a new ticket""");

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
//              case 2 -> insertEmployee();
                case 3 -> insertVehicle();
                case 4 -> insertInsurance();
                case 5 -> insertTicket();
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
            2 - View employees
            3 - View vehicles
            4 - View insurances
            5 - View tickets""");

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
                case 2 -> menuViewEmployee();
                case 3 -> menuViewVehicle();
                case 4 -> menuViewInsurance();
                case 5 -> menuViewTicket();
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
        scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("====================UPDATE MENU====================");
            System.out.println("Please choose an option: ");

            System.out.println("""
                    1 - Update a customer
                    2 - Update a vehicle
                    3 - Update an insurance
                    4 - Update a ticket
                    5 - Update an employee
                    """);

            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (!s.isEmpty() || !s.isBlank()) {
                choice = Integer.parseInt(s);
            } else {
                choice = -1;
            }
            switch (choice) {
                case 1 -> updateCustomer();
                case 2 -> menuUpdateVehicle();
                case 3 -> menuUpdateInsurance();
                case 4 -> menuUpdateTicket();
                case 5 -> updateEmployee();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

}
