package employeeacess;

import model.Employee;

import java.sql.Date;
import java.util.Scanner;


//Adicionar forma de inserir, ou fazer update de multiplos campos de uma só vez
//Fazer o view e o delete
//Se calhar devia-se usar herança para fazer backoffice para cada tipo de employee. Os métodos em comum
//eram herdados e os outros implementados na classe em específico.

public abstract class BackOffice {

    private DataSource dataSource;
    private Scanner scan;
    private Employee employee;
    private static BackOffice instance = null;

    BackOffice(DataSource dataSource, Employee employee) {
        this.dataSource = dataSource;
        this.employee = employee;
    }

    public static void startBackOffice(Employee employee) {
        DataSource dataSource = new DataSource();

        if (employee == null) {
            throw new IllegalArgumentException("DataSource and Employee cannot be null");
        }

        if (!dataSource.open()) {
            throw new IllegalStateException("Cannot connect to database");
        }

        switch (employee.getAccess_level()) {
            case 0 -> {
                BackOfficeEmployee boe = new BackOfficeEmployee(dataSource, employee);
                return;
            }

            case 1 -> {
                BackOfficeEmployeeManager boem = new BackOfficeEmployeeManager(dataSource, employee);
                return;
            }

            case 2 -> {
                BackOfficeAdmin boea = new BackOfficeAdmin(dataSource, employee);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid access level");
    }

    abstract void start();

    abstract void insertMenu();

    DataSource getDataSource() {
        return dataSource;
    }
    Employee getEmployee() {
        return employee;
    }

    abstract void viewMenu();

    abstract void updateMenu();

    protected void insertVehicle() {
        System.out.println("Insert 0 to go back or 1 to start submetting vehicle information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to insert menu...");
            return;
        }
        System.out.println("Enter plate number in format XX-XX-XX: ");
        String plateNumber = scan.nextLine().trim();
        System.out.println("Enter VIN: ");
        String vin = scan.nextLine().trim();
        System.out.println("Enter color: ");
        String color = scan.nextLine().replaceAll("\n", "");
        System.out.println("Enter brand: ");
        String brand = scan.nextLine().replaceAll("\n", "");
        System.out.println("Enter model: ");
        String model = scan.nextLine().replaceAll("\n", "");
        System.out.println("Enter registration date (yyyy-mm-day):");
        Date registrationDate = Date.valueOf(scan.nextLine().trim());
        System.out.println("""
                Enter vehicle category:
                Light Commercial Vehicle -> 1
                Light Passenger Vehicle -> 2
                Heavy-duty Passenger Vehicle -> 3
                Heavy-Duty Goods Vehicle -> 4
                Motorcycle -> 5
                Moped -> 6""");
        int categoryNumber = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter NIF: ");
        int nif2 = Integer.parseInt(scan.nextLine().trim());
        dataSource.insertVehicle(plateNumber, vin, color, brand, model, registrationDate, categoryNumber, nif2);
    }

    protected void insertCustomer() {
    }

    protected void insertEmployee() {
    }

    protected void insertInsurance() {
        System.out.println("Insert 0 to go back or 1 to start submetting insurance information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to insert menu..." + "\n");
            return;
        }
        System.out.println("Enter policy number: ");
        int policyNumber = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter plate number in format XX-XX-XX: ");
        String plateNumberForInsurance = scan.nextLine().trim();
        System.out.println("Enter start date (yyyy-mm-dd):");
        Date startDate = Date.valueOf(scan.nextLine().trim());
        System.out.println("""
                Enter extra category number for:
                Comprehensive Insurance -> 1
                Auto Liability Insurance -> 2
                Theft Insurance -> 3""");
        int extraCategory = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date expiryDate = Date.valueOf(scan.nextLine().trim());
        System.out.println("Enter company name:" );
        String companyName = scan.nextLine().replaceAll("\n", "");
        System.out.println("Enter NIF: ");
        int nif3 = Integer.parseInt(scan.nextLine().trim());
        dataSource.insertInsurance(policyNumber, plateNumberForInsurance, startDate, extraCategory, expiryDate, companyName, nif3);
    }

    protected void insertTicket() {
        System.out.println("Insert 0 to go back or 1 to start submetting ticket information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to insert menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int driverLicenseNumber = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter plate number: ");
        String plateNumberForTicket = scan.nextLine().trim();
        System.out.println("Enter date (yyyy-mm-dd):");
        Date ticketDate = Date.valueOf(scan.nextLine().trim());
        System.out.println("""
                Enter reason number for:
                Speeding -> 1
                Red Light -> 2
                Illegal Parking -> 3
                Reckless Driving -> 4
                DUI -> 5""");
        int reason = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter ticket value: ");
        double ticketValue = Double.parseDouble(scan.nextLine().trim());
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date ticketExpiryDate = Date.valueOf(scan.nextLine().trim());
        dataSource.insertTicket(driverLicenseNumber, plateNumberForTicket, ticketDate, reason, ticketValue, ticketExpiryDate);
    }

