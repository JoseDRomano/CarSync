

import employeeacess.BackOffice;
import employeeacess.DataSource;
import model.Employee;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.chainsaw.Main;
import org.mindrot.jbcrypt.BCrypt;
import util.LogUtil;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class MainConnection {

    private static final Logger logger = Logger.getLogger(MainConnection.class);


    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

       /* PropertyConfigurator.configure("C:\\Users\\PedroOriakhi\\OneDrive - Polarising, Unipessoal, Lda\\Documentos\\GitHub\\IMTT-alike\\resources\\log4j.properties");

//        BackOffice.startBackOffice(296789012);
        MainConnection.initiate();*/

        MainConnection.readTicketsFromFile();

    }

    private static void initiate() {
        try {
            WelcomeMenu wm = new WelcomeMenu();
            wm.run();
        }
        catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean readTicketsFromFile() {
        boolean result = false;
        Path filePath = Path.of("Tickets.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(filePath.toString())));
            scanner.useDelimiter("^[-]{5,}$"); {
                while(scanner.hasNextLine()) {
                    int ticketID = MainConnection.getID(scanner.nextLine());
                    String ticketType = MainConnection.ticketType(scanner.nextLine());
                    String ticketStatus = MainConnection.ticketStatus(scanner.nextLine());
                    Date ticketDate = MainConnection.ticketDate(scanner.nextLine());
                    String ticketInfo = MainConnection.ticketInfo(scanner.nextLine());
                    int nif = MainConnection.getNIF(scanner.nextLine());
                    System.out.println(ticketID);
                    System.out.println(ticketType);
                    System.out.println(ticketStatus);
                    System.out.println(ticketDate.toString());
                    System.out.println(ticketInfo);
                    System.out.println(nif);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
        return result;

    }

    static int getID(String s) {
       String[] svalues = s.trim().split(":");
       return Integer.parseInt(svalues[1].trim());
    }

    static String ticketType(String s) {
        String[] svalues = s.split(":");
        String type = svalues[1];
        return type;
    }

    static String ticketStatus(String s) {
        String[] svalues = s.split(":");
        String status = svalues[1];
        return status;
    }

    static Date ticketDate(String s) {
        String[] svalues = s.split(":");
        Date date = java.sql.Date.valueOf(svalues[1].trim());
        return date;
    }

    static int getNIF(String s) {
        String[] svalues = s.split(":");
        int nif = Integer.parseInt(svalues[1].trim());
        return nif;
    }

    static String ticketInfo(String s) {
        String[] svalues = s.split(":");
        String info = svalues[1];
        return info;
    }




}
