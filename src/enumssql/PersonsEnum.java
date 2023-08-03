package enumssql;

public enum PersonsEnum {
    // constants for the variables of the table Person
    TABLE_PERSON,
    COLUMN_NIF,
    COLUMN_NAME,
    COLUMN_BIRTH_DATE,
    COLUMN_PWD,
    COLUMN_ADDRESS,
    COLUMN_PERSON_TYPE_EMPLOYEE,
    COLUMN_PERSON_TYPE_CUSTOMER,

    //contants for the varibles of the table Customer
    TABLE_CUSTOMER,
    COLUMN_CUSTOMER_DRIVER_LICENSE,
    COLUMN_CUSTOMER_LICENSE_TYPE,
    COLUMN_CUSTOMER_START_DATE,
    COLUMN_CUSTOMER_EXPIRATION_DATE,
    QUERY_TABLE_CUSTOMER,

    //Constants for the variables of the table Employee
    TABLE_EMPLOYEE,
    COLUMN_EMPLOYEE_ACCESS_LEVEL, INSERT_INTO_PERSON, INSERT_INTO_EMPLOYEE, INSERT_INTO_CUSTOMER;

    //Method to get the string of the enum
    public static String getString(PersonsEnum pes) {
        String s = switch (pes) {
            case TABLE_PERSON -> "person";
            case COLUMN_NIF -> "nif";
            case COLUMN_NAME -> "name";
            case COLUMN_BIRTH_DATE -> "birth_date";
            case COLUMN_PWD -> "pwd";
            case COLUMN_ADDRESS -> "address";
            case TABLE_CUSTOMER -> "customer";
            case COLUMN_PERSON_TYPE_CUSTOMER -> "CUSTOMER";
            case COLUMN_CUSTOMER_DRIVER_LICENSE -> "driver_license_number";
            case COLUMN_CUSTOMER_LICENSE_TYPE -> "license_type";
            case COLUMN_CUSTOMER_START_DATE -> "start_date";
            case COLUMN_CUSTOMER_EXPIRATION_DATE -> "expiration_date";
            case TABLE_EMPLOYEE -> "employee";
            case COLUMN_PERSON_TYPE_EMPLOYEE -> "EMPLOYEE";
            case COLUMN_EMPLOYEE_ACCESS_LEVEL -> "access_level";
            case QUERY_TABLE_CUSTOMER -> "SELECT * FROM " + getString(TABLE_CUSTOMER);

            case INSERT_INTO_PERSON -> "INSERT INTO " + getString(PersonsEnum.TABLE_PERSON) + '('
                    + getString(PersonsEnum.COLUMN_NIF) + ", " +
                    getString(PersonsEnum.COLUMN_NAME) + ", " +
                    getString(PersonsEnum.COLUMN_BIRTH_DATE) + ", " +
                    getString(PersonsEnum.COLUMN_PWD) + ", " +
                    getString(PersonsEnum.COLUMN_ADDRESS) + ") VALUES(?, ?, ?, ?, ?)";

            case INSERT_INTO_EMPLOYEE -> "INSERT INTO " + getString(PersonsEnum.TABLE_EMPLOYEE) + '('
                    + getString(PersonsEnum.COLUMN_NIF) + ", " +
                    getString(PersonsEnum.COLUMN_NAME) + ", " +
                    getString(PersonsEnum.COLUMN_BIRTH_DATE) + ", " +
                    getString(PersonsEnum.COLUMN_PWD) + ", " +
                    getString(PersonsEnum.COLUMN_ADDRESS) + ", " +
                    getString(PersonsEnum.COLUMN_EMPLOYEE_ACCESS_LEVEL) + ") VALUES(?, ?, ?, ?, ?, ?)";
            case INSERT_INTO_CUSTOMER -> "INSERT INTO " + getString(PersonsEnum.TABLE_CUSTOMER) + '('
                    + getString(PersonsEnum.COLUMN_NIF) + ", " +
                    getString(PersonsEnum.COLUMN_NAME) + ", " +
                    getString(PersonsEnum.COLUMN_BIRTH_DATE) + ", " +
                    getString(PersonsEnum.COLUMN_PWD) + ", " +
                    getString(PersonsEnum.COLUMN_ADDRESS) + ", " +
                    getString(PersonsEnum.COLUMN_CUSTOMER_DRIVER_LICENSE) + ", " +
                    getString(PersonsEnum.COLUMN_CUSTOMER_LICENSE_TYPE) + ", " +
                    getString(PersonsEnum.COLUMN_CUSTOMER_START_DATE) + ", " +
                    getString(PersonsEnum.COLUMN_CUSTOMER_EXPIRATION_DATE) + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";


            default -> throw new IllegalArgumentException("No such column or operation for person table");

        };
        return s;
    }


}
