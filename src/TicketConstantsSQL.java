public enum TicketConstantsSQL {

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

    public static String getString(TicketConstantsSQL tcs) {

        String s = switch(tcs) {
            case TABLE_TICKET -> "ticket";
            case COLUMN_TICKET_DATE -> "date";
            case COLUMN_TICKET_DRIVER_LICENSE_NUMBER -> "driver_license_number";
            case COLUMN_TICKET_EXPIRY_DATE -> "expiry_plate";
            case COLUMN_TICKET_PLATE -> "plate";
            case COLUMN_TICKET_VALUE -> "value";
            case INSERT_TICKET -> "INSERT INTO " + TicketConstantsSQL.getString(TicketConstantsSQL.TABLE_TICKET) + '('
                    + TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_DATE) + ", " +
                    TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_PLATE) + ", " +
                    TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_DRIVER_LICENSE_NUMBER) + ", " +
                    TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_VALUE) + ", " +
                    TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_EXPIRY_DATE) + ") VALUES(?, ?, ?, ?, ?)";
            case DELETE_TICKET -> "DELETE FROM " + TicketConstantsSQL.getString(TicketConstantsSQL.TABLE_TICKET) + " WHERE " +
                    TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_DRIVER_LICENSE_NUMBER) + " = ? AND " +
                    TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_PLATE) + " = ? ";
            case QUERY_TABLE_TICKET -> "SELECT FROM " + TicketConstantsSQL.getString(TicketConstantsSQL.TABLE_TICKET) + " WHERE " +
                    TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_DRIVER_LICENSE_NUMBER) + " = ? AND " +
                    TicketConstantsSQL.getString(TicketConstantsSQL.COLUMN_TICKET_PLATE) + " = ? ";
            default -> throw new IllegalArgumentException("No such column or operation for vehicle table");
        };
        return s;
    }
}
