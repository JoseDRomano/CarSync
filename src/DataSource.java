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

    private PreparedStatement insertIntoVehicle;
    private PreparedStatement insertIntoInsurance;
    private PreparedStatement insertIntoTicket;

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


            return true;
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {

            if(insertIntoTicket != null) {
                insertIntoTicket.close();
            }

            if(insertIntoInsurance != null) {
                insertIntoInsurance.close();
            }

            if(insertIntoVehicle != null) {
                insertIntoVehicle.close();
            }

            if(queryVehicles != null) {
                queryVehicles.close();
            }

            if(queryTickets != null) {
                queryTickets.close();
            }

            if(queryInsurances != null) {
                queryInsurances.close();
            }

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
                vehicle.setCategory(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_CATEGORY)));
                vehicle.setBrand(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_BRAND)));
                vehicle.setColor(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_COLOR)));
                vehicle.setModel(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_MODEL)));
                vehicle.setRegistration_date(resultSet.getDate(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_REGISTRATION_DATE)));
                vehicle.setVin(resultSet.getString(VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_VIN)));
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


    public void insertVehicle(String plate, String vin, String color,
                              String brand, String model, Date registration_date,
                              Vehicle.VehicleCategory category) {

        if(plate.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$") == false) {
            System.out.println("Wrong plate number format");
            return;
        }

        for(Vehicle v: queryVehicles()) {
            if(v.getPlate().equals(plate)) {
                System.out.printf("Plate number: %s already in database %n", v.getPlate());
                return;
            }
        }

        try {
            connection.setAutoCommit(false);

            insertIntoVehicle.setString(1, model);
            insertIntoVehicle.setString(2, brand);
            insertIntoVehicle.setString(3, color);
            insertIntoVehicle.setString(4, plate);
            insertIntoVehicle.setString(5, category.toString());
            insertIntoVehicle.setString(6, registration_date.toString());
            insertIntoVehicle.setString(7, vin);
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


}
