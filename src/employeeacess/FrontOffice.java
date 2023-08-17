package employeeacess;

import model.Customer;
import model.Insurance;
import model.Ticket;
import model.Vehicle;
import java.util.List;
import java.util.Scanner;

public class FrontOffice {
    private DataSource dataSource;
    private Customer customer;
    private Scanner scan;

    public FrontOffice(DataSource dataSource, Customer customer) {
        this.dataSource = dataSource;
        this.customer = customer;
        this.scan = new Scanner(System.in);
    }

    public void viewCustomerVehicles() {
        List<Vehicle> customerVehicles = dataSource.getVehicleByNIF(customer.getNif());

        if (customerVehicles == null || customerVehicles.isEmpty()) {
            System.out.println("No vehicles found for the customer.");
        } else {
            System.out.println("Vehicles for Customer " + customer.getNif() + ":");
            for (Vehicle vehicle : customerVehicles) {
                System.out.println(vehicle);
            }
        }
    }

   // public void viewCustomerInsurances() {


      //  List<Insurance> insurances = dataSource.queryInsurancesByNIF(customer.getNif());

      //  if (insurances == null || insurances.isEmpty()) {
      //      System.out.println("No insurances found for the customer.");
      //  } else {
      //      System.out.println("Insurances for Customer " + customer.getNif() + ":");
      //      for (Insurance insurance : insurances) {
      //          System.out.println(insurance);
      //      }
      //  }

   // }

    public void viewCustomerTickets() {
        List<Ticket> customerTickets = dataSource.queryTickets();

        if (customerTickets == null || customerTickets.isEmpty()) {
            System.out.println("No tickets found for the customer.");
        } else {
            System.out.println("Tickets for Customer " + customer.getNif() + ":");
            for (Ticket ticket : customerTickets) {
                System.out.println(ticket);
            }
        }
    }


    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Front Office Menu ===");
            System.out.println("1. View Vehicles");
            System.out.println("2. View Insurances");
            System.out.println("3. View Tickets");
            System.out.println("0. Exit");
            System.out.print("Please enter your choice: ");

            int choice = scan.nextInt();
            scan.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewCustomerVehicles();
                    break;
                case 2:
                  //  viewCustomerInsurances();
                    break;
                case 3:
                    viewCustomerTickets();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting Front Office...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void main(String[] args) {
       //processo de login dps
        int customerNif = 200000000; /*dps meter o metodo de  login que apanha o nif*/

        DataSource dataSource = new DataSource();

        if (!dataSource.open()) {
            throw new IllegalStateException("Cannot connect to database");
        }

        Customer customer = dataSource.getCustomerByNIF(customerNif);

        if (customer == null) {
            throw new IllegalArgumentException("Customer not found for NIF: " + customerNif);
        }

        FrontOffice frontOffice = new FrontOffice(dataSource, customer);
        frontOffice.showMenu();
    }
}