package employeeacess;

import model.*;
import org.apache.log4j.Logger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public abstract class BackOffice {

    private static final Logger logger = Logger.getLogger(BackOffice.class);
    private DataSource dataSource;
    private Scanner scan;
    private Employee employee;

    BackOffice(DataSource dataSource, Employee employee) {
        this.dataSource = dataSource;
        this.employee = employee;
        this.scan = new Scanner(System.in);

        logger.info("BackOffice initialized for employee: " + employee.getNif());
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
                logger.info("Starting BackOffice for Employee level 0");
                new BackOfficeEmployee(dataSource, employee);
                return;
            }

            case 1 -> {
                logger.info("Starting BackOffice for Employee Manager");
                new BackOfficeEmployeeManager(dataSource, employee);
                return;
            }

            case 2 -> {
                logger.info("Starting BackOffice for Admin");
                new BackOfficeAdmin(dataSource, employee);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid access level");
    }

    public static void startBackOffice(DataSource dataSource, Employee e1) {
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


    Date getDate() {
        Date sqlDate = null;
        boolean validInput = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        do {
            String input = scan.nextLine().trim();
            try {
                LocalDate localDate = LocalDate.parse(input, formatter);
                sqlDate = Date.valueOf(localDate);
                validInput = true;
            } catch (DateTimeParseException e) {
                logger.error("Invalid date input: " + input, e);
                System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
            }
        } while (!validInput);

        return sqlDate;
    }

    int getNIF() {
        int nif = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{9}")) {
                nif = Integer.parseInt(s);
                validInput = true;
            } else {
                logger.error("Invalid NIF: " + s);
                System.out.println("Invalid NIF. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return nif;
    }


    int getDriverLicense() {
        int driverLicense = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{8}")) {
                driverLicense = Integer.parseInt(s);
                validInput = true;
            } else {
                logger.error("Invalid driver license number: " + s);
                System.out.println("Invalid driver license number. Please enter a 8-digit number.");
            }
        } while (!validInput);

        return driverLicense;
    }


    int getPolicy() {
        int policy = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{9}")) {
                policy = Integer.parseInt(s);
                validInput = true;
            } else {
                logger.error("Invalid policy number: " + s);
                System.out.println("Invalid policy number. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return policy;
    }

    String getPlate() {
        String plate = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$")) {
                plate = s;
                validInput = true;
            } else {
                logger.error("Invalid plate: " + s);
                System.out.println("Invalid plate. Please enter a XX-XX-XX format.");
            }
        } while (!validInput);

        return plate;
    }

    String getString() {
        String str = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^(?=.*[a-zA-Z0-9].*[a-zA-Z0-9])[\\w\\s]{2,}$|^[a-zA-Z0-9]+\\s[a-zA-Z0-9]+$")) {
                str = s;
                validInput = true;
            } else {
                logger.error("Invalid word: " + s);
                System.out.println("Invalid word. Please make sure there are no special letters");
            }
        } while (!validInput);

        return str;
    }


    String getVIN() {
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

    double getDouble() {
        double amount = 0.00;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^\\d+\\.\\d{2}$|^\\d+\\.00$")) {
                amount = Double.parseDouble(s);
                validInput = true;
            } else {
                logger.error("Invalid amount: " + s);
                System.out.println("Invalid amount. Please enter a value that ends with a decimal part or .00 .");
            }
        } while (!validInput);

        return amount;
    }

    protected void insertVehicle() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate number in format XX-XX-XX: ");
        String plateNumber = getPlate();
        System.out.println("Enter VIN: ");
        String vin = getVIN();
        System.out.println("Enter color: ");
        String color = getString();
        System.out.println("Enter brand: ");
        String brand = getString();
        System.out.println("Enter model: ");
        String model = getString();
        System.out.println("Enter registration date (yyyy-mm-day):");
        Date registrationDate = getDate();
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
        int nif2 = getNIF();

        dataSource.insertVehicle(plateNumber, vin, color, brand, model, registrationDate, categoryNumber, nif2);
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
        int nif = getNIF();
        System.out.println("Enter name: ");
        String name = getString();
        System.out.println("Enter address: ");
        String address = getString();
        System.out.println("Enter birth date with format yyyy-mm-dd: ");
        Date birthDate = getDate();
        System.out.println("Enter password: ");
        String password = getString();
        System.out.println("Enter driverLicense: ");
        int driverLicense = getDriverLicense();
        logger.info("Employee insertion completed.");
    }

    protected void insertEmployee() {
        logger.info("Starting insertEmployee method.");
        // ... (method implementation)
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
        int policyNumber = getPolicy();
        logger.info("Policy number entered: " + policyNumber);
        System.out.println("Enter plate number in format XX-XX-XX: ");
        String plateNumberForInsurance = getPlate();
        System.out.println("Enter start date (yyyy-mm-dd):");
        Date startDate = getDate();
        logger.info("Start date entered: " + startDate);
        System.out.println("""
                Enter extra category number for:
                Comprehensive Insurance -> 1
                Auto Liability Insurance -> 2
                Theft Insurance -> 3""");
        int extraCategory = getInteger(scan);
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date expiryDate = getDate();
        logger.info("Expiry date entered: " + expiryDate);
        System.out.println("Enter company name:" );
        String companyName = getString();
        logger.info("Company name entered: " + companyName);
        System.out.println("Enter NIF: ");
        int nif3 = getNIF();

        dataSource.insertInsurance(policyNumber, plateNumberForInsurance, startDate, extraCategory, expiryDate, companyName, nif3);
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
        int nif = getNIF();
        logger.info("NIF entered: " + nif);
        System.out.println("Enter plate number: ");
        String plateNumberForTicket = getPlate();
        System.out.println("Enter date (yyyy-mm-dd):");
        Date ticketDate = getDate();
        logger.info("Ticket date entered: " + ticketDate);
        System.out.println("""
                Enter reason number for:
                Speeding -> 1
                Red Light -> 2
                Illegal Parking -> 3
                Reckless Driving -> 4
                DUI -> 5""");
        int reason = getInteger(scan);
        logger.info("Reason number entered: " + reason);
        System.out.println("Enter ticket value: ");
        double ticketValue = getDouble();
        logger.info("Ticket value entered: " + ticketValue);
        System.out.println("Enter expiry date (yyyy-MM-dd):");
        Date ticketExpiryDate = getDate();
        logger.info("Ticket expiry date entered: " + ticketExpiryDate);

        logger.info("Inserting ticket data into the database.");
        dataSource.insertTicket(nif, plateNumberForTicket, ticketDate, reason, ticketValue, ticketExpiryDate);
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
        String plateForUpdateColor = getPlate();
        logger.info("Plate number entered for color update: " + plateForUpdateColor);
        System.out.println("Enter new color: ");
        String newColor = getString();
        logger.info("New color entered: " + newColor);
        System.out.println("Enter NIF: ");
        int nif4 = getNIF();
        logger.info("NIF entered: " + nif4);

        logger.info("Updating vehicle color in the database.");
        dataSource.updateVehicleColor(newColor, plateForUpdateColor, nif4);
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
        String plateForChangeOwner = getPlate();
        logger.info("Plate number entered for owner change: " + plateForChangeOwner);
        System.out.println("Enter new owner NIF:");
        int newOwnerNif = getNIF();
        logger.info("New owner NIF entered: " + newOwnerNif);
        System.out.println("Enter old owner NIF:");
        int oldOwnerNif = getNIF();
        logger.info("Old owner NIF entered: " + oldOwnerNif);

        logger.info("Changing vehicle owner in the database.");
        dataSource.changeVehicleOwner(plateForChangeOwner, oldOwnerNif, newOwnerNif);
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
        int policyForRenew = getPolicy();
        logger.info("Insurance policy number entered for renewal: " + policyForRenew);

        System.out.println("Enter start date (yyyy-mm-dd):");
        Date newStartDate = getDate();
        logger.info("New start date entered for renewal: " + newStartDate);
        System.out.println("Enter expiry date (yyyy-MM-dd):");
        Date newExpiryDate = getDate();
        logger.info("New expiry date entered for renewal: " + newExpiryDate);
        System.out.println("Enter extra category:");
        int newExtraCategory = getInteger(scan);
        logger.info("New extra category entered for renewal: " + newExtraCategory);
        System.out.println("Enter company name:");
        String newCompanyName = getString();
        logger.info("New company name entered for renewal: " + newCompanyName);
        int nif5 = getNIF();
        logger.info("NIF entered for renewal: " + nif5);

        logger.info("Renewing insurance in the database.");
        //Verificar se conteudo esta bem.
        dataSource.renewInsurance(newStartDate, newExpiryDate, newExtraCategory, newCompanyName, policyForRenew, nif5);
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
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        logger.info("NIF entered: " + nif);
        System.out.println("Enter plate number in format XX-XX-XX:");
        String plate7 = getPlate();
        logger.info("Plate number entered for ticket payment: " + plate7);
        System.out.println("Enter date (yyyy-MM-dd):");
        Date date7 = getDate();
        logger.info("Payment date entered: " + date7);
        System.out.println("Amount of money: ");
        double d7 = getDouble();
        logger.info("Payment amount entered: " + d7);

        logger.info("Processing ticket payment in the database.");
        //Verificar se conteudo esta bem.
        dataSource.payTicket(nif, plate7, date7, d7);
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")){
                choiceUV = Integer.parseInt(s);
            }
            else {
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choiceUT = Integer.parseInt(s);
            }
            else {
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choiceUI = Integer.parseInt(s);
            }
            else {
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
        logger.info("Starting updateEmployee method.");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            logger.info("User chose to go back. Employee update aborted.");
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        logger.info("Continuing with employee update.");
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        logger.info("NIF entered: " + nif);
        System.out.println("Enter new access level:");
        int acl = getInteger(scan);
        logger.info("New access level entered: " + acl);
        //Verificar se conteudo esta bem.
        // dataSource.updateEmployee(nif, dln7, date7, expdate7);

        // Uncomment the above line and add log statements if you implement the dataSource.updateEmployee method.

        logger.info("Employee update completed.");
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
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
        String vin = getVIN();
        Collections.sort(vehiclesList);
        for (Vehicle vehicle : vehiclesList) {
            if ( vehicle.getVin()!= null && vehicle.getVin().equals(vin)) {
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
        int nif = getNIF();
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
        s = getPlate();
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
            vehicleList.sort(new Vehicle.RegistrationDateComparator());
        } else {
            vehicleList.sort(new Vehicle.RegistrationDateComparator().reversed());
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
        vehicleList.sort(new Vehicle.StringPlateComparator());
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
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

        if(order == 1 || order < 0 || order > 2) {
            ticketList.sort(new Ticket.ExpirationateComparator());
        }
        else {
            ticketList.sort(new Ticket.ExpirationateComparator().reversed());
        }

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(ticketList, 10);
        }
        else {
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
        String plate = getPlate();

        ticketList.sort(new Ticket.StringPlateComparator());
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
        int nif = getNIF();

        ticketList.sort(new Ticket.StringPlateComparator());
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
            ticketList.sort(new Ticket.RegistrationDateComparator());
        } else {
            ticketList.sort(new Ticket.RegistrationDateComparator().reversed());
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

        ticketList.sort(new Ticket.StringPlateComparator());
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
            if (!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
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

        int policy = getPolicy();

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

        int nif = getNIF();

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

        String plate = getPlate();

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
            insuranceList.sort(new Insurance.RegistrationDateComparator());
        } else {
            insuranceList.sort(new Insurance.RegistrationDateComparator().reversed());
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

        if(order == 1 || order < 0 || order > 2) {
            insuranceList.sort(new Insurance.ExpirationateComparator());
        }
        else {
            insuranceList.sort(new Insurance.ExpirationateComparator().reversed());
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

        insuranceList.sort(new Insurance.StringPlateComparator());

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(insuranceList, 10);
        } else {
            displayList(insuranceList, rowsPerPage);
        }
        logger.info("Exiting viewInsuranceByPlate method.");
    }


    protected void menuViewEmployee() {
        /*System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);


        List<Employee> employeeList = dataSource.queryEmployee();
        Collections.sort(employeeList);

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(employeeList, 10);
        } else {
            displayList(employeeList, rowsPerPage);

        }*/
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
                8 - View customer of a specific policy number
                """);
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank()) {
                choiceVV = Integer.parseInt(s);
            }
            else {
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

        int policy = getPolicy();

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
        int driverLicense = getDriverLicense();

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

        int nif = getNIF();

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

        String plate = getPlate();

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
            // Collections.sort(customerList, new Customer.ExpirationateComparator());
        } else {
            // Collections.sort(insuranceList, new Insurance.RegistrationDateComparator().reversed());
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
            // Collections.sort(customerList, new Insurance.ExpirationateComparator());
        } else {
            // Collections.sort(customerList, new Insurance.ExpirationateComparator().reversed());
        }

        if (rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        } else {
            for (int i = 0; i < rowsPerPage; i++) {
                displayList(customerList, rowsPerPage);
            }
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
            for (int i = 0; i < rowsPerPage; i++) {
                displayList(customerList, rowsPerPage);
            }
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
            for (int i = 0; i < rowsPerPage; i++) {
                displayList(customerList, rowsPerPage);
            }
        }
        logger.info("Viewing customers by license completed.");
    }

    protected void updateCustomer() {
        logger.info("Updating customer information...");
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        System.out.println("Enter driver license number:");
        int dln7 = getDriverLicense();
        System.out.println("Enter date (yyyy-mm-dd):");
        Date date7 = getDate();
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date expdate7 = getDate();

        // Verificar informacao
        // dataSource.updateCustomer(nif, dln7, date7, expdate7);
        logger.info("Customer information updated.");
    }

    public static void displayList(List<?> objects, int rowsPerPage) {
        logger.info("Displaying list...");
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

            System.out.println("Displaying from page " + aux + " of " + pages + ":");
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
                default -> {
                    System.out.println("Invalid input. Please try again.");
                    logger.warn("Invalid input received.");
                }
            }
        }
        logger.info("Displaying list completed.");
    }


    private int getInteger(Scanner scan) {
        logger.info("Getting integer input...");
        int value = 0;
        boolean validInput = false;
        do {
            String s = scan.nextLine().trim();
            if (s.matches("[0-9]+")) {
                value = Integer.parseInt(s);
                validInput = true;
            } else {
                System.out.println("Invalid value. Please enter a number.");
                logger.warn("Invalid value entered. Please enter a number.");
            }
        } while (!validInput);

        logger.info("Integer input obtained: " + value);
        return value;
    }
}