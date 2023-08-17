package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketFileManagment {

    private List<TicketFormat> ticketList;
    private static Path filePath = Path.of("Tickets.txt");

    TicketFileManagment() {
        this.ticketList = new ArrayList<>();
        readTicketsFromFile();
    }
     class TicketFormat {
        private final int ticketID;
        private final String ticketType;
        private boolean ticketStatus;
        private String ticketDate;
        List<String> ticketInfo;
        private final int nif;


         TicketFormat(int ticketID, String ticketType, boolean ticketStatus, String ticketDate, List<String> ticketInfo, int nif) {
            this.ticketID = ticketID;
            this.ticketType = ticketType;
            this.ticketStatus = ticketStatus;
            this.ticketDate = ticketDate;
            this.ticketInfo = ticketInfo;
            this.nif = nif;
        }

        @Override
        public String toString() {
            String s = """
                    Ticket ID: %d
                    - Type: %s
                    - Status: %s
                    - Date: %s
                    - Info: %s
                    Customer NIF: %d
                    """;
            return String.format(s, ticketID, ticketType, ticketStatus, ticketDate, ticketInfo, nif);
        }

    }

    public static boolean readTicketsFromFile() {
         boolean result = false;
         try {
             Files.readAllLines(filePath).forEach(System.out::println);

         } catch (IOException e) {
             System.out.println("Error reading from file: " + e.getMessage());
             e.printStackTrace();
         }
         return result;

    }


    public static boolean writeTicketToFile(TicketFormat tF) {
        //Should get data from file and see if the ticket provided doesn't already exist
        boolean result = false;
        try {
            Files.write(filePath, tF.toString().getBytes(), StandardOpenOption.CREATE);
            result = true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteTicketFromFile(TicketFormat tF) {
        Path filePath = Path.of("Tickets.txt");
        boolean result = false;
        try {
            Files.delete(filePath);
            result = true;
        } catch (IOException e) {
            System.out.println("Error deleting file: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateTicketFromFile(TicketFormat tF) {
        Path filePath = Path.of("Tickets.txt");
        boolean result = false;
        try {
            Files.write(filePath, tF.toString().getBytes(), StandardOpenOption.APPEND);
            result = true;
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
