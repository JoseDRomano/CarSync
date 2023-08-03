import employeeacess.DataSource;

import java.sql.Date;

public class MainConnection {

    public static void main(String[] args) {

        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        /*//Todos os metodos abaixo funcionam
        dataSource.queryTickets().forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        dataSource.queryVehicles().forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        dataSource.queryInsurances().forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        dataSource.queryCustomers().forEach(System.out::println);*/

//        dataSource.insertVehicle("AA-00-00", "213123AASFSFD3", "BLACK", "Audi", "A4", Date.valueOf("2003-12-2"), 2, 2000019888);
//        dataSource.insertTicket(2000019888, "AA-00-00",
//                Date.valueOf("2021-12-2"), 4, 190.65,  Date.valueOf("2024-12-2"));

//        dataSource.insertInsurance(2000019888, "AA-00-00",
//                Date.valueOf("2021-12-2"), 2, Date.valueOf("2024-12-2"), "Allianz", 2000019888);
        dataSource.queryInsurances().forEach(System.out::println);
        dataSource.close();

    }
}
