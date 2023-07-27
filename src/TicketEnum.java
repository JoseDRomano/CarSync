public enum TicketEnum {

    TABLE_TICKET,
    COLUMN_TICKET_DRIVER_LICENSE_NUMBER,
    COLUMN_TICKET_PLATE,
    COLUMN_TICKET_DATE,
    COLUMN_TICKET_EXPIRY_DATE,
    COLUMN_TICKET_VALUE,
    INSERT_TICKET,
    DELETE_TICKET,
    UPDATE_VEHICLE,
    QUERY_TABLE_TICKET;

    public static String getString(TicketEnum tcs) {

        String s = switch (tcs) {
            case TABLE_TICKET -> "ticket";
            case COLUMN_TICKET_DATE -> "date";
            case COLUMN_TICKET_DRIVER_LICENSE_NUMBER -> "driver_license_number";
            case COLUMN_TICKET_EXPIRY_DATE -> "expiry_plate";
            case COLUMN_TICKET_PLATE -> "plate";
            case COLUMN_TICKET_VALUE -> "value";
            case INSERT_TICKET -> "INSERT INTO " + getString(TABLE_TICKET) + '('
                    + getString(COLUMN_TICKET_DATE) + ", " +
                    getString(COLUMN_TICKET_PLATE) + ", " +
                    getString(COLUMN_TICKET_DRIVER_LICENSE_NUMBER) + ", " +
                    getString(COLUMN_TICKET_VALUE) + ", " +
                    getString(COLUMN_TICKET_EXPIRY_DATE) + ") VALUES(?, ?, ?, ?, ?)";
//            case INSERT_TICKET -> "INSERT INTO " + TicketEnum.getString(TicketEnum.TABLE_TICKET) + '('
//                    + TicketEnum.getString(TicketEnum.COLUMN_TICKET_DATE) + ", " +
//                    TicketEnum.getString(TicketEnum.COLUMN_TICKET_PLATE) + ", " +
//                    TicketEnum.getString(TicketEnum.COLUMN_TICKET_DRIVER_LICENSE_NUMBER) + ", " +
//                    TicketEnum.getString(TicketEnum.COLUMN_TICKET_VALUE) + ", " +
//                    TicketEnum.getString(TicketEnum.COLUMN_TICKET_EXPIRY_DATE) + ") VALUES(?, ?, ?, ?, ?)";
            case DELETE_TICKET -> "DELETE FROM " + TicketEnum.getString(TicketEnum.TABLE_TICKET) + " WHERE " +
                    TicketEnum.getString(TicketEnum.COLUMN_TICKET_DRIVER_LICENSE_NUMBER) + " = ? AND " +
                    TicketEnum.getString(TicketEnum.COLUMN_TICKET_PLATE) + " = ? ";
            case QUERY_TABLE_TICKET -> "SELECT FROM " + TicketEnum.getString(TicketEnum.TABLE_TICKET) + " WHERE " +
                    TicketEnum.getString(TicketEnum.COLUMN_TICKET_DRIVER_LICENSE_NUMBER) + " = ? AND " +
                    TicketEnum.getString(TicketEnum.COLUMN_TICKET_PLATE) + " = ? ";
            default -> throw new IllegalArgumentException("No such column or operation for vehicle table");
        };
        return s;
    }
}
