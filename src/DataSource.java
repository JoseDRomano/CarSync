import model.Insurance;
import model.Ticket;
import model.Vehicle;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static final String DB_NAME = "projeto_imt";

    //Este port number é o port number que aparece no XAMPP quando voçês dão start
    //para conectar à base de dados e no meu caso é 3306.
    public static final int PORT_NUMBER = 3306;
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + DB_NAME;
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    private PreparedStatement queryVehicles;
    private PreparedStatement queryInsurances;
    private PreparedStatement queryTickets;
    private PreparedStatement queryVehicleByPlate;

    private PreparedStatement insertIntoVehicle;
    private PreparedStatement insertIntoInsurance;
    private PreparedStatement insertIntoTicket;

    private PreparedStatement updateVehicleColor;
    private PreparedStatement renewInsurance;
    private PreparedStatement updateTicket;
    private PreparedStatement updateVehicleOwner;

    private PreparedStatement deleteVehicle;
    private PreparedStatement deleteInsurance;
    private PreparedStatement deleteTicket;


    private Connection connection;

    public boolean open() {

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            insertIntoInsurance = connection.prepareStatement(InsuranceEnum.getString(InsuranceEnum.INSERT_INSURANCE));
            insertIntoVehicle = connection.prepareStatement(VehicleEnum.getString(VehicleEnum.INSERT_VEHICLE));
            insertIntoTicket = connection.prepareStatement(TicketEnum.getString(TicketEnum.INSERT_TICKET));

            queryInsurances = connection.prepareStatement(InsuranceEnum.getString(InsuranceEnum.QUERY_TABLE_INSURANCE));
            queryTickets = connection.prepareStatement(TicketEnum.getString(TicketEnum.QUERY_TABLE_TICKET));
            queryVehicles = connection.prepareStatement(VehicleEnum.getString(VehicleEnum.QUERY_TABLE_VEHICLE));
            queryVehicleByPlate = connection.prepareStatement(VehicleEnum.getString(VehicleEnum.QUERY_TABLE_VEHICLE_BY_PLATE));

            renewInsurance = connection.prepareStatement(InsuranceEnum.getString(InsuranceEnum.RENEW_INSURANCE));
            updateVehicleColor = connection.prepareStatement(VehicleEnum.getString(VehicleEnum.UPDATE_VEHICLE_COLOR));
            updateVehicleOwner = connection.prepareStatement(VehicleEnum.getString(VehicleEnum.UPDATE_VEHICLE_OWNER));

            //Serve para atualizar o valor da multa e a data de validade quando o mesmo for necessário.
            updateTicket = connection.prepareStatement(TicketEnum.getString(TicketEnum.UPDATE_TICKET));

            deleteVehicle = connection.prepareStatement(VehicleEnum.getString(VehicleEnum.DELETE_VEHICLE));
            deleteInsurance = connection.prepareStatement(InsuranceEnum.getString(InsuranceEnum.DELETE_INSURANCE));
            deleteTicket = connection.prepareStatement(TicketEnum.getString(TicketEnum.DELETE_TICKET));

            return true;
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {

            if (insertIntoTicket != null) {
                insertIntoTicket.close();
            }

            if (insertIntoInsurance != null) {
                insertIntoInsurance.close();
            }

            if (insertIntoVehicle != null) {
                insertIntoVehicle.close();
            }

            if (queryVehicles != null) {
                queryVehicles.close();
            }

            if (queryTickets != null) {
                queryTickets.close();
            }

            if (queryInsurances != null) {
                queryInsurances.close();
            }

            if (renewInsurance != null) {
                renewInsurance.close();
            }

            if (updateVehicleColor != null) {
                updateVehicleColor.close();
            }

/*          if (updateTicket != null) {
            updateTicket.close();
            }*/

            if(connection != null) {
                connection.close();
                System.out.println("Connection successfully closed");
            }

        }catch (SQLException e) {
            System.out.println("Couldn't close connection to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Vehicle> queryVehicles() {

        List<Vehicle> vehicles = new ArrayList<>();
        try {
            ResultSet resultSet = queryVehicles.executeQuery();
            while(resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setPlate(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_PLATE)));
                vehicle.setCategory(resultSet.getInt(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_CATEGORY)));
                vehicle.setBrand(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_BRAND)));
                vehicle.setColor(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_COLOR)));
                vehicle.setModel(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_MODEL)));
                vehicle.setRegistrationDate(resultSet.getDate(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_REGISTRATION_DATE)));
                vehicle.setVin(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_VIN)));
                vehicle.setNif(resultSet.getInt(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_NIF)));
                vehicles.add(vehicle);
            }
            return vehicles;
        }
        catch (SQLException e) {
            System.out.println("Couldn't retrieve data from vehicle table: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Insurance> queryInsurances() {
        List<Insurance> insurances = new ArrayList<>();
        try {
            ResultSet resultSet = queryInsurances.executeQuery();
            while (resultSet.next()) {
                Insurance insurance = new Insurance();
                insurance.setPolicy(resultSet.getInt(InsuranceEnum.getString(InsuranceEnum.COLUMN_INSURANCE_POLICY)));
                insurance.setCarPlate(resultSet.getString(InsuranceEnum.getString(InsuranceEnum.COLUMN_INSURANCE_PLATE)));
                insurance.setStartDate(resultSet.getDate(InsuranceEnum.getString(InsuranceEnum.COLUMN_INSURANCE_START_DATE)));
                insurance.setExtraCategory(resultSet.getInt(InsuranceEnum.getString(InsuranceEnum.COLUMN_INSURANCE_EXTRA_CATEGORY)));
                insurance.setExpDate(resultSet.getDate(InsuranceEnum.getString(InsuranceEnum.COLUMN_INSURANCE_EXPIRY_DATE)));
                insurance.setCompanyName(resultSet.getString(InsuranceEnum.getString(InsuranceEnum.COLUMN_INSURANCE_COMPANY)));
                insurances.add(insurance);
            }
            return insurances;

        }catch (SQLException e) {
            System.out.println("Couldn't retrieve data from insurance table: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Ticket> queryTicket() {
        List<Ticket> tickets = new ArrayList<>();
        try {
            ResultSet resultSet = queryTickets.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setDriver_license_number(resultSet.getInt(TicketEnum.getString(TicketEnum.COLUMN_TICKET_DRIVER_LICENSE_NUMBER)));
                ticket.setPlate(resultSet.getString(TicketEnum.getString(TicketEnum.COLUMN_TICKET_PLATE)));
                ticket.setDate(resultSet.getDate(TicketEnum.getString(TicketEnum.COLUMN_TICKET_DATE)));
                ticket.setReason(resultSet.getInt(TicketEnum.getString(TicketEnum.COLUMN_TICKET_REASON)));
                ticket.setValue(resultSet.getDouble(TicketEnum.getString(TicketEnum.COLUMN_TICKET_VALUE)));
                ticket.setExpiry_date(resultSet.getDate(TicketEnum.getString(TicketEnum.COLUMN_TICKET_EXPIRY_DATE)));
                tickets.add(ticket);
            }
            return tickets;

        }catch (SQLException e) {
            System.out.println("Couldn't retrieve data from ticket table: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Vehicle getVehicle(String plate) {
        for(Vehicle vehicle : queryVehicles()) {
            if(vehicle.getPlate().equals(plate)) {
                Vehicle vehicle1 = new Vehicle();
                vehicle1.setBrand(vehicle.getBrand());
                vehicle1.setCategory(vehicle.getCategory());
                vehicle1.setColor(vehicle.getColor());
                vehicle1.setModel(vehicle.getModel());
                vehicle1.setPlate(vehicle.getPlate());
                vehicle1.setVin(vehicle.getVin());
                vehicle1.setRegistrationDate(vehicle.getregistrationDate());
                vehicle1.setNif(vehicle.getNif());
                return vehicle1;
            }
        }
        System.out.println("No such vehicle with plate number: " + plate);
        return null;
    }

    public void insertInsurance(int policy, String plate, Date startDate, int extraCategory, Date expDate, String companyName) {

        if(checkIfPlateExists(plate) == false || extraCategory < 0 || extraCategory > 3) {
            System.out.println("Plate does not exist" + plate);
            return;
        }

        try {
            connection.setAutoCommit(false);
            insertIntoInsurance.setInt(4, policy);
            insertIntoInsurance.setString(2, plate);
            insertIntoInsurance.setDate(6, startDate);
            insertIntoInsurance.setInt(5, extraCategory);
            insertIntoInsurance.setDate(3, expDate);
            insertIntoInsurance.setString(1, companyName);
            int affected = insertIntoInsurance.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't insert new insurance with policy number: " + policy);
            }

        }catch (SQLException e) {
            System.out.println("Couldn't insert data into insurance table: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            }catch (SQLException e2) {
                System.out.println("Couldn't perform rollback: " + e2.getMessage());
                e2.printStackTrace();
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                connection.setAutoCommit(true);
            }catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public void insertVehicle(String plate, String vin, String color,
                              String brand, String model, Date registrationDate,
                              int categoryNumber, int nif) {

        if(!plate.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$") || categoryNumber <= 0 || categoryNumber > 6) {
            System.out.println("Wrong input: " + plate + " " + categoryNumber);
            return;
        }

        for(Vehicle v: queryVehicles()) {
            if(v.getPlate().equals(plate)) {
                System.out.printf("Plate number: %s already in database %n", v.getPlate());
                return;
            }
        }

        //É necessário previamente verficar se o nif existe na tabela de clientes.

        try {
            connection.setAutoCommit(false);

            insertIntoVehicle.setString(1, model);
            insertIntoVehicle.setString(2, brand);
            insertIntoVehicle.setString(3, color);
            insertIntoVehicle.setString(4, plate);
            insertIntoVehicle.setInt(5, categoryNumber);
            insertIntoVehicle.setString(6, registrationDate.toString());
            insertIntoVehicle.setString(7, vin);
            insertIntoVehicle.setInt(8, nif);
            int affected = insertIntoVehicle.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't insert new vehicle with plate number: " + plate);
            }

        } catch (SQLException e) {
            System.out.println("Insert vehicle exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Couldn't perform rollback after inserting vehicle went wrong");
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior after insert vehicle");
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset default commit behavior after insert vehicle");
            }
        }
    }
    public void insertTicket(int driver_license_number, String plate, Date date, int reason, double value, Date expiry_date) {

      /*  if(!checkIfPlateAndDriverExists(driver_license_number, plate)) {
            System.out.println("Plate or driver license number doesn't exist in database" + driver_license_number + " " + plate);
            return;
        }
*/
        try {
            connection.setAutoCommit(false);
            insertIntoTicket.setInt(3, driver_license_number);
            insertIntoTicket.setString(2, plate);
            insertIntoTicket.setDate(1, date);
            insertIntoTicket.setInt(5, reason);
            insertIntoTicket.setDouble(4, value);
            insertIntoTicket.setDate(6, expiry_date);
            int affected = insertIntoTicket.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't insert new ticket with plate number: " + plate);
            }

        }catch (SQLException e) {
            System.out.println("Couldn't insert data into ticket table: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            }catch (SQLException e2) {
                System.out.println("Couldn't perform rollback: " + e2.getMessage());
                e2.printStackTrace();
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                connection.setAutoCommit(true);
            }catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void updateVehicleColor(String color, String plate) {

        if(!checkIfPlateExists(plate)) {
            System.out.println("Plate does not exist " + plate);
            return;
        }

        try {
            connection.setAutoCommit(false);
            updateVehicleColor.setString(1, color);
            updateVehicleColor.setString(2, plate);
            int affected = updateVehicleColor.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't update vehicle color");
            }

        }catch (SQLException e) {
            System.out.println("Couldn't update vehicle color: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            }catch (SQLException e2) {
                System.out.println("Couldn't perform rollback: " + e2.getMessage());
                e2.printStackTrace();
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                connection.setAutoCommit(true);
            }catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void changeVehicleOwner(String plate, int nif) {

        if(!checkIfPlateExists(plate)) {
            System.out.println("Plate does not exist " + plate);
            return;
        }

        //Necessário verificar que o novo nif corresponde a uma pessoa que exitsta
        //na tabela customer.

        try {
            connection.setAutoCommit(false);
            updateVehicleOwner.setInt(1, nif);
            updateVehicleOwner.setString(2, plate);
            int affected = updateVehicleOwner.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't update vehicle owner");
            }

        }catch (SQLException e) {
            System.out.println("Couldn't update vehicle owner: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            }catch (SQLException e2) {
                System.out.println("Couldn't perform rollback: " + e2.getMessage());
                e2.printStackTrace();
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                connection.setAutoCommit(true);
            }catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void updateExpiredTicket(Date oldDate, double newValue, Date expDate, int driverLicense, String plate, Date todayDate) {

        if(!checkIfPlateAndDriverExists(driverLicense, plate)) {
            System.out.println("Plate or driver license number doesn't exist in database" + driverLicense + " " + plate);
            return;
        }

        try {
            connection.setAutoCommit(false);
            updateTicket.setDate(1, todayDate);
            updateTicket.setDouble(2, newValue);
            updateTicket.setDate(3, expDate);
            updateTicket.setInt(4, driverLicense);
            updateTicket.setString(5, plate);
            updateTicket.setDate(6, oldDate);
            int affected = updateTicket.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't update ticket");
            }

        }catch (SQLException e) {
            System.out.println("Couldn't update ticket: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            }catch (SQLException e2) {
                System.out.println("Couldn't perform rollback: " + e2.getMessage());
                e2.printStackTrace();
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                connection.setAutoCommit(true);
            }catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private boolean checkIfPlateExists(String plate) {
        List<Vehicle> vehicles = queryVehicles();
        for(var v : vehicles) {
            if(v.getPlate().equals(plate)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfPlateAndDriverExists(int driver_license_number, String plate) {
        List<Vehicle> vehicles = queryVehicles();
        //List<Driver> drivers = queryDrivers();
        for(var v : vehicles) {
            if(v.getPlate().equals(plate)) {
                return true;
            }
        }
        return false;
    }

    private boolean insuranceExists(int policy) {
        for(Insurance i: queryInsurances()) {
            if(i.getPolicy() == policy) {
                return true;
            }
        }
        return false;
    }

    public void renewInsurance(Date startDate, Date expiryDate, int Category, String companyName, int policy) {

        if(!insuranceExists(policy)) {
            System.out.println("No such insurance with policy number: " + policy);
            return;
        }

        try {
            connection.setAutoCommit(false);
            renewInsurance.setDate(1, startDate);
            renewInsurance.setDate(2, expiryDate);
            renewInsurance.setInt(3, Category);
            renewInsurance.setString(4, companyName);
            renewInsurance.setInt(5, policy);
            int affected = renewInsurance.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't renew insurance with policy number: " + policy);
            }

        }catch (SQLException e) {
            System.out.println("Couldn't renew insurance: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            }catch (SQLException e2) {
                System.out.println("Couldn't perform rollback: " + e2.getMessage());
                e2.printStackTrace();
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                connection.setAutoCommit(true);
            }catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void deleteVehicle(String plate, int driver_license_number) { //Inacabado falta primeiro criar a classe Driver

        //Faz sentido que apenas possamos apagar carros que não tenham dono, ou seja, Driver associado.

       /* if(!checkIfPlateExists(plate)) {
            System.out.println("Plate doesn't exist in database" + plate);
            return;
        }*/

        try {
            connection.setAutoCommit(false);
            deleteVehicle.setString(1, plate);
            int affected = deleteVehicle.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't delete vehicle with plate number: " + plate);
            }

        }catch (SQLException e) {
            System.out.println("Couldn't delete vehicle: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            }catch (SQLException e2) {
                System.out.println("Couldn't perform rollback: " + e2.getMessage());
                e2.printStackTrace();
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                connection.setAutoCommit(true);
            }catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void deleteInsurance(int policy) {

        if(!insuranceExists(policy)) {
            System.out.println("No such insurance with policy number: " + policy);
            return;
        }

        try {
            connection.setAutoCommit(false);
            deleteInsurance.setInt(1, policy);
            int affected = deleteInsurance.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't delete insurance with policy number: " + policy);
            }

        }catch (SQLException e) {
            System.out.println("Couldn't delete insurance: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            }catch (SQLException e2) {
                System.out.println("Couldn't perform rollback: " + e2.getMessage());
                e2.printStackTrace();
            }
        }finally {
            try {
                System.out.println("Resetting default commit behavior");
                connection.setAutoCommit(true);
            }catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }





}

