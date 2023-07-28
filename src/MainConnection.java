import model.Vehicle;

import java.sql.Date;

public class MainConnection {

    public static void main(String[] args) {


        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        /*dataSource.queryVehicles().forEach(System.out::println);
        System.out.println("Inserting a new vehicle");*/
        dataSource.insertVehicle("AD-98-00", "47835565", "Grey",
                "Fiat", "Punto", new Date(2009, 04, 27),
                Vehicle.VehicleCategory.Light_Commercial_Vehicle);
       /* System.out.println("-------------------------------------------");
        dataSource.queryVehicles().forEach(System.out::println);*/

        dataSource.close();


    }
}
