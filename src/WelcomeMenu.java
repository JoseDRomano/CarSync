import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class WelcomeMenu {

    public static void main(String[] args) throws SQLException {
        run();
    }

    public static void run() throws SQLException {
        System.out.println("Welcome to IMT (but better). For every menu you'll have a few options to choose and you'll" +
                " have to type the number of the option you want to choose. \n" +
                "For any question, contact us at 217 949 000");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                Login login = new Login();
                login.run();
                break;
            case 2:
                Register register = new Register();
                register.run();
                break;
            case 3:
                System.out.println("Bye bye");
                break;
            default:
                System.out.println("Invalid option. Please try again");
                run();
        }
        scanner.close();
    }
}

class Register {
    private static final String SUCCESSFUL_REGISTER = "Register successful, please login";
    private static final String NIF_ALREADY_REGISTERED = "NIF already registered in our System. Please login";

    public static final String DB_NAME = "projeto_imt";
    //Este port number é o port number que aparece no XAMPP quando voçês dão start
    //para conectar à base de dados e no meu caso é 3306.
    public static final int PORT_NUMBER = 3306;

    private static final String URL = "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + DB_NAME;
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    private Connection connection;

    public void run() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Scanner scanner = new Scanner(System.in);
        System.out.println("------ Register ------");

        int nif = validateNIF(scanner);

        System.out.println("Enter name:");
        String name = scanner.nextLine().trim();

        System.out.println("Enter address:");
        String address = scanner.nextLine().trim();

        System.out.println("Enter birthdate (YYYY-MM-DD):");
        String birthdate = validateDate(scanner);

        //Password
        String password = validatePWD(scanner);

        //Other info
        System.out.println("Enter driver license:");
        String driver_license = scanner.nextLine().trim();
        System.out.println("Enter License Type:");
        System.out.println("1. A\n" + "2. B\n" + "3. C\n" + "4. D\n" + "(enter the number please)");
        int license_type = scanner.nextInt();
        System.out.println("Starting date:");
        String starting_date = validateDate(scanner);
        System.out.println("Expiration date:");
        String expiration_date = validateDate(scanner);

        /*código SQL para registar na base de dados*/
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String sql = "INSERT INTO users (nif, name, address, birthdate, password, driver_license, license_type, " +
                "starting_date, expiration_date) VALUES ('" + nif + "', '" + name + "', '" + address + "', '" +
                birthdate + "', '" + hashedPassword + "', '" + driver_license + "', '" + license_type + "', '" +
                starting_date + "', '" + expiration_date + "')";

        /*Passamos para o login*/
        System.out.println(SUCCESSFUL_REGISTER);
        Login login = new Login();
        login.run();
    }

    //check if the passowrd is at least 8 characters long
    private String validatePWD(Scanner scanner) {
        System.out.println("Enter password (must be at least 8 characters long):");
        String password = scanner.nextLine().trim();

        while (!(password.length() < 8)) {
            System.out.println("Password must be at least 8 characters long");
            System.out.println("Enter password (must be at least 8 characters long):");
            password = scanner.nextLine().trim();
        }
        while (true) {
            System.out.println("Confirm password:");
            String confirmPassword = scanner.nextLine().trim();
            if (password.equals(confirmPassword)) {
                return password;
            } else {
                System.out.println("Passwords don't match. Please try again");
                validatePWD(scanner);
            }
        }
    }

    //this method will verify if there's already a nif registered in the Customer SQL table
    private int validateNIF(Scanner scanner) {

        while (true) {
            System.out.println("Enter nif:");
            String input = scanner.nextLine();
            if (input.length() != 9) {
                System.out.println("Invalid NIF. Please try again");
                validateNIF(scanner);
            }
            try {
                String sql = "SELECT * FROM person WHERE nif = '" + input + "'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    System.out.println(NIF_ALREADY_REGISTERED);
                    Login login = new Login();
                    login.run();
                } else {
                    return Integer.parseInt(input);
                }
            } catch (SQLException e) {
                System.out.println("Invalid NIF. Please try again");
            }
        }
    }

    private static String validateDate(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();

            try {
                LocalDate.parse(input); // This will throw an exception if the format is invalid
                return input; // If no exception is thrown, the input is valid
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
            }
        }
    }
}

class Login {
    private static final String SUCCESSFUL_LOGIN = "Login successful";
    private static final String WRONG_PASSWORD = "Wrong password";
    private static final String NIF_NOT_REGISTERED = "NIF is not registered in our System. Please register first";

    public static final String DB_NAME = "projeto_imt";
    //Este port number é o port number que aparece no XAMPP quando voçês dão start
    //para conectar à base de dados e no meu caso é 3306.
    public static final int PORT_NUMBER = 3306;

    private static final String URL = "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + DB_NAME;
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    private Connection connection;

    Scanner scanner;

    public void run() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        boolean isCorrect = false;
        boolean goBack = false;
        scanner = new Scanner(System.in);
        System.out.println("------ Login ------");
        while (!isCorrect || !goBack) {
            System.out.println("Please enter your NIF:");
            String nif = scanner.nextLine().trim();
            System.out.println("Please enter your password:");
            String password = scanner.nextLine().trim();
            String result = authenticateUser(nif, password);
            switch (result) {
                case SUCCESSFUL_LOGIN:
                    System.out.println("Login successful");
                    isCorrect = true;
                    break;
                case WRONG_PASSWORD:
                    System.out.println("Wrong password. Wanna go back? (y/n)");
                    String answer = scanner.nextLine().trim();
                    if (answer.equals("y")) {
                        goBack = true;
                    }
                    break;
                case NIF_NOT_REGISTERED:
                    System.out.println("NIF is not registered in our System. Please register first");
                    break;
            }
        }

        if (isCorrect) {
            Dummy dummy = getDummy(scanner.nextLine().trim());
            if (dummy.data.get("type").equals("employee")) {
                //call Backoffice and send the dummy object as an argument
            } else {
                //call FrontOffice and send the dummy object as an argument
            }
        } else {
            System.out.println("Going back to main menu");
        }
    }

    class Dummy {
        public HashMap<String, String> data = new HashMap();

        public Dummy(String nif, String name, String address, String date) {
            data.put("nif", nif);
            data.put("name", name);
            data.put("address", address);
            data.put("date", date);
        }
    }

    private String authenticateUser(String nif, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE nif = '" + nif + "'");
            //PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE nif =?");
            //ResultSet resultSet = statement.getResultSet();
            //verify if this password is equals to the password in the database
            if (resultSet.next()) {
                //if pwd is correct, return info of the person (nif, name, email, phone)
                if (BCrypt.checkpw(password, resultSet.getString("password")))
                    return SUCCESSFUL_LOGIN;
                else
                    return WRONG_PASSWORD;
            } else
                return NIF_NOT_REGISTERED;


        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //check if the nif is in the Employee SQL table or in the Customer SQL table
    private Dummy getDummy(String nif) {
        Dummy dummy = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE nif = '" + nif + "'");
            dummy = new Dummy(resultSet.getString("nif"), resultSet.getString("name"),
                    resultSet.getString("address"), resultSet.getString("b_date"));
            if (resultSet.next()) {
                dummy.data.put("type", "employee");
                dummy.data.put("access_level", resultSet.getString("access_level"));

            } else {
                dummy.data.put("type", "customer");
                dummy.data.put("driver_license", resultSet.getString("driver_license"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
