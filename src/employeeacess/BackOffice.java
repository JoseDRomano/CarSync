package employeeacess;

import model.*;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public abstract class BackOffice implements getInputValues {


    private static final Logger logger = Logger.getLogger(BackOffice.class);
    private DataSource dataSource;
    private Scanner scan;
    private Employee employee;

    BackOffice(DataSource dataSource, Employee employee) {
        this.employee = employee;
        this.dataSource = dataSource;
        this.scan = new Scanner(System.in);

        logger.info("BackOffice initialized for employee: " + employee.getNif());
    }

    public static void startBackOffice(int nif) {
        DataSource dataSource = new DataSource();

        if (!dataSource.open()) {
            throw new IllegalStateException("Cannot connect to database");
        }

        Employee employee1 = new Employee();
        for (Employee e : dataSource.queryEmployees()) {
            if (e.getNif() == nif) {
                employee1 = e;
                break;
            }
        }

        //Temporario
        employee1.setAccess_level(2);
        employee1.setName("Pedro");
        switch (employee1.getAccess_level()) {
            case 0 -> {
                logger.info("Starting BackOffice for Employee level 0");
                new BackOfficeEmployee(dataSource, employee1);
                return;
            }

            case 1 -> {
                logger.info("Starting BackOffice for Employee Manager");
                new BackOfficeEmployeeManager(dataSource, employee1);
                return;
            }

            case 2 -> {
                logger.info("Starting BackOffice for Admin");
                new BackOfficeAdmin(dataSource, employee1);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid access level");
    }

    Logger getLogger() {
        return logger;
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

    abstract void deactivateMenu();

    protected void insertVehicle() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to insert menu..." + "\n");
            return;
        }
        System.out.println("Enter plate number in format XX-XX-XX: ");
        String plateNumber = getPlate(scan);
        System.out.println("Enter VIN: ");
        String vin = getVIN(scan);
        System.out.println("Enter color: ");
        String color = getString(scan);
        System.out.println("Enter brand: ");
        String brand = getString(scan);
        System.out.println("Enter model: ");
        String model = getString(scan);
        System.out.println("Enter registration date (yyyy-mm-day):");
        Date registrationDate = getDate(scan);
        System.out.println("""
                Enter vehicle category:
                    Light Commercial Vehicle -> 1
                    Light Passenger Vehicle -> 2
                    Heavy-duty Passenger Vehicle -> 3
                    Heavy-Duty Goods Vehicle -> 4
                    Motorcycle -> 5
                Moped -> 6""");
        int categoryNumber = getInteger(scan);
        System.out.println("Enter NIF: ");
        int nif2 = getNIF(scan);

//        dataSource.insertVehicle(plateNumber, vin, color, brand, model, registrationDate, categoryNumber, nif2);
    }


    protected void insertCustomer() {
        logger.info("Starting insertCustomer method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }

        System.out.println("Enter NIF: ");
        int nif = getNIF(scan);
        System.out.println("Enter name: ");
        String name = getString(scan);
        System.out.println("Enter address: ");
        String address = getString(scan);
        System.out.println("Enter birth date with format yyyy-mm-dd: ");
        Date birthDate = getBirthDate(scan);
        System.out.println("Enter password: ");
        String password = getPassword(scan);
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Enter driverLicense: ");
        int driverLicense = getDriverLicense(scan);
        System.out.println("""
                Enter license type:
                    1 for A
                    2 for B
                    3 for C
                    4 for D""");
        //Aqui nao deve ser getInteger, deve ser getLicenseType porque apenas aceita 1,2,3,4
        int licenseType = getInteger(scan);
        System.out.println("Enter registration date: ");
        Date registrationDate = getDate(scan);
        System.out.println("Enter expiration date: ");
        Date expirationDate = getDate(scan);
        System.out.println("Enter email: ");
        String email = getEmail(scan);
//        dataSource.insertCustomer(nif, name, address, birthDate, password, email,
//                driverLicense, licenseType, registrationDate, expirationDate);
        logger.info("Employee insertion completed.");
    }

    protected void insertEmployee() {
        logger.info("Starting insertEmployee method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }

        System.out.println("Enter NIF: ");
        int nif = getNIF(scan);
        System.out.println("Enter name: ");
        String name = getString(scan);
        System.out.println("Enter address: ");
        String address = getString(scan);
        System.out.println("Enter birth date with format yyyy-mm-dd: ");
        Date birthDate = getBirthDate(scan);
        System.out.println("Enter password: ");
        String password = getPassword(scan);
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Enter employee access level: ");
        int accessLevel = getInteger(scan);
        System.out.println("Enter email: ");
        String email = getEmail(scan);
        dataSource.insertEmployee(nif, name, address, birthDate, password, email, accessLevel);
        logger.info("Employee insertion completed.");
    }

    protected void insertInsurance() {
        logger.info("Starting insertInsurance method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Insurance insertion aborted.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        logger.info("Continuing with insurance insertion.");
        System.out.println("Enter policy number: ");
        int policyNumber = getPolicy(scan);
        logger.info("Policy number entered: " + policyNumber);
        System.out.println("Enter plate number in format XX-XX-XX: ");
        String plateNumberForInsurance = getPlate(scan);
        System.out.println("Enter start date (yyyy-mm-dd):");
        Date startDate = getDate(scan);
        logger.info("Start date entered: " + startDate);
        System.out.println("""
                Enter extra category number for:
                    Comprehensive Insurance -> 1
                    Auto Liability Insurance -> 2
                    Theft Insurance -> 3""");
        /*int extraCategory = getInteger(scan);
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date expiryDate = getDate(scan);
        logger.info("Expiry date entered: " + expiryDate);
        System.out.println("Enter company name:");
        String companyName = getString(scan);
        logger.info("Company name entered: " + companyName);*/

//        dataSource.insertInsurance(policyNumber, plateNumberForInsurance, startDate, extraCategory, expiryDate, companyName);
        logger.info("Insurance insertion completed.");
    }

    protected void insertTicket() {
        logger.info("Starting insertTicket method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Ticket insertion aborted.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        logger.info("Continuing with ticket insertion.");
        System.out.println("Enter NIF: ");
        int nif = getNIF(scan);
        logger.info("NIF entered: " + nif);
        System.out.println("Enter plate number: ");
        String plateNumberForTicket = getPlate(scan);
        System.out.println("Enter date (yyyy-mm-dd):");
        Date ticketDate = getDate(scan);
        logger.info("Ticket date entered: " + ticketDate);
        System.out.println("""
                Enter reason number for:
                Speeding -> 1
                Red Light -> 2
                Illegal Parking -> 3
                Reckless Driving -> 4
                DUI -> 5""");
//        String reason = getInteger(scan);
//        logger.info("Reason number entered: " + reason);
        System.out.println("Enter ticket value: ");
        double ticketValue = getDouble(scan);
        logger.info("Ticket value entered: " + ticketValue);
        System.out.println("Enter expiry date (yyyy-MM-dd):");
        Date ticketExpiryDate = getDate(scan);
        logger.info("Ticket expiry date entered: " + ticketExpiryDate);

        logger.info("Inserting ticket data into the database.");
//        dataSource.insertTicket(nif, plateNumberForTicket, ticketDate, reason, ticketValue, ticketExpiryDate);
        logger.info("Ticket insertion completed.");
    }


    protected void updateVehicleColor() {
        logger.info("Starting updateVehicleColor method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Update color operation aborted.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        logger.info("Continuing with vehicle color update.");
        System.out.println("Enter plate number: ");
        String plateForUpdateColor = getPlate(scan);
        logger.info("Plate number entered for color update: " + plateForUpdateColor);
        System.out.println("Enter new color: ");
        String newColor = getString(scan);
        logger.info("New color entered: " + newColor);
        logger.info("Updating vehicle color in the database.");

        dataSource.updateVehicleColor(newColor, plateForUpdateColor);
        logger.info("Vehicle color update completed.");
    }


    protected void changeVehicleOwner() {
        logger.info("Starting changeVehicleOwner method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Change owner operation aborted.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        logger.info("Continuing with vehicle owner change.");
        System.out.println("Enter plate number in format XX-XX-XX:");
        String plateForChangeOwner = getPlate(scan);
        logger.info("Plate number entered for owner change: " + plateForChangeOwner);
        System.out.println("Enter new owner NIF:");
        int newOwnerNif = getNIF(scan);
        logger.info("New owner NIF entered: " + newOwnerNif);
        logger.info("Changing vehicle owner in the database.");

        dataSource.changeVehicleOwner(plateForChangeOwner, newOwnerNif);
        logger.info("Vehicle owner change completed.");
    }


    protected void renewInsurance() {
        logger.info("Starting renewInsurance method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Insurance renewal operation aborted.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        logger.info("Continuing with insurance renewal.");
        System.out.println("Enter insurance policy number: ");
        int policyForRenew = getPolicy(scan);
        logger.info("Insurance policy number entered for renewal: " + policyForRenew);

        System.out.println("Enter start date (yyyy-mm-dd):");
        Date newStartDate = getDate(scan);
        logger.info("New start date entered for renewal: " + newStartDate);
        System.out.println("Enter expiry date (yyyy-MM-dd):");
        Date newExpiryDate = getDate(scan);
        logger.info("New expiry date entered for renewal: " + newExpiryDate);
        System.out.println("Enter extra category:");
        int newExtraCategory = getInteger(scan);
        logger.info("New extra category entered for renewal: " + newExtraCategory);
        System.out.println("Enter company name:");
        String newCompanyName = getString(scan);
        logger.info("New company name entered for renewal: " + newCompanyName);

        logger.info("Renewing insurance in the database.");

        dataSource.renewInsurance(newStartDate, newExpiryDate, newExtraCategory, newCompanyName, policyForRenew);
        logger.info("Insurance renewal completed.");
    }


    protected void payTicket() {
        logger.info("Starting payTicket method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Ticket payment aborted.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        logger.info("Continuing with ticket payment.");
        System.out.println("Enter plate number in format XX-XX-XX:");
        String plate7 = getPlate(scan);
        logger.info("Plate number entered for ticket payment: " + plate7);
        System.out.println("Enter date (yyyy-MM-dd):");
        Date date7 = getDate(scan);
        logger.info("Payment date entered: " + date7);
        System.out.println("Amount of money: ");
        double d7 = getDouble(scan);
        logger.info("Payment amount entered: " + d7);

        logger.info("Processing ticket payment in the database.");

//        dataSource.payTicket(plate7, date7, d7);
        logger.info("Ticket payment completed.");
    }

    protected void menuUpdateVehicle() {
        logger.info("Starting menuUpdateVehicle method.");
        scan = new Scanner(System.in);
        int choiceUV = -1;
        while (choiceUV != 0) {
            logger.info("Displaying UPDATE VEHICLE MENU.");
            System.out.println("====================UPDATE VEHICLE MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("1 - Update vehicle color");
            System.out.println("2 - Change vehicle owner");
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choiceUV = Integer.parseInt(s);
            } else {
                logger.warn("Invalid option chosen: " + s);
                choiceUV = -1;
            }

            switch (choiceUV) {
                case 1 -> {
                    logger.info("User chose to update vehicle color.");
                    updateVehicleColor();
                }
                case 2 -> {
                    logger.info("User chose to change vehicle owner.");
                    changeVehicleOwner();
                }
                case 0 -> {
                    logger.info("User chose to exit UPDATE VEHICLE MENU.");
                    System.out.println("Back to update menu..." + "\n");
                    return;
                }
                default -> {
                    logger.warn("Invalid option entered. Displaying error message.");
                    System.out.println("Invalid option, please try again");
                }
            }
        }
        logger.info("Exiting menuUpdateVehicle method.");
        scan.close();
    }


    protected void menuUpdateTicket() {
        logger.info("Starting menuUpdateTicket method.");
        scan = new Scanner(System.in);
        int choiceUT = -1;
        while (choiceUT != 0) {
            logger.info("Displaying UPDATE TICKET MENU.");
            System.out.println("====================UPDATE TICKET MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("1 - Pay ticket");
            System.out.println("2 - Update expired ticket");
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choiceUT = Integer.parseInt(s);
            } else {
                logger.warn("Invalid option chosen: " + s);
                choiceUT = -1;
            }
            switch (choiceUT) {
                case 1 -> payTicket();
//              case 2 -> updateExpiredTicket();
                case 0 -> {
                    System.out.println("Back to update menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
        logger.info("Exiting menuUpdateTicket method.");
    }


    protected void menuUpdateInsurance() {
        logger.info("Starting menuUpdateInsurance method.");
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
            if (s.matches("^[0-9]$")) {
                choiceUI = Integer.parseInt(s);
            } else {
                choiceUI = -1;
            }

            switch (choiceUI) {
//                case 2 -> payTicket();
                case 1 -> {
                    logger.info("User chose to renew insurance.");
                    renewInsurance();
                }
                case 0 -> {
                    logger.info("User chose to exit UPDATE INSURANCE MENU.");
                    System.out.println("Back to update menu..." + "\n");
                    return;
                }

                default -> {
                    logger.warn("Invalid option entered. Displaying error message.");
                    System.out.println("Invalid option, please try again");
                }
            }
        }
        logger.info("Exiting menuUpdateInsurance method.");
    }

    protected void updateEmployee() {
        logger.info("Starting menu update employee.");
        scan = new Scanner(System.in);
        int choiceUI = -1;
        List<Employee> employeeList = dataSource.queryEmployees();
        while (choiceUI != 0) {
            System.out.println("====================UPDATE EMPLOYEE MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("""
                    1 - Change employee access level
                    2 - Change employee password
                    3 - Change employee address
                    4 - Change employee email
                    """);
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choiceUI = Integer.parseInt(s);
            } else {
                choiceUI = -1;
            }

            switch (choiceUI) {
                case 1 -> {
                    logger.info("Employee choose to change employee access level.");
                    logger.info("Updating employee access level...");
                    System.out.println("Insert B to go back, anything else to continue");
                    s = scan.nextLine().trim();
                    if (s.compareToIgnoreCase("B") == 0) {
                        System.out.println("Going back to update menu..." + "\n");
                        break;
                    }
                    System.out.println("Enter NIF: ");
                    int nif = getNIF(scan);
                    System.out.println("Enter new access level: ");
                    int accessLevel = getInteger(scan);
                    dataSource.updateEmployeeAccessLevel(nif, accessLevel);
                    logger.info("Employee access level changed.");
                }

                case 2 -> {
                    logger.info("Employee choose to change employee password.");
                    changePassword(employeeList);
                }

                case 3 -> {
                    logger.info("Employee choose to change employee address.");
                    updateAddress(employeeList);
                }

                case 4 -> {
                    logger.info("Employee choose to change employee email.");
                    System.out.println("Insert B to go back, anything else to continue");
                    s = scan.nextLine().trim();
                    if (s.compareToIgnoreCase("B") == 0) {
                        System.out.println("Going back to update menu..." + "\n");
                        break;
                    }
                    System.out.println("Enter NIF: ");
                    int nif = getNIF(scan);
                    System.out.println("Enter new email: ");
                    String email = getEmail(scan);
                    dataSource.updatePersonEmail(nif, email);
                    logger.info("Employee email changed.");
                }
                case 0 -> {
                    logger.info("User chose to exit UPDATE EMPLOYEE MENU.");
                    System.out.println("Back to update menu..." + "\n");
                    return;
                }

                default -> {
                    logger.warn("Invalid option entered. Displaying error message.");
                    System.out.println("Invalid option, please try again");
                }
            }
        }
        logger.info("Exiting menuUpdateInsurance method.");
    }


    protected void menuViewVehicle() {
        logger.info("Starting menuViewVehicle method.");
        scan = new Scanner(System.in);
        int choiceVV = -1;
        List<Vehicle> vehiclesList = dataSource.queryVehicles();
        while (choiceVV != 0) {
            logger.info("Displaying VIEW VEHICLE MENU.");
            System.out.println("====================VIEW VEHICLE MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("""
                    1 - View vehicles organized by plate
                    2 - View vehicles organized by NIF
                    3 - View vehicles organized by registration date
                    4 - View vehicle of a specific NIF
                    5 - View vehicle of a specific plate
                    6 - View vehicle of a specific VIN""");
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choiceVV = Integer.parseInt(s);
            } else {
                logger.warn("Invalid input for option: " + s);
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> {
                    logger.info("User chose to view vehicles organized by plate.");
                    viewVehicleByPlate(vehiclesList);
                }
                case 2 -> {
                    logger.info("User chose to view vehicles organized by NIF.");
                    viewVehicleByNIF(vehiclesList);
                }
                case 3 -> {
                    logger.info("User chose to view vehicles organized by registration date.");
                    viewVehicleByRegistrationDate(vehiclesList);
                }
                case 4 -> {
                    logger.info("User chose to view a specific vehicle by NIF.");
                    viewSpecificVehicleByNIF(vehiclesList);
                }
                case 5 -> {
                    logger.info("User chose to view a specific vehicle by plate.");
                    viewSpecificVehicleByPlate(vehiclesList);
                }
                case 6 -> {
                    logger.info("User chose to view a specific vehicle by VIN.");
                    viewSpecificVehicleByVIN(vehiclesList);
                }
                case 0 -> {
                    logger.info("User chose to exit VIEW VEHICLE MENU.");
                    System.out.println("Back to view menu..." + "\n");
                    return;
                }
                default -> {
                    logger.warn("Invalid option entered. Displaying error message.");
                    System.out.println("Invalid option, please try again");
                }
            }
        }
        logger.info("Exiting menuViewVehicle method.");
    }


    private void viewSpecificVehicleByVIN(List<Vehicle> vehiclesList) {
        logger.info("Starting viewSpecificVehicleByVIN method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificVehicleByVIN method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter VIN: ");
        String vin = getVIN(scan);
        Collections.sort(vehiclesList);
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.getVin() != null && vehicle.getVin().equals(vin)) {
                logger.info("Vehicle found with VIN: " + vin);
                System.out.println(vehicle);
                logger.info("Exiting viewSpecificVehicleByVIN method.");
                return;
            }
        }
        logger.info("No vehicle found with VIN: " + vin);
        System.out.println("No vehicle found with that VIN: " + vin);
        logger.info("Exiting viewSpecificVehicleByVIN method.");
    }

    private void viewSpecificVehicleByNIF(List<Vehicle> vehicleList) {
        logger.info("Starting viewSpecificVehicleByNIF method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificVehicleByNIF method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF(scan);
        Collections.sort(vehicleList);
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getNif() == nif) {
                logger.info("Vehicle found with NIF: " + nif);
                System.out.println(vehicle);
                logger.info("Exiting viewSpecificVehicleByNIF method.");
                return;
            }
        }
        logger.info("No vehicle found with NIF: " + nif);
        System.out.println("No vehicle found with that NIF");
        logger.info("Exiting viewSpecificVehicleByNIF method.");
    }

    private void viewSpecificVehicleByPlate(List<Vehicle> vehicleList) {
        logger.info("Starting viewSpecificVehicleByPlate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificVehicleByPlate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");
        s = getPlate(scan);
        Collections.sort(vehicleList);
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getPlate().equals(s)) {
                logger.info("Vehicle found with plate: " + s);
                System.out.println(vehicle);
                logger.info("Exiting viewSpecificVehicleByPlate method.");
                return;
            }
        }
        logger.info("No vehicle found with plate: " + s);
        System.out.println("No vehicle found with that plate");
        logger.info("Exiting viewSpecificVehicleByPlate method.");
    }


    private void viewVehicleByRegistrationDate(List<Vehicle> vehicleList) {
        logger.info("Starting viewVehicleByRegistrationDate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewVehicleByRegistrationDate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        System.out.println("""
                1 - To see from the most recent date
                2 - To see from the oldest date""");

        int order = getInteger(scan);

        if (order == 1 || order < 0 || order > 2) {
            vehicleList.sort(Comparator.comparing(Vehicle::getregistrationDate));
        } else {
            vehicleList.sort(Comparator.comparing(Vehicle::getregistrationDate).reversed());
        }

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(vehicleList, 10);
        } else {
            displayList(vehicleList, rowsPerPage);
        }
        logger.info("Exiting viewVehicleByRegistrationDate method.");
    }


    private void viewVehicleByNIF(List<Vehicle> vehicleList) {
        logger.info("Starting viewVehicleByNIF method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewVehicleByNIF method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        Collections.sort(vehicleList);
        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(vehicleList, 10);
        } else {
            displayList(vehicleList, rowsPerPage);
        }

        logger.info("Exiting viewVehicleByNIF method.");
    }


    private void viewVehicleByPlate(List<Vehicle> vehicleList) {
        logger.info("Starting viewVehicleByPlate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewVehicleByPlate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);
        vehicleList.sort(Comparator.comparing(Vehicle::getPlate));
        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(vehicleList, 10);
        } else {
            displayList(vehicleList, rowsPerPage);
        }
        logger.info("Exiting viewVehicleByPlate method.");
    }

    protected void menuViewTicket() {
        logger.info("Starting menuViewTicket method.");
        scan = new Scanner(System.in);
        int choiceVV = -1;
        List<Ticket> ticketList = dataSource.queryTickets();
        while (choiceVV != 0) {
            System.out.println("====================VIEW TICKET MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("""
                    1 - View tickets organized by plate
                    2 - View tickets organized by NIF
                    3 - View tickets organized by date
                    4 - View tickets organized by expiration date
                    5 - View ticket of a specific NIF
                    6 - View ticket of a specific plate
                    """);
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choiceVV = Integer.parseInt(s);
            } else {
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> viewTicketByPlate(ticketList);
                case 2 -> viewTicketByNIF(ticketList);
                case 3 -> viewTicketByRegistrationDate(ticketList);
                case 5 -> viewSpecificTicketByNIF(ticketList);
                case 6 -> viewSpecificTicketByPlate(ticketList);
                case 4 -> viewTicketByExpirationDate(ticketList);
                case 0 -> {
                    logger.info("User chose to exit. Exiting menuViewTicket method.");
                    System.out.println("Back to view menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
        logger.info("Exiting menuViewTicket method.");
    }

    private void viewTicketByExpirationDate(List<Ticket> ticketList) {
        logger.info("Starting viewSpecificTicketByPlate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificTicketByPlate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        System.out.println("""
                1 - To sse from the most recent expiration date
                2 - To see from the oldest expiration date""");

        int order = getInteger(scan);

        if (order == 1 || order < 0 || order > 2) {
            ticketList.sort(Comparator.comparing(Ticket::getExpiry_date));
        } else {
            ticketList.sort(Comparator.comparing(Ticket::getExpiry_date).reversed());
        }

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(ticketList, 10);
        } else {
            displayList(ticketList, rowsPerPage);
        }
    }

    private void viewSpecificTicketByPlate(List<Ticket> ticketList) {
        logger.info("Starting viewSpecificTicketByPlate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificTicketByPlate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");
        String plate = getPlate(scan);

        ticketList.sort(Comparator.comparing(Ticket::getPlate));
        for (Ticket t : ticketList) {
            if (t.getPlate().equals(plate)) {
                System.out.println(t);
                logger.info("Ticket found and displayed. Exiting viewSpecificTicketByPlate method.");
                return;
            }
        }
        System.out.println("No ticket found with that plate number");
        logger.info("No ticket found with plate number: " + plate + ". Exiting viewSpecificTicketByPlate method.");
    }


    private void viewSpecificTicketByNIF(List<Ticket> ticketList) {
        logger.info("Starting viewSpecificTicketByNIF method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificTicketByNIF method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF(scan);

        ticketList.sort(Comparator.comparing(Ticket::getNif));
        for (Ticket t : ticketList) {
            if (t.getNif() == nif) {
                System.out.println(t);
                logger.info("Ticket found and displayed. Exiting viewSpecificTicketByNIF method.");
                return;
            }
        }
        System.out.println("No ticket found with that NIF");
        logger.info("No ticket found with NIF: " + nif + ". Exiting viewSpecificTicketByNIF method.");
    }

    private void viewTicketByRegistrationDate(List<Ticket> ticketList) {
        logger.info("Starting viewTicketByRegistrationDate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewTicketByRegistrationDate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        System.out.println("""
                1 - To see from the most recent date
                2 - To see from the oldest date""");

        int order = getInteger(scan);

        if (order == 1 || order < 0 || order > 2) {
            ticketList.sort(Comparator.comparing(Ticket::getDate));
        } else {
            ticketList.sort(Comparator.comparing(Ticket::getDate).reversed());
        }

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(ticketList, 10);
        } else {
            displayList(ticketList, rowsPerPage);
        }
        logger.info("Exiting viewTicketByRegistrationDate method.");
    }

    private void viewTicketByNIF(List<Ticket> ticketList) {
        logger.info("Starting viewTicketByNIF method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewTicketByNIF method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);
        Collections.sort(ticketList);

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(ticketList, 10);
        } else {
            displayList(ticketList, rowsPerPage);
        }
        logger.info("Exiting viewTicketByNIF method.");
    }

    private void viewTicketByPlate(List<Ticket> ticketList) {
        logger.info("Starting viewTicketByPlate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewTicketByPlate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        ticketList.sort(Comparator.comparing(Ticket::getPlate));
        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(ticketList, 10);
        } else {
            displayList(ticketList, rowsPerPage);
        }
        logger.info("Exiting viewTicketByPlate method.");
    }

    protected void menuViewInsurance() {
        logger.info("Starting menuViewInsurance method.");
        scan = new Scanner(System.in);
        int choiceVV = -1;
        List<Insurance> insuranceList = dataSource.queryInsurances();
        while (choiceVV != 0) {
            System.out.println("====================VIEW INSURANCE MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("""
                    1 - View insurances organized by policy
                    2 - View insurances organized by NIF
                    3 - View insurances organized by expiration date
                    4 - View insurances organized by date
                    5 - View insurance of a specific NIF
                    6 - View insurance of a specific plate
                    7 - View insurance of a specific policy
                    """);
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choiceVV = Integer.parseInt(s);
            } else {
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> viewInsuranceByPlate(insuranceList);
                case 2 -> viewInsuranceByNIF(insuranceList);
                case 3 -> viewInsuranceByExpiryDate(insuranceList);
                case 4 -> viewInsuranceByRegistrationDate(insuranceList);
                case 5 -> viewSpecificInsuranceByPlate(insuranceList);
                case 6 -> viewSpecificInsuranceByNIF(insuranceList);
                case 7 -> viewSpecificInsuranceByPolicy(insuranceList);
                case 0 -> {
                    System.out.println("Back to view menu..." + "\n");
                    logger.info("Exiting menuViewInsurance method.");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
        logger.info("Exiting menuViewInsurance method.");
    }

    private void viewSpecificInsuranceByPolicy(List<Insurance> insuranceList) {
        logger.info("Starting viewSpecificInsuranceByPolicy method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificInsuranceByPolicy method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter policy: ");

        int policy = getPolicy(scan);

        Collections.sort(insuranceList);
        for (Insurance t : insuranceList) {
            if (t.getPolicy() == policy) {
                System.out.println(t);
                logger.info("Insurance found and displayed. Exiting viewSpecificInsuranceByPolicy method.");
                return;
            }
        }
        System.out.println("No insurance found with that policy number: " + policy + "\n");
        logger.info("No insurance found with policy number: " + policy + ". Exiting viewSpecificInsuranceByPolicy method.");
    }

    private void viewSpecificInsuranceByNIF(List<Insurance> insuranceList) {
        logger.info("Starting viewSpecificInsuranceByNIF method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificInsuranceByNIF method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");

        int nif = getNIF(scan);

        Collections.sort(insuranceList);
        List<Vehicle> vehicleList = dataSource.queryVehicles();
        Collections.sort(vehicleList);
        for (Vehicle v : vehicleList) {
            if (v.getNif() == nif) {
                for (Insurance t : insuranceList) {
                    if (t.getCarPlate().equals(v.getPlate())) {
                        System.out.println(t);
                        logger.info("Insurance found and displayed. Exiting viewSpecificInsuranceByNIF method.");
                        return;
                    }
                }
            }
        }
        System.out.println("No insurance found with that NIF number: " + nif + "\n");
        logger.info("No insurance found with NIF number: " + nif + ". Exiting viewSpecificInsuranceByNIF method.");
    }

    private void viewSpecificInsuranceByPlate(List<Insurance> insuranceList) {
        logger.info("Starting viewSpecificInsuranceByPlate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificInsuranceByPlate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");

        String plate = getPlate(scan);

        Collections.sort(insuranceList);
        for (Insurance t : insuranceList) {
            if (t.getCarPlate().equals(plate)) {
                System.out.println(t);
                logger.info("Insurance found and displayed. Exiting viewSpecificInsuranceByPlate method.");
                return;
            }
        }
        System.out.println("No insurance found with that plate number");
        logger.info("No insurance found with plate number: " + plate + ". Exiting viewSpecificInsuranceByPlate method.");
    }

    private void viewInsuranceByRegistrationDate(List<Insurance> insuranceList) {
        logger.info("Starting viewInsuranceByRegistrationDate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewInsuranceByRegistrationDate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        System.out.println("""
                1 - To see from the most recent date
                2 - To see from the oldest date""");

        int order = getInteger(scan);

        if (order == 1 || order < 0 || order > 2) {
            insuranceList.sort(Comparator.comparing(Insurance::getStartDate));
        } else {
            insuranceList.sort(Comparator.comparing(Insurance::getStartDate).reversed());
        }

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(insuranceList, 10);
        } else {
            displayList(insuranceList, rowsPerPage);
        }
        logger.info("Exiting viewInsuranceByRegistrationDate method.");
    }

    private void viewInsuranceByExpiryDate(List<Insurance> insuranceList) {
        logger.info("Starting viewInsuranceByExpiryDate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewInsuranceByExpiryDate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        System.out.println("""
                1 - To see from the most recent expiration date
                2 - To see from the oldest expiration date""");

        int order = getInteger(scan);

        if (order == 1 || order < 0 || order > 2) {
            insuranceList.sort(Comparator.comparing(Insurance::getExpDate));
        } else {
            insuranceList.sort(Comparator.comparing(Insurance::getExpDate).reversed());
        }

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(insuranceList, 10);
        } else {
            displayList(insuranceList, rowsPerPage);
        }
        logger.info("Exiting viewInsuranceByExpiryDate method.");
    }


    private void viewInsuranceByNIF(List<Insurance> insuranceList) {
        logger.info("Starting viewInsuranceByNIF method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewInsuranceByNIF method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        Collections.sort(insuranceList);

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(insuranceList, 10);
        } else {
            displayList(insuranceList, rowsPerPage);
        }
        logger.info("Exiting viewInsuranceByNIF method.");
    }

    private void viewInsuranceByPlate(List<Insurance> insuranceList) {
        logger.info("Starting viewInsuranceByPlate method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewInsuranceByPlate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        insuranceList.sort(Comparator.comparing(Insurance::getCarPlate));

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(insuranceList, 10);
        } else {
            displayList(insuranceList, rowsPerPage);
        }
        logger.info("Exiting viewInsuranceByPlate method.");
    }


    protected void menuViewEmployee() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);


        List<Employee> employeeList = dataSource.queryEmployees();
        Collections.sort(employeeList);

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(employeeList, 10);
        } else {
            displayList(employeeList, rowsPerPage);

        }
    }


    protected void menuViewCustomer() {
        logger.info("Starting menuViewCustomer method.");
        scan = new Scanner(System.in);
        int choiceVV = -1;
        List<Customer> customerList = dataSource.queryCustomers();
        while (choiceVV != 0) {
            System.out.println("====================VIEW CUSTOMER MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("""
                    1 - View customers organized by driver license number
                    2 - View customers organized by NIF
                    3 - View customers organized by expiration date
                    4 - View customers organized by registration date
                    5 - View customer of a specific plate
                    6 - View customer of a specific NIF
                    7 - View customer of a specific driver license number
                    8 - View customer of a specific policy number""");
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choiceVV = Integer.parseInt(s);
            } else {
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> {
                    logger.info("User chose to view customers organized by driver license number.");
                    viewCustomerByLicense(customerList);
                }
                case 2 -> {
                    logger.info("User chose to view customers organized by NIF.");
                    viewCustomerByNIF(customerList);
                }
                case 3 -> {
                    logger.info("User chose to view customers organized by expiration date.");
                    viewCustomerByExpiryDate(customerList);
                }
                case 4 -> {
                    logger.info("User chose to view customers organized by registration date.");
                    viewCustomerByRegistrationDate(customerList);
                }
                case 5 -> {
                    logger.info("User chose to view customer of a specific plate.");
                    viewSpecificCustomerByPlate(customerList);
                }
                case 6 -> {
                    logger.info("User chose to view customer of a specific NIF.");
                    viewSpecificCustomerByNIF(customerList);
                }
                case 7 -> {
                    logger.info("User chose to view customer of a specific driver license number.");
                    viewSpecificCustomerByLicense(customerList);
                }
                case 8 -> {
                    logger.info("User chose to view customer of a specific policy number.");
                    viewSpecificCustomerByPolicy(customerList);
                }
                case 0 -> {
                    logger.info("User chose to exit. Going back to view menu...");
                    System.out.println("Back to view menu..." + "\n");
                    return;
                }
                default -> {
                    logger.info("User chose an invalid option. Please try again.");
                    System.out.println("Invalid option, please try again");
                }
            }
        }
        logger.info("Exiting menuViewCustomer method.");
    }


    private void viewSpecificCustomerByPolicy(List<Customer> customerList) {
        logger.info("Starting viewSpecificCustomerByPolicy method.");

        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificCustomerByPolicy method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter policy: ");

        int policy = getPolicy(scan);

        List<Insurance> insuranceList = dataSource.queryInsurances();
        List<Vehicle> vehicleList = dataSource.queryVehicles();

        for (Insurance insurance : insuranceList) {
            if (insurance.getPolicy() == policy) {
                for (Vehicle vehicle : vehicleList) {
                    if (vehicle.getPlate().equals(insurance.getCarPlate())) {
                        for (Customer customer : customerList) {
                            if (customer.getNif() == vehicle.getNif()) {
                                System.out.println(customer);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("No customer found with that policy number: " + policy);
        logger.info("No customer found with policy number: " + policy + ". Exiting viewSpecificCustomerByPolicy method.");
    }

    private void viewSpecificCustomerByLicense(List<Customer> customerList) {
        logger.info("Starting viewSpecificCustomerByLicense method.");

        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificCustomerByLicense method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's license number: ");
        int driverLicense = getDriverLicense(scan);

        for (Customer customer : customerList) {
            if (customer.getDriverLicenseNum() == driverLicense) {
                System.out.println(customer);
                return;
            }
        }
        System.out.println("No customer found with that driver license number: " + driverLicense);
        logger.info("Exiting viewSpecificCustomerByLicense method.");
    }

    private void viewSpecificCustomerByNIF(List<Customer> customerList) {
        logger.info("Starting viewSpecificCustomerByNIF method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificCustomerByNIF method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's NIF: ");

        int nif = getNIF(scan);

        for (Customer customer : customerList) {
            if (customer.getNif() == nif) {
                System.out.println(customer);
            }
        }
        System.out.println("No customer found with that NIF: " + nif);
        logger.info("Exiting viewSpecificCustomerByNIF method.");
    }

    private void viewSpecificCustomerByPlate(List<Customer> customerList) {
        logger.info("Starting viewSpecificCustomerByPlate method.");

        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewSpecificCustomerByPlate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's plate: ");

        String plate = getPlate(scan);

        List<Vehicle> vehicleList = dataSource.queryVehicles();

        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getPlate().equals(plate)) {
                for (Customer customer : customerList) {
                    if (customer.getNif() == vehicle.getNif()) {
                        System.out.println(customer);
                    }
                }
            }
        }

        System.out.println("No customer found with that plate: " + plate);
        logger.info("Exiting viewSpecificCustomerByPlate method.");
    }

    private void viewCustomerByRegistrationDate(List<Customer> customerList) {
        logger.info("Starting viewCustomerByRegistrationDate method.");

        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Exiting viewCustomerByRegistrationDate method.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        System.out.println("""
                1 - To see from the most recent date
                2 - To see from the oldest date""");

        int order = getInteger(scan);

        if (order == 1 || order < 0 || order > 2) {
            customerList.sort(Comparator.comparing(Customer::getStartingDate));
        } else {
            customerList.sort(Comparator.comparing(Customer::getStartingDate).reversed());
        }

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        } else {
            displayList(customerList, rowsPerPage);
        }
        logger.info("Exiting viewCustomerByRegistrationDate method.");
    }


    private void viewCustomerByExpiryDate(List<Customer> customerList) {
        logger.info("Viewing customers by expiry date...");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        System.out.println("""
                1 - To see from the most recent expiration date
                2 - To see from the oldest expiration date""");

        int order = getInteger(scan);

        if (order == 1 || order < 0 || order > 2) {
            customerList.sort(Comparator.comparing(Customer::getExpDate));
        } else {
            customerList.sort(Comparator.comparing(Customer::getExpDate).reversed());
        }

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        } else {

            displayList(customerList, rowsPerPage);
        }
        logger.info("Viewing customers by expiry date completed.");
    }

    private void viewCustomerByNIF(List<Customer> customerList) {
        logger.info("Viewing customers by NIF...");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        // Verificar se não é vazio.
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        customerList.sort(Comparator.comparing(Customer::getNif));

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        } else {
            displayList(customerList, rowsPerPage);

        }
        logger.info("Viewing customers by NIF completed.");
    }

    private void viewCustomerByLicense(List<Customer> customerList) {
        logger.info("Viewing customers by license...");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        customerList.sort(Comparator.comparing(Customer::getDriverLicenseNum));

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        } else {
            displayList(customerList, rowsPerPage);
        }
        logger.info("Viewing customers by license completed.");
    }


    protected void menuUpdateCustomer() {
        logger.info("Starting menuUpdateCustomer method.");
        scan = new Scanner(System.in);
        int choiceVV = -1;
        List<Customer> customerList = dataSource.queryCustomers();
        while (choiceVV != 0) {
            System.out.println("====================UPDATE CUSTOMER MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("""
                    1 - Renew customer's driver license
                    2 - Update customer password
                    3 - Update customer address
                    4 - Change customer email
                    0 - Exit
                    """);
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if (s.matches("^[0-9]$")) {
                choiceVV = Integer.parseInt(s);
            } else {
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> {
                    logger.info("Employee choose to renew customer's driver license.");
                    renewDriverLicense(customerList);
                }
                case 2 -> {
                    logger.info("Employee choose to update customer password");
                    updateAddress(customerList);
                }
                case 3 -> {
                    logger.info("Employee choose to update customer address");
                    changePassword(customerList);
                }
                case 4 -> {
                    logger.info("Employee choose to change customer email");
                    System.out.println("Insert B to go back, anything else to continue");
                    s = scan.nextLine().trim();
                    if (s.compareToIgnoreCase("B") == 0) {
                        System.out.println("Going back to update menu..." + "\n");
                        return;
                    }
                    System.out.println("Enter NIF: ");
                    int nif = getNIF(scan);
                    System.out.println("Enter new email:");
                    String email = getString(scan);
                    dataSource.updatePersonEmail(nif, email);
                    logger.info("Customer email changed.");
                }

                case 0 -> {
                    logger.info("User chose to exit. Going back to view menu...");
                    System.out.println("Back to update menu..." + "\n");
                    return;
                }
                default -> {
                    logger.info("User chose an invalid option. Please try again.");
                    System.out.println("Invalid option, please try again");
                }
            }
        }
        logger.info("Exiting menuViewCustomer method.");
    }

    private void changePassword(List<?> personList) {
        logger.info("Updating customer password...");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF(scan);
        System.out.println("Enter new password:");
        String password = getPassword(scan);
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        dataSource.updatePersonPassword(nif, password);
        logger.info("Customer password changed.");
    }

    private void updateAddress(List<?> personList) {
        logger.info("Updating customer address...");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF(scan);
        System.out.println("Enter new address:");
        String address = getString(scan);
        dataSource.updatePersonAddress(nif, address);
        logger.info("Customer address changed.");
    }

    private void renewDriverLicense(List<?> personList) {
        logger.info("Renewing customer driver's license...");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's license number: ");
        int dricerLicense = getDriverLicense(scan);
        System.out.println("Enter new emission date: ");
        Date date = getDate(scan);
        System.out.println("Enter new expiration date: ");
        Date expdate = getDate(scan);
        dataSource.updateCustomerDriverLicenseDates(date, expdate, dricerLicense);
        logger.info("Customer driver license renewed.");
    }

     void deactivateCustomer() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF(scan);
        getDataSource().deactivateCustomer(nif);
    }

    void deactivateVehicle() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");
        String plate = getPlate(scan);
        getDataSource().deactivateVehicle(plate);
    }

    void deactivateInsurance() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
        System.out.println("Enter policy number: ");
        int policy = getPolicy(scan);
        getDataSource().deactivateInsurance(policy);
    }

    void deactivateTicket() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to delete menu..." + "\n");
            return;
        }
        System.out.println("Enter nif: ");
        int nif = getNIF(scan);
        getDataSource().deactivateTicket(nif);
    }

}