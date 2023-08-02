import employeeacess.DataSource;

public class MainConnection {

    public static void main(String[] args) {


        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        //Todos os metodos abaixo funcionam


        dataSource.queryTickets().forEach(System.out::println);
        dataSource.queryVehicles().forEach(System.out::println);
        dataSource.queryInsurances().forEach(System.out::println);




        dataSource.close();

    }
}
