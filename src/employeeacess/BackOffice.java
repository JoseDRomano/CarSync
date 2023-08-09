package employeeacess;

import model.*;

import java.sql.Date;
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
//              case 2 -> updateExpiredTicket();
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
//      dataSource.updateEmployee(nif7, dln7, date7, expdate7);
    }

    protected void menuViewVehicle() {
        scan = new Scanner(System.in);
        int choiceVV = -1;
        while (choiceVV != 0) {
            System.out.println("====================VIEW VEHICLE MENU====================");
            System.out.println("Please choose an option: ");
            System.out.println("""
                    1 - View vehicles organized by plate
                    2 - View vehicles organized by NIF
                    3 - View vehicles organized by registration date
                    4 - View vehicle of a specific NIF
                    5 - View vehicle of a specific plate
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
                case 1 -> viewVehicleByPlate();
                case 2 -> viewVehicleByNIF();
                case 3 -> viewVehicleByRegistrationDate();
                case 4 -> viewSpecificVehicleByNIF();
                case 5 -> viewSpecificVehicleByPlate();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewSpecificVehicleByNIF() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view vehicle menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());
        List<Vehicle> vehicleList = dataSource.queryVehicles();
        Collections.sort(vehicleList);
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getNif() == nif7) {
                System.out.println(vehicle);
                return;
            }
        }
        System.out.println("No vehicle found with that NIF");
    }

    private void viewSpecificVehicleByPlate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view vehicle menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");
        String s = scan.nextLine().trim();
        List<Vehicle> vehicleList = dataSource.queryVehicles();
        Collections.sort(vehicleList);
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getPlate().equals(s)) {
                System.out.println(vehicle);
                return;
            }
        }
        System.out.println("No vehicle found with that plate");
    }

    private void viewVehicleByRegistrationDate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view vehicle menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());

        System.out.println("""
        1 - To sse from the most recent date
        2 - To see from the oldest date""");

        int nif8 = Integer.parseInt(scan.nextLine().trim());
        List<Vehicle> vehicleList = dataSource.queryVehicles();

        if(nif8 == 1 || nif8 < 0 || nif8 > 2) {
            vehicleList.sort(new Vehicle.RegistrationDateComparator());
        }
        else {
            vehicleList.sort(new Vehicle.RegistrationDateComparator().reversed());
        }

        if(nif7 > 20 || nif7 < 0) {
            displayList(vehicleList, 10);
        }
        else {
            displayList(vehicleList, nif7);
        }
    }

    private void viewVehicleByNIF() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view vehicle menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());
        List<Vehicle> vehicleList = dataSource.queryVehicles();
        Collections.sort(vehicleList);
        if(nif7 > 20 || nif7 < 0) {
           displayList(vehicleList, 10);
        }
        else {
            displayList(vehicleList, nif7);
        }
    }

    private void viewVehicleByPlate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view vehicle menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());
        List<Vehicle> vehicleList = dataSource.queryVehicles();
        vehicleList.sort(new Vehicle.StringPlateComparator());
        if(nif7 > 20 || nif7 < 0) {
           displayList(vehicleList, 10);
        }
        else {
            displayList(vehicleList, nif7);
        }
    }

    protected void menuViewTicket() {
        scan = new Scanner(System.in);
        int choiceVV = -1;
        while (choiceVV != 0) {
            System.out.println("====================VIEW VEHICLE MENU====================");
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
            if(!s.isEmpty() || !s.isBlank()) {
                choiceVV = Integer.parseInt(s);
            }
            else {
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> viewTicketByPlate();
                case 2 -> viewTicketByNIF();
                case 3 -> viewTicketByRegistrationDate();
                case 4 -> viewSpecificTicketByNIF();
                case 5 -> viewSpecificTicketByPlate();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewSpecificTicketByPlate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter plate: ");
        String plate = scan.nextLine().trim();

        List<Ticket> ticketList = dataSource.queryTickets();
        ticketList.sort(new Ticket.StringPlateComparator());
        for(Ticket t: ticketList) {
            if(t.getPlate().equals(plate)) {
                System.out.println(t);
                return;
            }
        }
        System.out.println("No ticket found with that plate number");
    }

    private void viewSpecificTicketByNIF() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = Integer.parseInt(scan.nextLine().trim());

        List<Ticket> ticketList = dataSource.queryTickets();
        ticketList.sort(new Ticket.StringPlateComparator());
        for(Ticket t: ticketList) {
            if(t.getNif() == nif) {
                System.out.println(t);
                return;
            }
        }
        System.out.println("No ticket found with that NIF");
    }

    private void viewTicketByRegistrationDate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());

        System.out.println("""
        1 - To sse from the most recent date
        2 - To see from the oldest date""");

        int nif8 = Integer.parseInt(scan.nextLine().trim());
        List<Ticket> ticketList = dataSource.queryTickets();

        if(nif8 == 1 || nif8 < 0 || nif8 > 2) {
            ticketList.sort(new Ticket.RegistrationDateComparator());
        }
        else {
            ticketList.sort(new Ticket.RegistrationDateComparator().reversed());
        }

        if(nif7 > 20 || nif7 < 0) {
            displayList(ticketList, 10);
        }
        else {
            displayList(ticketList, nif7);
        }
    }

    private void viewTicketByNIF() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());
        List<Ticket> ticketList = dataSource.queryTickets();
        Collections.sort(ticketList);

        if(nif7 > 20 || nif7 < 0) {
            displayList(ticketList, 10);
        }
        else {
            displayList(ticketList, nif7);
        }
    }

    private void viewTicketByPlate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());
        List<Ticket> ticketList = dataSource.queryTickets();
        ticketList.sort(new Ticket.StringPlateComparator());
        if(nif7 > 20 || nif7 < 0) {
           displayList(ticketList, 10);
        }
        else {
            displayList(ticketList, nif7);
        }
    }

    protected void menuViewInsurance() {
        scan = new Scanner(System.in);
        int choiceVV = -1;
        while (choiceVV != 0) {
            System.out.println("====================VIEW VEHICLE MENU====================");
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
            if(!s.isEmpty() || !s.isBlank()) {
                choiceVV = Integer.parseInt(s);
            }
            else {
                choiceVV = -1;
            }

            switch (choiceVV) {
                case 1 -> viewInsuranceByPlate();
                case 2 -> viewInsuranceByNIF();
                case 3 -> viewInsuranceByExpiryDate();
                case 4 -> viewInsuranceByRegistrationDate();
                case 5 -> viewSpecificInsuranceByPlate();
                case 6 -> viewSpecificInsuranceByNIF();
                case 7 -> viewSpecificInsuranceByPolicy();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewSpecificInsuranceByPolicy() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter policy: ");
        int nif = Integer.parseInt(scan.nextLine().trim());

        List<Insurance> insuranceList = dataSource.queryInsurances();
        Collections.sort(insuranceList);
        for(Insurance t: insuranceList) {
            if(t.getPolicy() == nif) {
                System.out.println(t);
                return;
            }
        }
        System.out.println("No insurance found with that policy number");
    }

    private void viewSpecificInsuranceByNIF() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter NIF: ");
        int nif = Integer.parseInt(scan.nextLine().trim());

        List<Insurance> insuranceList = dataSource.queryInsurances();
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
        System.out.println("No insurance found with that policy number");
    }

    private void viewSpecificInsuranceByPlate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter policy: ");
        String plate = scan.nextLine().trim();

        List<Insurance> insuranceList = dataSource.queryInsurances();
        Collections.sort(insuranceList);
        for(Insurance t: insuranceList) {
            if(t.getCarPlate().equals(plate)) {
                System.out.println(t);
                return;
            }
        }
        System.out.println("No insurance found with that policy number");
    }

    private void viewInsuranceByRegistrationDate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());

        System.out.println("""
        1 - To sse from the most recent date
        2 - To see from the oldest date""");

        int nif8 = Integer.parseInt(scan.nextLine().trim());
        List<Insurance> insuranceList = dataSource.queryInsurances();

        if(nif8 == 1 || nif8 < 0 || nif8 > 2) {
            insuranceList.sort(new Insurance.ExpirationateComparator());
        }
        else {
            insuranceList.sort(new Insurance.RegistrationDateComparator().reversed());
        }

        if(nif7 > 20 || nif7 < 0) {
            displayList(insuranceList, 10);
        }
        else {
            displayList(insuranceList, nif7);
        }
    }

    private void viewInsuranceByExpiryDate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());

        System.out.println("""
        1 - To sse from the most recent expiration date
        2 - To see from the oldest expiration date""");

        int nif8 = Integer.parseInt(scan.nextLine().trim());
        List<Insurance> insuranceList = dataSource.queryInsurances();

        if(nif8 == 1 || nif8 < 0 || nif8 > 2) {
            insuranceList.sort(new Insurance.ExpirationateComparator());
        }
        else {
            insuranceList.sort(new Insurance.ExpirationateComparator().reversed());
        }

        if(nif7 > 20 || nif7 < 0) {
            displayList(insuranceList, 10);
        }
        else {
            for (int i = 0; i < nif7; i++) {
                displayList(insuranceList, nif7);
            }
        }
    }

    private void viewInsuranceByNIF() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());


        List<Insurance> insuranceList = dataSource.queryInsurances();
        Collections.sort(insuranceList);

        if (nif7 > 20 || nif7 < 0) {
            displayList(insuranceList, 10);
        } else {
            displayList(insuranceList, nif7);

        }
    }

    private void viewInsuranceByPlate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());


        List<Insurance> insuranceList = dataSource.queryInsurances();
        insuranceList.sort(new Insurance.StringPlateComparator());

        if (nif7 > 20 || nif7 < 0) {
            displayList(insuranceList, 10);
        } else {
            displayList(insuranceList, nif7);

        }
    }

    protected void menuViewEmployee() {
       /* System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());


        List<Employee> employeeList = dataSource.queryEmployee();
        Collections.sort(employeeList);

        if (nif7 > 20 || nif7 < 0) {
            displayList(employeeList, 10);
        } else {
            displayList(employeeList, nif7);

        }*/
    }
    protected void menuViewCustomer() {
        scan = new Scanner(System.in);
        int choiceVV = -1;
        while (choiceVV != 0) {
            System.out.println("====================VIEW VEHICLE MENU====================");
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
                case 1 -> viewCustomerByLicense();
                case 2 -> viewCustomerByNIF();
                case 3 -> viewCustomerByExpiryDate();
                case 4 -> viewCustomerByRegistrationDate();
                case 5 -> viewSpecificCustomerByPlate();
                case 6 -> viewSpecificCustomerByNIF();
                case 7 -> viewSpecificCustomerByLicense();
                case 8 -> viewSpecificCustomerByPolicy();
                case 0 -> {
                    System.out.println("Back to main menu..." + "\n");
                    return;
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewSpecificCustomerByPolicy() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter policy: ");
        int policy =Integer.parseInt(scan.nextLine().trim());

        List<Insurance> insuranceList = dataSource.queryInsurances();
        List<Vehicle> vehicleList = dataSource.queryVehicles();
        List<Customer> customerList = dataSource.queryCustomers();

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

    private void viewSpecificCustomerByLicense() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's license number: ");
        int policy =Integer.parseInt(scan.nextLine().trim());

        List<Customer> customerList = dataSource.queryCustomers();

        for(Customer customer : customerList) {
            if(customer.getDriverLicenseNum() == policy) {
                System.out.println(customer);
            }
        }
        System.out.println("No customer found with that driver license number: " + policy);
    }

    private void viewSpecificCustomerByNIF() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's NIF: ");
        int policy =Integer.parseInt(scan.nextLine().trim());

        List<Customer> customerList = dataSource.queryCustomers();

        for(Customer customer : customerList) {
            if(customer.getNif() == policy) {
                System.out.println(customer);
            }
        }
        System.out.println("No customer found with that NIF: " + policy);
    }

    private void viewSpecificCustomerByPlate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter driver's NIF: ");
        String policy = scan.nextLine().trim();

        List<Vehicle> vehicleList = dataSource.queryVehicles();
        List<Customer> customerList = dataSource.queryCustomers();

        for(Vehicle vehicle : vehicleList) {
            if(vehicle.getPlate().equals(policy)) {
                for(Customer customer : customerList) {
                    if(customer.getNif() == vehicle.getNif()) {
                        System.out.println(customer);
                    }
                }
            }
        }

        System.out.println("No customer found with that plate : " + policy);
    }

    private void viewCustomerByRegistrationDate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());

        System.out.println("""
        1 - To sse from the most recent date
        2 - To see from the oldest date""");

        int nif8 = Integer.parseInt(scan.nextLine().trim());
        List<Customer> customerList = dataSource.queryCustomers();

        if(nif8 == 1 || nif8 < 0 || nif8 > 2) {
//            Collections.sort(customerList, new Customer().ExpirationateComparator());
        }
        else {
//            Collections.sort(insuranceList, new Insurance.RegistrationDateComparator().reversed());
        }

        if(nif7 > 20 || nif7 < 0) {
            displayList(customerList, 10);
        }
        else {
            displayList(customerList, nif7);
        }
    }

    private void viewCustomerByExpiryDate() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());

        System.out.println("""
        1 - To sse from the most recent expiration date
        2 - To see from the oldest expiration date""");

        int nif8 = Integer.parseInt(scan.nextLine().trim());
        List<Customer> customerList = dataSource.queryCustomers();

        if(nif8 == 1 || nif8 < 0 || nif8 > 2) {
//            Collections.sort(customerList, new Insurance.ExpirationateComparator());
        }
        else {
//            Collections.sort(customerList, new Insurance.ExpirationateComparator().reversed());
        }

        if(nif7 > 20 || nif7 < 0) {
            displayList(customerList, 10);
        }
        else {
            for (int i = 0; i < nif7; i++) {
                displayList(customerList, nif7);
            }
        }
    }

    private void viewCustomerByNIF() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());

        List<Customer> customerList = dataSource.queryCustomers();
        customerList.sort(Comparator.comparing(Customer::getNif));

        if(nif7 > 20 || nif7 < 0) {
            displayList(customerList, 10);
        }
        else {
            for (int i = 0; i < nif7; i++) {
                displayList(customerList, nif7);
            }
        }

    }

    private void viewCustomerByLicense() {
        System.out.println("Insert 0 to go back or 1 to continue");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice == 0) {
            System.out.println("Going back to view ticket menu..." + "\n");
            return;
        }
        System.out.println("Enter number of rows per page, max is 20: ");
        int nif7 = Integer.parseInt(scan.nextLine().trim());

        List<Customer> customerList = dataSource.queryCustomers();
        customerList.sort(Comparator.comparing(Customer::getDriverLicenseNum));

        if(nif7 > 20 || nif7 < 0) {
            displayList(customerList, 10);
        }
        else {
            for (int i = 0; i < nif7; i++) {
                displayList(customerList, nif7);
            }
        }

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
