package employeeacess;

import model.Employee;
import util.LogUtil;

import java.sql.Date;
import java.util.Scanner;

public class BackOfficeAdmin extends BackOffice{
    Scanner scan;
    private LogUtil logUtil;
    BackOfficeAdmin(DataSource dataSource, Employee employee) {
        super(dataSource, employee);
        logUtil = new LogUtil();
        LogUtil.info("Admin logged in");
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
            System.out.println("To delete information -> 3");
            System.out.println("To view information -> 4");
            System.out.println("0 - Exit");
            System.out.print("Option: ");
            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choice = Integer.parseInt(s);
            }
            else {
                choice = -1;
            }
            switch (choice) {
                case 1 -> insertMenu();
                case 2 -> updateMenu();
                case 3 -> deleteMenu();
                case 4 -> viewMenu();
                case 0 -> {
                    System.out.println("Exiting...");
                    getDataSource().close();
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
        scan.close();
    }

    private void deleteMenu() {
        scan = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("====================DELETE MENU====================");
            System.out.println("Please choose an option: ");

            System.out.println("""
            1 - Delete a customer
            2 - Delete a employee
            3 - Delete a vehicle
            4 - Delete a insurance
            5 - Delete a ticket
            0 - Exit""");

            System.out.print("Option: ");


            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choice = Integer.parseInt(s);
            }
            else {
                choice = -1;
            }
            switch (choice) {
                case 1 -> deleteCustomer();
                case 2 -> deleteEmployee();
                case 3 -> deleteVehicle();
                case 4 -> deleteInsurance();
                case 5 -> deleteTicket();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void deleteTicket() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        System.out.println("Enter plate number: ");
        String plateNumberForTicket = getPlate();
        System.out.println("Enter date (yyyy-mm-dd):");
        Date ticketDate = getDate();

        getDataSource().deleteTicket(nif, plateNumberForTicket, ticketDate);
    }

    private void deleteInsurance() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }

        System.out.println("Enter policy number: ");
        int policyNumber = getPolicy();
        System.out.println("Enter NIF: ");
        int nif3 = getNIF();

        getDataSource().deleteInsurance(policyNumber, nif3);
    }

    private void deleteVehicle() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
        System.out.println("Enter plate number: ");
        String plate= getPlate();
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        getDataSource().deleteVehicle(plate, nif);
    }

    private void deleteEmployee() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
    }

    private void deleteCustomer() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
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
            System.out.println("====================VIEW MENU====================");
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choice = Integer.parseInt(s);
            }
            else {
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
