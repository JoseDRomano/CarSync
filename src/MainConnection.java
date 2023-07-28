import model.Vehicle;

import java.sql.Date;

public class MainConnection {

    public static void main(String[] args) {


        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        dataSource.queryVehicles().forEach(System.out::println);
        System.out.println("Inserting a new vehicle");
        dataSource.insertVehicle("SS-7H-U6", "76343282", "GREEN",
                "Ford", "S9", new Date(2000 - 1900, 04 - 1, 27),
                6);
        System.out.println("-------------------------------------------");
        dataSource.queryVehicles().forEach(System.out::println);
        dataSource.close();

    }
}
