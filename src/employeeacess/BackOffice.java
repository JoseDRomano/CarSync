package employeeacess;

import model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


//Adicionar forma de inserir, ou fazer "update" de multiplos campos de uma só vez
//Fazer o view e o delete
//Se calhar devia-se usar herança para fazer backoffice para cada tipo de employee. Os métodos em comum
//eram herdados e os outros implementados na classe em específico.

public abstract class BackOffice {

    private DataSource dataSource;
    private Scanner scan;
    private Employee employee;

    BackOffice(DataSource dataSource, Employee employee) {
        this.dataSource = dataSource;
        this.employee = employee;
        this.scan = new Scanner(System.in);
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
                new BackOfficeEmployee(dataSource, employee);
                return;
            }

            case 1 -> {
                new BackOfficeEmployeeManager(dataSource, employee);
                return;
            }

            case 2 -> {
                new BackOfficeAdmin(dataSource, employee);
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


    private Date getDate() {
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
                System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
            }
        } while (!validInput);

        return sqlDate;
    }

    private int getNIF() {
        int nif = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{9}")) {
                nif = Integer.parseInt(s);
                validInput = true;
            } else {
                System.out.println("Invalid NIF. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return nif;
    }


    private int getDriverLicense() {
        int driverLicense = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{8}")) {
                driverLicense = Integer.parseInt(s);
                validInput = true;
            } else {
                System.out.println("Invalid driver license number. Please enter a 8-digit number.");
            }
        } while (!validInput);

        return driverLicense;
    }


    private int getPolicy() {
        int policy = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{9}")) {
                policy = Integer.parseInt(s);
                validInput = true;
            } else {
                System.out.println("Invalid policy number. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return policy;
    }

    private String getPlate() {
        String plate = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$")) {
                plate = s;
                validInput = true;
            } else {
                System.out.println("Invalid plate. Please enter a XX-XX-XX format.");
            }
        } while (!validInput);

        return plate;
    }

    private String getString() {
        String str = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^(?=.*[a-zA-Z0-9].*[a-zA-Z0-9])[\\w\\s]{2,}$|^[a-zA-Z0-9]+\\s[a-zA-Z0-9]+$")) {
                str = s;
                validInput = true;
            } else {
                System.out.println("Invalid word. Please make sure there are no special letters");
            }
        } while (!validInput);

        return str;
    }


    private String getVIN() {
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

    private double getDouble() {
        double amount = 0.00;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^\\d+\\.\\d{2}$|^\\d+\\.00$")) {
                amount = Double.parseDouble(s);
                validInput = true;
            } else {
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
    }

    protected void insertEmployee() {
    }

    protected void insertInsurance() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter policy number: ");
        int policyNumber = getPolicy();
        System.out.println("Enter plate number in format XX-XX-XX: ");
        String plateNumberForInsurance = getPlate();
        System.out.println("Enter start date (yyyy-mm-dd):");
        Date startDate = getDate();
        System.out.println("""
                Enter extra category number for:
                Comprehensive Insurance -> 1
                Auto Liability Insurance -> 2
                Theft Insurance -> 3""");
        int extraCategory = getInteger(scan);;
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date expiryDate = getDate();
        System.out.println("Enter company name:" );
        String companyName = getString();
        System.out.println("Enter NIF: ");
        int nif3 = getNIF();
        //Verificar se conteudo esta bem.
        dataSource.insertInsurance(policyNumber, plateNumberForInsurance, startDate, extraCategory, expiryDate, companyName, nif3);
    }

    protected void insertTicket() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        System.out.println("Enter plate number: ");
        String plateNumberForTicket = getPlate();;
        System.out.println("Enter date (yyyy-mm-dd):");
        Date ticketDate = getDate();
        System.out.println("""
                Enter reason number for:
                Speeding -> 1
                Red Light -> 2
                Illegal Parking -> 3
                Reckless Driving -> 4
                DUI -> 5""");
        int reason = getInteger(scan);
        System.out.println("Enter ticket value: ");
        double ticketValue = getDouble();
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date ticketExpiryDate = getDate();

        dataSource.insertTicket(nif, plateNumberForTicket, ticketDate, reason, ticketValue, ticketExpiryDate);
    }


    protected void updateVehicleColor() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate number: ");
        String plateForUpdateColor = getPlate();
        System.out.println("Enter new color: ");
        String newColor = getString();
        System.out.println("Enter NIF: ");
        int nif4 = getNIF();

        //Verificar se conteudo esta bem.
        dataSource.updateVehicleColor(newColor, plateForUpdateColor, nif4);
    }

    protected void changeVehicleOwner() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate number in format XX-XX-XX:");
        String plateForChangeOwner = getPlate();
        System.out.println("Enter new owner NIF:");
        int newOwnerNif = getNIF();
        System.out.println("Enter old owner NIF:");
        int oldOwnerNif = getNIF();
        //Verificar se conteudo esta bem.
        dataSource.changeVehicleOwner(plateForChangeOwner, oldOwnerNif, newOwnerNif);
    }

    protected void renewInsurance() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter insurance policy number: ");
        int policyForRenew = getPolicy();;
        System.out.println("Enter start date (yyyy-mm-dd):");
        Date newStartDate = getDate();
        System.out.println("Enter expiry date (yyyy-mm-dd):");
        Date newExpiryDate = getDate();
        System.out.println("Enter extra category:");
        int newExtraCategory = getInteger(scan);
        System.out.println("Enter company name:");
        String newCompanyName = getString();
        int nif5 = getNIF();
        //Verificar se conteudo esta bem.
        dataSource.renewInsurance(newStartDate, newExpiryDate, newExtraCategory, newCompanyName, policyForRenew, nif5);
    }

    protected void payTicket() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        System.out.println("Enter plate number in format XX-XX-XX:");
        String plate7 = getPlate();
        System.out.println("Enter date (yyyy-mm-dd):");
        Date date7 = getDate();
        System.out.println("Amount of money: ");
        double d7 = getDouble();
        //Verificar se conteudo esta bem.
        dataSource.payTicket(nif, plate7, date7, d7);
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")){
                choiceUV = Integer.parseInt(s);
            }
            else {
                choiceUV = -1;
            }
            switch (choiceUV) {
                case 1 -> updateVehicleColor();
                case 2 -> changeVehicleOwner();
                case 0 -> {
                    System.out.println("Back to update menu..." + "\n");
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choiceUT = Integer.parseInt(s);
            }
            else {
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choiceUI = Integer.parseInt(s);
            }
            else {
                choiceUI = -1;
            }

            switch (choiceUI) {
//                case 2 -> payTicket();
                case 1 -> renewInsurance();
                case 0 -> {
                    System.out.println("Back to update menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    protected void updateEmployee() {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        System.out.println("Enter new access level:");
        int acl = getInteger(scan);
        //Verificar se conteudo esta bem.
//      dataSource.updateEmployee(nif, dln7, date7, expdate7);
    }

    protected void menuViewVehicle() {
        scan = new Scanner(System.in);
        int choiceVV = -1;
        List<Vehicle> vehiclesList = dataSource.queryVehicles();
        while (choiceVV != 0) {
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
            }
            else {
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> viewVehicleByPlate(vehiclesList);
                case 2 -> viewVehicleByNIF(vehiclesList);
                case 3 -> viewVehicleByRegistrationDate(vehiclesList);
                case 4 -> viewSpecificVehicleByNIF(vehiclesList);
                case 5 -> viewSpecificVehicleByPlate(vehiclesList);
                case 6 -> viewSpecificVehicleByVIN(vehiclesList);
                case 0 -> {
                    System.out.println("Back to view menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewSpecificVehicleByVIN(List<Vehicle> vehiclesList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter VIN: ");
        String vin = getVIN();
        Collections.sort(vehiclesList);
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.getVin().equals(vin)) {
                System.out.println(vehicle);
                return;
            }
        }
        System.out.println("No vehicle found with that VIN: " + vin);
    }

    private void viewSpecificVehicleByNIF(List<Vehicle> vehicleList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF();
        Collections.sort(vehicleList);
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getNif() == nif) {
                System.out.println(vehicle);
                return;
            }
        }
        System.out.println("No vehicle found with that NIF");
    }

    private void viewSpecificVehicleByPlate(List<Vehicle> vehicleList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");
        s = getPlate();;
        Collections.sort(vehicleList);
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getPlate().equals(s)) {
                System.out.println(vehicle);
                return;
            }
        }
        System.out.println("No vehicle found with that plate");
    }

    private void viewVehicleByRegistrationDate(List<Vehicle> vehicleList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        System.out.println("""
        1 - To sse from the most recent date
        2 - To see from the oldest date""");

        int order = getInteger(scan);

        if(order == 1 || order < 0 || order > 2) {
            vehicleList.sort(new Vehicle.RegistrationDateComparator());
        }
        else {
            vehicleList.sort(new Vehicle.RegistrationDateComparator().reversed());
        }

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(vehicleList, 10);
        }
        else {
            displayList(vehicleList, rowsPerPage);
        }
    }

    private void viewVehicleByNIF(List<Vehicle> vehicleList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        Collections.sort(vehicleList);
        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(vehicleList, 10);
        }
        else {
            displayList(vehicleList, rowsPerPage);
        }
    }

    private void viewVehicleByPlate(List<Vehicle> vehicleList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);;
        vehicleList.sort(new Vehicle.StringPlateComparator());
        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(vehicleList, 10);
        }
        else {
            displayList(vehicleList, rowsPerPage);
        }
    }

    protected void menuViewTicket() {
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
                    4 - View ticket of a specific NIF
                    5 - View ticket of a specific plate
                    """);
            System.out.println("0 - Exit");
            System.out.print("Option: ");

            String s = scan.nextLine().trim();
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choiceVV = Integer.parseInt(s);
            }
            else {
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> viewTicketByPlate(ticketList);
                case 2 -> viewTicketByNIF(ticketList);
                case 3 -> viewTicketByRegistrationDate(ticketList);
                case 4 -> viewSpecificTicketByNIF(ticketList);
                case 5 -> viewSpecificTicketByPlate(ticketList);
                case 0 -> {
                    System.out.println("Back to view menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewSpecificTicketByPlate(List<Ticket> ticketList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");
        String plate = getPlate();;

        ticketList.sort(new Ticket.StringPlateComparator());
        for(Ticket t: ticketList) {
            if(t.getPlate().equals(plate)) {
                System.out.println(t);
                return;
            }
        }
        System.out.println("No ticket found with that plate number");
    }

    private void viewSpecificTicketByNIF(List<Ticket> ticketList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = getNIF();;

        ticketList.sort(new Ticket.StringPlateComparator());
        for(Ticket t: ticketList) {
            if(t.getNif() == nif) {
                System.out.println(t);
                return;
            }
        }
        System.out.println("No ticket found with that NIF");
    }

    private void viewTicketByRegistrationDate(List<Ticket> ticketList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        System.out.println("""
        1 - To sse from the most recent date
        2 - To see from the oldest date""");

        int order = getInteger(scan);

        if(order == 1 || order < 0 || order > 2) {
            ticketList.sort(new Ticket.RegistrationDateComparator());
        }
        else {
            ticketList.sort(new Ticket.RegistrationDateComparator().reversed());
        }

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(ticketList, 10);
        }
        else {
            displayList(ticketList, rowsPerPage);
        }
    }

    private void viewTicketByNIF(List<Ticket> ticketList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);
        Collections.sort(ticketList);

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(ticketList, 10);
        }
        else {
            displayList(ticketList, rowsPerPage);
        }
    }

    private void viewTicketByPlate(List<Ticket> ticketList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        ticketList.sort(new Ticket.StringPlateComparator());
        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(ticketList, 10);
        }
        else {
            displayList(ticketList, rowsPerPage);
        }
    }

    protected void menuViewInsurance() {
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
            if(!s.isEmpty() || !s.isBlank() || s.matches("[0-9]")) {
                choiceVV = Integer.parseInt(s);
            }
            else {
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
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewSpecificInsuranceByPolicy(List<Insurance> insuranceList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter policy: ");

        int policy = getPolicy();

        Collections.sort(insuranceList);
        for(Insurance t: insuranceList) {
            if(t.getPolicy() == policy) {
                System.out.println(t);
                return;
            }
        }
        System.out.println("No insurance found with that policy number: " + policy + "\n");
    }

    private void viewSpecificInsuranceByNIF(List<Insurance> insuranceList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");

        int nif = getNIF();

        Collections.sort(insuranceList);
        List<Vehicle> vehicleList = dataSource.queryVehicles();
        Collections.sort(vehicleList);
        for(Vehicle v: vehicleList) {
            if (v.getNif() == nif) {
                for(Insurance t: insuranceList) {
                    if(t.getCarPlate().equals(v.getPlate())) {
                        System.out.println(t);
                        return;
                    }
                }
            }
        }
        System.out.println("No insurance found with that NIF number: " + nif + "\n");
    }

    private void viewSpecificInsuranceByPlate(List<Insurance> insuranceList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");

        String plate = getPlate();

        Collections.sort(insuranceList);
        for(Insurance t: insuranceList) {
            if(t.getCarPlate().equals(plate)) {
                System.out.println(t);
                return;
            }
        }
        System.out.println("No insurance found with that plate number");
    }

    private void viewInsuranceByRegistrationDate(List<Insurance> insuranceList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        System.out.println("""
        1 - To sse from the most recent date
        2 - To see from the oldest date""");

        int order = getInteger(scan);

        if(order == 1 || order < 0 || order > 2) {
            insuranceList.sort(new Insurance.RegistrationDateComparator());
        }
        else {
            insuranceList.sort(new Insurance.RegistrationDateComparator().reversed());
        }

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(insuranceList, 10);
        }
        else {
            displayList(insuranceList, rowsPerPage);
        }
    }

    private void viewInsuranceByExpiryDate(List<Insurance> insuranceList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
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
            insuranceList.sort(new Insurance.ExpirationateComparator());
        }
        else {
            insuranceList.sort(new Insurance.ExpirationateComparator().reversed());
        }

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(insuranceList, 10);
        }
        else {
            displayList(insuranceList, rowsPerPage);
        }
    }

    private void viewInsuranceByNIF(List<Insurance> insuranceList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
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
    }

    private void viewInsuranceByPlate(List<Insurance> insuranceList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
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
                case 1 -> viewCustomerByLicense(customerList);
                case 2 -> viewCustomerByNIF(customerList);
                case 3 -> viewCustomerByExpiryDate(customerList);
                case 4 -> viewCustomerByRegistrationDate(customerList);
                case 5 -> viewSpecificCustomerByPlate(customerList);
                case 6 -> viewSpecificCustomerByNIF(customerList);
                case 7 -> viewSpecificCustomerByLicense(customerList);
                case 8 -> viewSpecificCustomerByPolicy(customerList);
                case 0 -> {
                    System.out.println("Back to view menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewSpecificCustomerByPolicy(List<Customer> customerList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter policy: ");

        int policy = getPolicy();

        List<Insurance> insuranceList = dataSource.queryInsurances();
        List<Vehicle> vehicleList = dataSource.queryVehicles();

        for(Insurance insurance : insuranceList) {
            if(insurance.getPolicy() == policy) {
                for(Vehicle vehicle : vehicleList) {
                    if(vehicle.getPlate().equals(insurance.getCarPlate())) {
                        for(Customer customer : customerList) {
                            if(customer.getNif() == vehicle.getNif()) {
                                System.out.println(customer);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("No customer found with that policy number: " + policy);
    }

    private void viewSpecificCustomerByLicense(List<Customer> customerList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's license number: ");
        int driverLicense = getDriverLicense();

        for(Customer customer : customerList) {
            if(customer.getDriverLicenseNum() == driverLicense) {
                System.out.println(customer);
            }
        }
        System.out.println("No customer found with that driver license number: " + driverLicense);
    }

    private void viewSpecificCustomerByNIF(List<Customer> customerList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's NIF: ");

        int nif = getNIF()  ;

        for(Customer customer : customerList) {
            if(customer.getNif() == nif) {
                System.out.println(customer);
            }
        }
        System.out.println("No customer found with that NIF: " + nif);
    }

    private void viewSpecificCustomerByPlate(List<Customer> customerList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's plate: ");

        String plate = getPlate();

        List<Vehicle> vehicleList = dataSource.queryVehicles();

        for(Vehicle vehicle : vehicleList) {
            if(vehicle.getPlate().equals(plate)) {
                for(Customer customer : customerList) {
                    if(customer.getNif() == vehicle.getNif()) {
                        System.out.println(customer);
                    }
                }
            }
        }

        System.out.println("No customer found with that plate : " + plate);
    }

    private void viewCustomerByRegistrationDate(List<Customer> customerList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int rowsPerPage = getInteger(scan);

        System.out.println("""
        1 - To sse from the most recent date
        2 - To see from the oldest date""");

        int order = getInteger(scan);

        if(order == 1 || order < 0 || order > 2) {
//            Collections.sort(customerList, new Customer().ExpirationateComparator());
        }
        else {
//            Collections.sort(insuranceList, new Insurance.RegistrationDateComparator().reversed());
        }

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        }
        else {
            displayList(customerList, rowsPerPage);
        }
    }

    private void viewCustomerByExpiryDate(List<Customer> customerList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
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
//            Collections.sort(customerList, new Insurance.ExpirationateComparator());
        }
        else {
//            Collections.sort(customerList, new Insurance.ExpirationateComparator().reversed());
        }

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        }
        else {
            for (int i = 0; i < rowsPerPage; i++) {
                displayList(customerList, rowsPerPage);
            }
        }
    }

    private void viewCustomerByNIF(List<Customer> customerList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        //Verificar se não é vazio.
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        customerList.sort(Comparator.comparing(Customer::getNif));

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        }
        else {
            for (int i = 0; i < rowsPerPage; i++) {
                displayList(customerList, rowsPerPage);
            }
        }

    }

    private void viewCustomerByLicense(List<Customer> customerList) {
        System.out.println("Insert B to go back, anything else to continue");
        String s = scan.nextLine().trim();
        if (s.compareToIgnoreCase("B") == 0) {
            System.out.println("Going back to update menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");

        int rowsPerPage = getInteger(scan);

        customerList.sort(Comparator.comparing(Customer::getDriverLicenseNum));

        if(rowsPerPage > 20 || rowsPerPage < 0) {
            displayList(customerList, 10);
        }
        else {
            for (int i = 0; i < rowsPerPage; i++) {
                displayList(customerList, rowsPerPage);
            }
        }

    }


    protected void updateCustomer() {
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

        //Verificar informacao
//      dataSource.updateCustomer(nif, dln7, date7, expdate7);
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
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private int getInteger(Scanner scan) {
        int value = 0;
        boolean validInput = false;
        do {
            String s = scan.nextLine().trim();
            if (s.matches("[0-9]+")) {
                value = Integer.parseInt(s);
                validInput = true;
            } else {
                System.out.println("Invalid NIF. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return value;
    }

}
