

import employeeacess.BackOffice;
import employeeacess.DataSource;
import model.Employee;

import java.sql.Date;

public class MainConnection {

    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.setAccess_level(2);
        e1.setName("Gonçalo Ramos");
        BackOffice.startBackOffice(e1);
    }

}
