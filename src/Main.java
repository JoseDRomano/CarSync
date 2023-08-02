import employeeacess.DataSource;
import model.Insurance;
import model.Ticket;
import model.Vehicle;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if (dataSource.open()) {
            System.out.println("Connected to the database.");
            showMenu(dataSource);
            dataSource.close();
        } else {
            System.out.println("Could not connect to the database.");
        }
    }

    private static void showMenu(DataSource dataSource) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("===== MENU =====");
            System.out.println("1. List all vehicles");
            System.out.println("2. List all insurances");
            System.out.println("3. List all tickets");
            System.out.println("4. Find a vehicle by plate number");
            System.out.println("5. Insert a new vehicle");
            System.out.println("6. Insert a new insurance");
            System.out.println("7. Insert a new ticket");
            System.out.println("8. Update vehicle color");
            System.out.println("9. Change vehicle owner");
            System.out.println("10. Renew insurance");
            System.out.println("11. Delete vehicle");
            System.out.println("12. Delete insurance");
            System.out.println("0. Exit");
            System.out.println("================");
            System.out.println("Please enter your choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Vehicle> vehicles = dataSource.queryVehicles();
                    for (Vehicle vehicle : vehicles) {
                        System.out.println(vehicle);
                    }
                    break;

                case 2:
                    List<Insurance> insurances = dataSource.queryInsurances();
                    for (Insurance insurance : insurances) {
                        System.out.println(insurance);
                    }
                    break;

                case 3:
                    List<Ticket> tickets = dataSource.queryTickets();
                    for (Ticket ticket : tickets) {
                        System.out.println(ticket);
                    }
                    break;

                case 4:
                    System.out.println("Enter the plate number:");
                    String plate = scanner.nextLine();
                    Vehicle vehicle = dataSource.getVehicle(plate);
                    if (vehicle != null) {
                        System.out.println(vehicle);
                    }
                    break;

                case 5:
                    System.out.println("Enter plate number:");
                    String plateNumber = scanner.nextLine();
                    System.out.println("Enter VIN:");
                    String vin = scanner.nextLine();
                    System.out.println("Enter color:");
                    String color = scanner.nextLine();
                    System.out.println("Enter brand:");
                    String brand = scanner.nextLine();
                    System.out.println("Enter model:");
                    String model = scanner.nextLine();
                    System.out.println("Enter registration date (dd-mm-yyyy):");
                    Date registrationDate = Date.valueOf(scanner.nextLine());
                    System.out.println("Enter category number:");
                    int categoryNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter NIF:");
                    int nif = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    dataSource.insertVehicle(plateNumber, vin, color, brand, model, registrationDate, categoryNumber, nif);
                    break;

                case 6:
                    System.out.println("Enter policy number:");
                    int policyNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter plate number:");
                    String plateNumberForInsurance = scanner.nextLine();
                    System.out.println("Enter start date (dd-mm-yyyy):");
                    Date startDate = Date.valueOf(scanner.nextLine());
                    System.out.println("Enter extra category:");
                    int extraCategory = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter expiry date (dd-mm-yyyy):");
                    Date expiryDate = Date.valueOf(scanner.nextLine());
                    System.out.println("Enter company name:");
                    String companyName = scanner.nextLine();
                    //dataSource.insertInsurance(policyNumber, plateNumberForInsurance, startDate, extraCategory, expiryDate, companyName);
                    break;

                case 7:
                    System.out.println("Enter driver license number:");
                    int driverLicenseNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter plate number:");
                    String plateNumberForTicket = scanner.nextLine();
                    System.out.println("Enter date (dd-mm-yyyy):");
                    Date ticketDate = Date.valueOf(scanner.nextLine());
                    System.out.println("Enter reason:");
                    int reason = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter ticket value:");
                    double ticketValue = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter expiry date (dd-mm-yyyy):");
                    Date ticketExpiryDate = Date.valueOf(scanner.nextLine());
                    dataSource.insertTicket(driverLicenseNumber, plateNumberForTicket, ticketDate, reason, ticketValue, ticketExpiryDate);
                    break;

                case 8:
                    System.out.println("Enter plate number:");
                    String plateForUpdateColor = scanner.nextLine();
                    System.out.println("Enter new color:");
                    String newColor = scanner.nextLine();
                   // dataSource.updateVehicleColor(newColor, plateForUpdateColor);
                    break;

                case 9:
                    System.out.println("Enter plate number:");
                    String plateForChangeOwner = scanner.nextLine();
                    System.out.println("Enter new owner NIF:");
                    int newOwnerNif = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    dataSource.changeVehicleOwner(plateForChangeOwner, newOwnerNif);
                    break;

                case 10:
                    System.out.println("Enter insurance policy number:");
                    int policyForRenew = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter start date (dd-mm-yyyy):");
                    Date newStartDate = Date.valueOf(scanner.nextLine());
                    System.out.println("Enter expiry date (dd-mm-yyyy):");
                    Date newExpiryDate = Date.valueOf(scanner.nextLine());
                    System.out.println("Enter extra category:");
                    int newExtraCategory = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter company name:");
                    String newCompanyName = scanner.nextLine();
                    dataSource.renewInsurance(newStartDate, newExpiryDate, newExtraCategory, newCompanyName, policyForRenew);
                    break;

                case 11:
                    System.out.println("Enter plate number:");
                    String plateForDelete = scanner.nextLine();
                    System.out.println("Enter driver license number (or -1 if not applicable):");
                    int driverLicenseNumberForDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    dataSource.deleteVehicle(plateForDelete, driverLicenseNumberForDelete);
                    break;

                case 12:
                    System.out.println("Enter insurance policy number:");
                    int policyForDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                   // dataSource.deleteInsurance(policyForDelete);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
