package employeeacess;

import enumssql.InsuranceEnum;
import enumssql.TicketEnum;
import enumssql.VehicleEnum;
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
    private PreparedStatement payTicket;

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
            payTicket = connection.prepareStatement(TicketEnum.getString(TicketEnum.PAY_TICKET));

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

            if(payTicket != null) {
                payTicket.close();
            }

            if(updateVehicleOwner != null) {
                updateVehicleOwner.close();
            }

            if (updateTicket != null) {
                updateTicket.close();
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

    //Devolve um arraylist com todos os veículos que estão na base de dados.
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

    //Devolve um arraylist com todos os seguros que estão na base de dados.
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

    //Devolve um arraylist com todas as multas que estão na base de dados.
    public List<Ticket> queryTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try {
            ResultSet resultSet = queryTickets.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setNif(resultSet.getInt(TicketEnum.getString(TicketEnum.COLUMN_TICKET_NIF)));
                ticket.setPlate(resultSet.getString(TicketEnum.getString(TicketEnum.COLUMN_TICKET_PLATE)));
                ticket.setDate(resultSet.getDate(TicketEnum.getString(TicketEnum.COLUMN_TICKET_DATE)));
                ticket.setReason(resultSet.getInt(TicketEnum.getString(TicketEnum.COLUMN_TICKET_REASON)));
                ticket.setValue(resultSet.getDouble(TicketEnum.getString(TicketEnum.COLUMN_TICKET_VALUE)));
                ticket.setExpiry_date(resultSet.getDate(TicketEnum.getString(TicketEnum.COLUMN_TICKET_EXPIRY_DATE)));
                ticket.setPaid(resultSet.getInt(TicketEnum.getString(TicketEnum.COLUMN_TICKET_PAID)));
                tickets.add(ticket);
            }
            return tickets;

        }catch (SQLException e) {
            System.out.println("Couldn't retrieve data from ticket table: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /*Devolve uma lista de veículos correspondente a um dado nif, se o nif não constar na base de dados
     *devolve null*/
    public List<Vehicle> getVehicle(int nif) {
        List<Vehicle> vehicles = new ArrayList<>();
        for(Vehicle vehicle : queryVehicles()) {
            if(vehicle.getNif() == nif) {
                Vehicle vehicle1 = new Vehicle();
                vehicle1.setBrand(vehicle.getBrand());
                vehicle1.setCategory(vehicle.getCategory());
                vehicle1.setColor(vehicle.getColor());
                vehicle1.setModel(vehicle.getModel());
                vehicle1.setPlate(vehicle.getPlate());
                vehicle1.setVin(vehicle.getVin());
                vehicle1.setRegistrationDate(vehicle.getregistrationDate());
                vehicle1.setNif(vehicle.getNif());
                vehicles.add(vehicle1);
            }
        }
        if(vehicles.isEmpty()) {
            System.out.println("No vehicles found for nif: " + nif);
            return null;
        }
        return vehicles;
    }


    /*Permite inserir um seguro dado, tendo o apolice do seguro e nif.
     */
    public void insertInsurance(int policy, String plate, Date startDate, int extraCategory, Date expDate, String companyName, int nif) {
        //Funciona, mas seria importante ver antecipadamente se o nif está na base de dados ou não
        if( !isVehicleOwner(nif, plate)) {
            System.out.println("Not owner of vehicle with plate: " + plate);
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


    /*Permite inserir um veículo dado a matrícula, e as caracteristicas do veículo. E ainda
    o nif que deve corresponder a um customer que exista na base de dados o que ainda não é
    verificado neste metodo.
    * */
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

        if(!isVehicleOwner(nif, plate)) {
            System.out.println("NIF: " + nif + " is not the owner of vehicle with plate: " + plate);
            return;
        }

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


    //NOT TESTED
    public void insertTicket(int nif, String plate, Date date, int reason, double value, Date expiry_date) {

      /*  if(!checkIfPlateAndDriverExists(driver_license_number, plate)) {
            System.out.println("Plate or driver license number doesn't exist in database" + driver_license_number + " " + plate);
            return;
        }
*/
        try {
            connection.setAutoCommit(false);
            insertIntoTicket.setInt(3, nif);
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


    //NOT TESTED
    public void updateVehicleColor(String color, String plate, int nif) {

        if(!isVehicleOwner(nif, plate)) {
            System.out.println("Something wrong with provided info");
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


    //NOT TESTED
    public void changeVehicleOwner(String plate, int oldNif, int newNif) {

        if(!isVehicleOwner(oldNif, plate)) {
            System.out.println("Something wron with provided info");
            return;
        }

        try {
            connection.setAutoCommit(false);
            updateVehicleOwner.setInt(1, newNif);
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


    /*Permite atualizar o valor a pagar de uma multa, este metodo deve ser chamado antes de listar as multas.
    Ainda está incompleto.
    **/
    public void updateExpiredTicket(Date oldDate, double newValue, Date expDate, int nif, String plate, Date todayDate) {

        //O novo valor da multa pode ser calculado seguindo uma dada tarifa, por exemplo?

        try {
            connection.setAutoCommit(false);
            updateTicket.setDate(1, todayDate);
            updateTicket.setDouble(2, newValue);
            updateTicket.setDate(3, expDate);
            updateTicket.setInt(4, nif);
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

    //Permite saber se o seguro existe.
    private boolean insuranceExists(int policy, int nif) {
        for(Insurance i: queryInsurances()) {
            if(isVehicleOwner(nif, i.getCarPlate())) {
                if (i.getPolicy() == policy) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isVehicleOwner(int nif, String plate) {
        for(Vehicle v: queryVehicles()) {
            if(v.getPlate().equals(plate) ) {
                if(v.getNif() == nif) {
                    return true;
                }
                System.out.println("Vehicle with plate: " + plate + " is not owned by NIF: " + nif);
                return false;
            }
        }
        System.out.println("NIF and plate aren't registered in the database");
        return false;
    }


    //NOT TESTED
    public void renewInsurance(Date startDate, Date expiryDate, int Category, String companyName, int policy, int nif) {

        if(!insuranceExists(policy, nif)) {
            System.out.println("Wrong info, policy: " + policy + " NIF: " + nif);
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



    //NOT TESTED
    public void payTicket(int nif, String plate, Date date, double value) {

        if(isVehicleOwner(nif, plate)) {
            System.out.println("Wrong information, plate: " + plate + " nif: " + nif);
            return;
        }

        int paid = 0;

        for(Ticket t: queryTickets()) {
            if(t.getNif() == nif && t.getPlate().equals(plate) && t.getDate().equals(date)) {
                if(t.isPaid()) {
                    System.out.println("Ticket is already paid");
                    return;
                }
                else {
                    if(t.payTicket(value)) {
                        paid = 1;
                        break;
                    }
                    break;
                }
            }
        }

        try {
            connection.setAutoCommit(false);
            payTicket.setInt(1, paid);
            payTicket.setInt(2, nif);
            payTicket.setString(3, plate);
            payTicket.setDate(4, date);
            int affected = payTicket.executeUpdate();

            if(affected == 1) {
                connection.commit();
            } else  {
                throw new SQLException("Couldn't pay ticket");
            }

        }catch (SQLException e) {
            System.out.println("Couldn't pay ticket: " + e.getMessage());
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


    //NOT TESTED
    public void deleteVehicle(String plate, int nif) { //Inacabado falta primeiro criar a classe Driver

        //Faz sentido que apenas possamos apagar carros que não tenham dono, ou seja, NIF associado.

        if(!isVehicleOwner(nif, plate)) {
            System.out.println("Wrong info, plate: " + plate + " NIF: " + nif);
            return;
        }

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


    //NOT TESTED
    public void deleteInsurance(int policy, int nif) {

        if(!insuranceExists(policy, nif)) {
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
