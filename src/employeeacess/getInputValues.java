package employeeacess;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public interface getInputValues {

    default Date getDate(Scanner scan) {
        Date sqlDate = null;
        boolean validInput = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        do {
            String input = scan.nextLine().trim();
            try {
                LocalDate localDate = LocalDate.parse(input, formatter);
                sqlDate = Date.valueOf(localDate);
                validInput = true;
            } catch (DateTimeParseException e) {
//                logger.error("Invalid date input: " + input, e);
                System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
            }
        } while (!validInput);

        return sqlDate;
    }

    default java.sql.Date getBirthDate(Scanner scan) {
        java.sql.Date bDate = null;
        boolean validInput = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        do {
            String input = scan.nextLine().trim();
            try {
                LocalDate localDate = LocalDate.parse(input, formatter);
                LocalDate today = LocalDate.now();

                if (Period.between(localDate, today).getYears() >= 18) {
                    bDate = java.sql.Date.valueOf(localDate);
                    validInput = true;
                } else {
                    System.out.println("The customer must be at least 18 years old. Please enter a valid date.");
                }
            } catch (DateTimeParseException e) {
//                logger.error("Invalid date input: " + input, e);
                System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
            }
        } while (!validInput);

        return bDate;
    }

    default int getNIF(Scanner scan) {
        int nif = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{9}")) {
                nif = Integer.parseInt(s);
                validInput = true;
            } else {
//                logger.error("Invalid NIF: " + s);
                System.out.println("Invalid NIF. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return nif;
    }


    default int getDriverLicense(Scanner scan) {
        int driverLicense = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{8}")) {
                driverLicense = Integer.parseInt(s);
                validInput = true;
            } else {
//                logger.error("Invalid driver license number: " + s);
                System.out.println("Invalid driver license number. Please enter a 8-digit number.");
            }
        } while (!validInput);

        return driverLicense;
    }


    default int getPolicy(Scanner scan) {
        int policy = 0;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("\\d{9}")) {
                policy = Integer.parseInt(s);
                validInput = true;
            } else {
//                logger.error("Invalid policy number: " + s);
                System.out.println("Invalid policy number. Please enter a 9-digit number.");
            }
        } while (!validInput);

        return policy;
    }

    default String getPlate(Scanner scan) {
        String plate = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$")) {
                plate = s;
                validInput = true;
            } else {
//                logger.error("Invalid plate: " + s);
                System.out.println("Invalid plate. Please enter a XX-XX-XX format.");
            }
        } while (!validInput);

        return plate;
    }

    default String getString(Scanner scan) {
        String str = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^(?=.*[a-zA-Z0-9].*[a-zA-Z0-9])[\\w\\s]{2,}$|^[a-zA-Z0-9]+\\s[a-zA-Z0-9]+$")) {
                str = s;
                validInput = true;
            } else {
//                logger.error("Invalid word: " + s);
                System.out.println("Invalid word. Please make sure there are no special letters and at least 2 letters");
            }
        } while (!validInput);

        return str;
    }


    default String getVIN(Scanner scan) {
        String str = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^[A-Z0-9]{17}$")) {
                str = s;
                validInput = true;
            } else {
//                logger.error("Invalid VIN: " + s);
                System.out.println("Invalid VIN. Please enter a 17 digit value.");
            }
        } while (!validInput);

        return str;
    }

    default double getDouble(Scanner scan) {
        double amount = 0.00;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^\\d+\\.\\d{2}$|^\\d+\\.00$")) {
                amount = Double.parseDouble(s);
                validInput = true;
            } else {
//                logger.error("Invalid amount: " + s);
                System.out.println("Invalid amount. Please enter a value that ends with a decimal part or .00 .");
            }
        } while (!validInput);

        return amount;
    }

    default int printValues(Scanner scan, Logger logger, Object... vs) {
        boolean validInput = false;
        int decision = -1;
        int i = 1;
        System.out.println("The values entered are: ");
        for (Object o : vs) {
            System.out.println("Value " + i + ": " + o.toString());
            i++;
        }
        System.out.println("If values are correct, press Y(y) to continue, N(n) to cancel.");
        do {
            String s = scan.nextLine().trim();
            if (s.compareToIgnoreCase("Y") == 0) {
                System.out.println("Values confirmed.");
                decision = 1;
                validInput = true;
            } else if (s.compareToIgnoreCase("N") == 0) {
                System.out.println("Procedure cancelled.");
                validInput = true;
            } else {
                System.out.println("Invalid input. Please try again.");
//                logger.warn("Invalid input received.");
            }
        } while (!validInput);

        return decision;
    }


    default String getPassword(Scanner scan) {
        String password = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{6,10}$).*")) {
                password = s;
                validInput = true;
            } else {
//                logger.error("Invalid amount: " + s);
                System.out.println("""
                        Invalid password. 
                        Please make sure the password contains at least:
                        - 1 uppercase letter
                        - 1 lowercase letter
                        - 1 number
                        - 1 special character
                        - 6 to 10 characters""");
            }
        } while (!validInput);

        return password;
    }

    default int getInteger(Scanner scan) {
//        logger.info("Getting integer input...");
        int value = 0;
        boolean validInput = false;
        do {
            String s = scan.nextLine().trim();
            if (s.matches("[0-9]+")) {
                value = Integer.parseInt(s);
                validInput = true;
            } else {
                System.out.println("Invalid value. Please enter a number.");
//                logger.warn("Invalid value entered. Please enter a number.");
            }
        } while (!validInput);

//        logger.info("Integer input obtained: " + value);
        return value;
    }

    default void displayList(List<?> objects, int rowsPerPage) {
//        logger.info("Displaying list...");
        Scanner scanner = new Scanner(System.in);
        int currentPosition = 0; // Always start from the first position
        int totalObjects = objects.size();

        int pages = (int) Math.ceil((double) totalObjects / rowsPerPage);
        int aux = 1;

        while (currentPosition < totalObjects) {
            System.out.println("Displaying from page " + aux + " of " + pages + ":");

            for (int i = currentPosition; i < Math.min(currentPosition + rowsPerPage, totalObjects); i++) {
                System.out.println(objects.get(i));
            }

            System.out.println("Displaying from page " + aux + " of " + pages + ":");
            System.out.print("\nOptions: (C)ontinue, (P)revious, (B)ack to menu: ");
            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "C" -> {
                    aux++;
                    currentPosition += rowsPerPage;
                }
                case "P" -> {
                    aux--;
                    aux = Math.max(1, aux);
                    currentPosition = Math.max(0, currentPosition - rowsPerPage);
                }
                case "B" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid input. Please try again.");
//                    logger.warn("Invalid input received.");
                }
            }
        }
//        logger.info("Displaying list completed.");
    }

    default String getEmail(Scanner scan) {
        String email = null;
        boolean validInput = false;

        do {
            String s = scan.nextLine().trim();
            if (s.matches("^[a-zA-Z0-9]{2,}@([a-zA-Z0-9]{2,}\\.com|[a-zA-Z0-9]{2,}\\.pt)$")) {
                email = s;
                validInput = true;
            } else {
//                logger.error("Invalid email: " + s);
                System.out.println("Invalid email. Please enter a valid email that ends with .com or .pt .");
            }
        } while (!validInput);

        return email;
    }

}
