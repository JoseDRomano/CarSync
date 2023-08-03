import employeeacess.DataSource;
import model.Insurance;
import model.Ticket;
import model.Vehicle;

import java.security.NoSuchAlgorithmException;
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

//    private static void showLogin(DataSource datasource) throws NoSuchAlgorithmException {
//        System.out.println("===== LOGIN =====");
//        System.out.println("Enter your NIF:");
//        Scanner sc = new Scanner(System.in);
//        String nif = sc.nextLine();
//        System.out.println("Enter your password:");
//        String password = sc.nextLine();
//        //ver se isto dá print mesmo estando dentro do if
//        if (datasource.login(nif, password)) {
//
//            System.out.print("===== MENU =====\n" +
//                    "");
//        } else {
//
//        }
//    }

    private static void showMenu(DataSource dataSource) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("===== MENU =====");

            /*Para os 3 métodos de listar basta tmb pedir
            * o número de coisas para listar*/
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
            System.out.println("13. Pay ticket");
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
                    System.out.println("Enter the plate number in formate XX-XX-XX: ");
                    String plate = scanner.nextLine();
                    plate.trim();
                    System.out.println("Enter the NIF: ");
                    int nif = Integer.valueOf(scanner.nextLine().trim());
                    List<Vehicle> vehicles2 = dataSource.getVehicleByNIF(nif);
                    if (vehicles2 != null) {
                        for(Vehicle vehicle : vehicles2) {
                            if(vehicle.getNif() == nif) {
                                System.out.println(vehicle);
                            }
                        }
                    } else {
                        System.out.println("No vehicles found for plate: " + plate + " and NIF: " + nif + ".");
                    }
                    break;

                case 5:
                    System.out.println("Enter plate number in format XX-XX-XX: ");
                    String plateNumber = scanner.nextLine().trim();
                    System.out.println("Enter VIN: ");
                    String vin = scanner.nextLine().trim();
                    System.out.println("Enter color: ");
                    String color = scanner.nextLine().replaceAll("\n", "");
                    System.out.println("Enter brand: ");
                    String brand = scanner.nextLine().replaceAll("\n", "");;
                    System.out.println("Enter model: ");
                    String model = scanner.nextLine().replaceAll("\n", "");;
                    System.out.println("Enter registration date (yyyy-mm-day):");
                    Date registrationDate = Date.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter category number: ");
                    int categoryNumber = Integer.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter NIF: ");
                    int nif2 = Integer.valueOf(scanner.nextLine().trim());
                    dataSource.insertVehicle(plateNumber, vin, color, brand, model, registrationDate, categoryNumber, nif2);
                    break;

                case 6:
                    System.out.println("Enter policy number: ");
                    int policyNumber = Integer.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter plate number in format XX-XX-XX: ");
                    String plateNumberForInsurance = scanner.nextLine().trim();
                    System.out.println("Enter start date (yyyy-mm-dd):");
                    Date startDate = Date.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter extra category: ");
                    int extraCategory = Integer.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter expiry date (yyyy-mm-dd):");
                    Date expiryDate = Date.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter company name:" );
                    String companyName = scanner.nextLine().replaceAll("\n", "");;
                    System.out.println("Enter NIF: ");
                    int nif3 = Integer.valueOf(scanner.nextLine().trim());
                    dataSource.insertInsurance(policyNumber, plateNumberForInsurance, startDate, extraCategory, expiryDate, companyName, nif3);
                    break;

                case 7:
                    System.out.println("Enter NIF: ");
                    int driverLicenseNumber = Integer.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter plate number: ");
                    String plateNumberForTicket = scanner.nextLine().trim();
                    System.out.println("Enter date (yyyy-mm-dd):");
                    Date ticketDate = Date.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter reason number for:" + "\n" + "Speeding -> 1 " + "\n" +
                            "Red Light -> 2" + "\n" + "Illegal Parking -> 3" + "\n" + "Reckless Driving -> 4" + "\n" + "DUI -> 5");
                    int reason = Integer.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter ticket value: ");
                    double ticketValue = Double.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter expiry date (yyyy-mm-dd):");
                    Date ticketExpiryDate = Date.valueOf(scanner.nextLine().trim());
                    dataSource.insertTicket(driverLicenseNumber, plateNumberForTicket, ticketDate, reason, ticketValue, ticketExpiryDate);
                    break;

                case 8:
                    System.out.println("Enter plate number: ");
                    String plateForUpdateColor = scanner.nextLine().trim();
                    System.out.println("Enter new color: ");
                    String newColor = scanner.nextLine().replaceAll("\n", "");
                    System.out.println("Enter NIF: ");
                    int nif4 = Integer.valueOf(scanner.nextLine().trim());
                    dataSource.updateVehicleColor(newColor, plateForUpdateColor, nif4);
                    break;

                case 9:
                    System.out.println("Enter plate number in format XX-XX-XX:");
                    String plateForChangeOwner = scanner.nextLine().trim();
                    System.out.println("Enter new owner NIF:");
                    int newOwnerNif = Integer.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter old owner NIF:");
                    int oldOwnerNif = Integer.valueOf(scanner.nextLine().trim());
                    dataSource.changeVehicleOwner(plateForChangeOwner, oldOwnerNif, newOwnerNif);
                    break;

                case 10:
                    System.out.println("Enter insurance policy number: ");
                    int policyForRenew = Integer.valueOf(scanner.nextLine().trim());;
                    System.out.println("Enter start date (yyyy-mm-dd):");
                    Date newStartDate = Date.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter expiry date (yyyy-mm-dd):");
                    Date newExpiryDate = Date.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter extra category:");
                    int newExtraCategory = Integer.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter company name:");
                    String newCompanyName = scanner.nextLine().replaceAll("\n", "");
                    int nif5 = Integer.valueOf(scanner.nextLine().trim());
                    dataSource.renewInsurance(newStartDate, newExpiryDate, newExtraCategory, newCompanyName, policyForRenew, nif5);
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
                    System.out.println("Enter NIF: ");
                    int nif6 = scanner.nextInt();
                    dataSource.deleteInsurance(policyForDelete, nif6);
                    break;

                case 13:
                    System.out.println("Enter NIF: ");
                    int nif7 = Integer.valueOf(scanner.nextLine().trim());
                    System.out.println("Enter plate number in format XX-XX-XX:");
                    String plate7 = scanner.nextLine().trim();
                    System.out.println("Enter date (yyyy-mm-dd):");
                    Date date7 = Date.valueOf(scanner.nextLine().trim());
                    System.out.println("Amount of money: ");
                    Double d7 = Double.valueOf(scanner.nextLine().trim());
                    dataSource.payTicket(nif7, plate7, date7, d7);
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
