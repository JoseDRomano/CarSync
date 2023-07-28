import model.Vehicle;

import java.sql.Date;

public class MainConnection {

    public static void main(String[] args) {


        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        dataSource.queryInsurances().forEach(System.out::println);
        System.out.println("-------------------------------------------");
        dataSource.queryVehicles().forEach(System.out::println);

        dataSource.close();

    }
}
