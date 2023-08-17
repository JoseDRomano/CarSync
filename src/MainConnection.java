

import employeeacess.BackOffice;
import employeeacess.DataSource;
import model.Employee;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import util.LogUtil;

import javax.xml.crypto.Data;
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

      /*  PropertyConfigurator.configure("C:\\Users\\PedroOriakhi\\OneDrive - Polarising, Unipessoal, Lda\\Documentos\\GitHub\\IMTT-alike\\resources\\log4j.properties");

        BackOffice.startBackOffice(296789012);*/

        MainConnection.initiate();

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


}
