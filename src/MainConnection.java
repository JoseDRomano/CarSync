

import employeeacess.BackOffice;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Scanner;


public class MainConnection {

    private static final Logger logger = Logger.getLogger(MainConnection.class);


    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        PropertyConfigurator.configure("C:\\Users\\PedroOriakhi\\OneDrive - Polarising, Unipessoal, Lda\\Documentos\\GitHub\\IMTT-alike\\resources\\log4j.properties");

        //Password para o nif 200000000 é Ab12@d3 (employee)
        /*Password para o nif 200000001 é Ab12@d4 (customer)
        + driver license number 1000000*/
        BackOffice.startBackOffice(296789012);

//        MainConnection.initiate();
        /*String s = "123abc";
        String hashed = BCrypt.hashpw(s, BCrypt.gensalt());
        System.out.println(hashed);*/


//        MainConnection.readTicketsFromFile();
        /*String s = """
                - Info:
                    Customer Name - John Doe;
                    Customer Address - 123 Main Street;
                    Customer Password - asdksaldjalskdj4312313@
                    Customer birth date - 1990-01-01
                    Customer driver license number - 123456789
                    Customer driver license expiration date - 2020-01-01
                    Customer license type - 3""";
        String[] svalues = s.split(":");
        System.out.println(svalues[1]);
        String s2 = svalues[1].replaceAll("\\t", "");
        System.out.println(s2);*/
    }

    private static void initiate() {
        try {
            WelcomeMenu wm = new WelcomeMenu();
            wm.run();
        } catch (SQLException e) {
            System.out.println("Error connecting to database" + e.getMessage());
            e.printStackTrace();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static boolean readTicketsFromFile() {
        boolean result = false;
        Path filePath = Path.of("Tickets.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Tickets.txt")));
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
        return type.trim();
    }

    static String ticketStatus(String s) {
        String[] svalues = s.split(":");
        String status = svalues[1];
        return status.trim();
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
*/


}