    protected void updateVehicleColor() {
        System.out.println("Insert 0 to go back or 1 to start submetting vehicle information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate number: ");
        String plateForUpdateColor = scan.nextLine().trim();
        System.out.println("Enter new color: ");
        String newColor = scan.nextLine().replaceAll("\n", "");
        System.out.println("Enter NIF: ");
        int nif4 = Integer.parseInt(scan.nextLine().trim());
        dataSource.updateVehicleColor(newColor, plateForUpdateColor, nif4);
    }

    protected void changeVehicleOwner() {
        System.out.println("Insert 0 to go back or 1 to start submetting vehicle information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate number in format XX-XX-XX:");
        String plateForChangeOwner = scan.nextLine().trim();
        System.out.println("Enter new owner NIF:");
        int newOwnerNif = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter old owner NIF:");
        int oldOwnerNif = Integer.parseInt(scan.nextLine().trim());
        dataSource.changeVehicleOwner(plateForChangeOwner, oldOwnerNif, newOwnerNif);
    }

    protected void renewInsurance() {
        System.out.println("Insert 0 to go back or 1 to start submetting insurance information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter insurance policy number: ");
        int policyForRenew = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter start date (yyyy-mm-dd):");
        Date newStartDate = Date.valueOf(scan.nextLine().trim());
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date newExpiryDate = Date.valueOf(scan.nextLine().trim());
        System.out.println("Enter extra category:");
        int newExtraCategory = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter company name:");
        String newCompanyName = scan.nextLine().replaceAll("\n", "");
        int nif5 = Integer.parseInt(scan.nextLine().trim());
        dataSource.renewInsurance(newStartDate, newExpiryDate, newExtraCategory, newCompanyName, policyForRenew, nif5);
    }

    protected void payTicket() {
        System.out.println("Insert 0 to go back or 1 to start submetting ticket information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter plate number in format XX-XX-XX:");
        String plate7 = scan.nextLine().trim();
        System.out.println("Enter date (yyyy-mm-dd):");
        Date date7 = Date.valueOf(scan.nextLine().trim());
        System.out.println("Amount of money: ");
        double d7 = Double.parseDouble(scan.nextLine().trim());
        dataSource.payTicket(nif7, plate7, date7, d7);
    }

    protected void menuUpdateVehicle() {
        scan = new Scanner(System.in);
        int choiceUV = -1;
        while (choiceUV != 0) {
            System.out.println("====================UPDATE VEHICLE MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("1 - Update vehicle color");
            System.out.println("2 - Change vehicle owner");
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank()) {
                choiceUV = Integer.parseInt(s);
            }
            else {
                choiceUV = -1;
            }
            switch (choiceUV) {
                case 1 -> updateVehicleColor();
                case 2 -> changeVehicleOwner();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
        scan.close();
    }

    protected void menuUpdateTicket() {
        scan = new Scanner(System.in);
        int choiceUT = -1;
        while (choiceUT != 0) {
            System.out.println("====================UPDATE TICKET MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("1 - Pay ticket");
            System.out.println("2 - Update expired ticket");
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank()) {
                choiceUT = Integer.parseInt(s);
            }
            else {
                choiceUT = -1;
            }
            switch (choiceUT) {
                case 1 -> payTicket();
//                case 2 -> updateExpiredTicket();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    protected void menuUpdateInsurance() {
        scan = new Scanner(System.in);
        int choiceUI = -1;
        while (choiceUI != 0) {
            System.out.println("====================UPDATE INSURANCE MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("1 - Renew insurance");
            //          System.out.println("2 - Change insurance category");
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank()) {
                choiceUI = Integer.parseInt(s);
            }
            else {
                choiceUI = -1;
            }

            switch (choiceUI) {
                case 1 -> payTicket();
                case 2 -> renewInsurance();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    protected void updateEmployee() {
        System.out.println("Insert 0 to go back or 1 to start submetting ticket information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter new access level:");
        int acl = Integer.parseInt(scan.nextLine().trim());
        double d7 = Double.parseDouble(scan.nextLine().trim());
//      dataSource.updateCustomer(nif7, dln7, date7, expdate7);
    }

    protected void menuViewVehicle() {
    }

    protected void menuViewTicket() {
    }

    protected void menuViewInsurance() {
    }

    protected void menuViewEmployee() {
    }
    protected void menuViewCustomer() {
    }

    protected void updateCustomer() {
        System.out.println("Insert 0 to go back or 1 to start submetting ticket information");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter driver license number:");
        int dln7 = Integer.parseInt(scan.nextLine().trim());
        System.out.println("Enter date (yyyy-mm-dd):");
        Date date7 = Date.valueOf(scan.nextLine().trim());
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date expdate7 = Date.valueOf(scan.nextLine().trim());
        double d7 = Double.parseDouble(scan.nextLine().trim());
//      dataSource.updateCustomer(nif7, dln7, date7, expdate7);
    }
}
