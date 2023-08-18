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
            System.out.println("====================MANAGER MENU====================");
            System.out.println("Welcome, " + getEmployee().getName() + "!");
            System.out.println("Please choose an option: ");
            System.out.println("""
                    1 - Insert information
                    2 - Update information
                    3 - View information
                    4 - Deactivate information
                    0 - Exit""");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if(s.matches("^[0-9]$")) {
                choice = Integer.parseInt(s);
            }
            else {
                choice = -1;
            }
            switch (choice) {
                case 1 -> insertMenu();
                case 2 -> updateMenu();
                case 3 -> viewMenu();
                case 4 -> deactivateMenu();
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
            if(s.matches("^[0-9]$")) {
                choice = Integer.parseInt(s);
            }
            else {
                choice = -1;
            }
            switch (choice) {
              case 1 -> insertCustomer();
            case 2 -> insertEmployee();
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
            if(s.matches("^[0-9]$")) {
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
                    5 - Update an employee""");

            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if(s.matches("^[0-9]$")) {
                choice = Integer.parseInt(s);
            }
            else {
                choice = -1;
            }
            switch (choice) {
                case 1 -> menuUpdateCustomer();
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

    @Override
    void deactivateMenu() {
        scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("====================DEACTIVATE MENU====================");
            System.out.println("Please choose an option: ");

            System.out.println("""
                    1 - Deactivate a customer
                    2 - Deactivate a vehicle
                    3 - Deactivate an insurance
                    4 - Deactivate a ticket
                    5 - Deactivate an employee
                    0 - Exit""");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choice = Integer.parseInt(s);
            } else {
                choice = -1;
            }
            switch (choice) {
                case 1 -> deactivateCustomer();
                case 2 -> deactivateVehicle();
                case 3 -> deactivateInsurance();
                case 4 -> deactivateTicket();
                case 5 -> deactivateEmployee();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }

    }

    void deactivateEmployee() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF(scan, getLogger());
        getDataSource().deactivateEmployee(nif);
    }

}
