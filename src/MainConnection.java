public class MainConnection {

    public static void main(String[] args) {


        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        dataSource.queryVehicles().forEach(System.out::println);

        dataSource.close();

        /*String plate = "AA-AA-AAA";
        if(plate.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$")) {
            System.out.println("True");
        }*/


    }
}
