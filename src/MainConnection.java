import model.Vehicle;

import java.sql.Date;

public class MainConnection {

    public static void main(String[] args) {


        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        //Todos os metodos listados abaixo funcionam e tenta correr para veres os resultados
        dataSource.queryTickets().forEach(System.out::println);
        dataSource.queryVehicles().forEach(System.out::println);
        dataSource.queryInsurances().forEach(System.out::println);
        dataSource.close();

    }
}
