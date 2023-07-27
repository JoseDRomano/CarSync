public enum VehicleConstantsSQL {

    TABLE_VEHICLE,
    COLUMN_VEHICLE_MODEL,
    COLUMN_VEHICLE_BRAND,
    COLUMN_VEHICLE_COLOR,
    COLUMN_VEHICLE_REGISTRATION_DATE,
    COLUMN_VEHICLE_PLATE,
    COLUMN_VEHICLE_CATEGORY,
    INSERT_VEHICLE,
    DELETE_VEHICLE,
    UPDATE_VEHICLE,
    QUERY_TABLE_VEHICLE,
    COLUMN_VEHICLE_VIN;

    public static String getString(VehicleConstantsSQL vps) {

        String s = switch(vps) {
            case TABLE_VEHICLE -> "vehicle";
            case COLUMN_VEHICLE_BRAND -> "brand";
            case COLUMN_VEHICLE_VIN -> "vin";
            case COLUMN_VEHICLE_COLOR -> "color";
            case COLUMN_VEHICLE_MODEL -> "model";
            case COLUMN_VEHICLE_PLATE -> "plate";
            case COLUMN_VEHICLE_CATEGORY -> "category";
            case INSERT_VEHICLE -> "INSERT INTO " + VehicleConstantsSQL.getString(VehicleConstantsSQL.TABLE_VEHICLE) + '('
                    + VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_MODEL) + ", " +
                    VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_BRAND) + ", " +
                    VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_COLOR) + ", " +
                    VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_PLATE) + ", " +
                    VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_CATEGORY) + ", " +
                    VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_REGISTRATION_DATE) + ", " +
                    VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_VIN) + ") VALUES(?, ?, ?, ?, ?, ?, ?)";
            case DELETE_VEHICLE -> "DELETE FROM " + VehicleConstantsSQL.getString(VehicleConstantsSQL.TABLE_VEHICLE) + " WHERE " +
                    VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_PLATE) + " = ?";
            case QUERY_TABLE_VEHICLE -> " SELECT * FROM " + VehicleConstantsSQL.getString(VehicleConstantsSQL.TABLE_VEHICLE) +
                    " WHERE " + VehicleConstantsSQL.getString(VehicleConstantsSQL.COLUMN_VEHICLE_PLATE) + " = ? ";
            case COLUMN_VEHICLE_REGISTRATION_DATE -> "registration_date";
            default -> throw new IllegalArgumentException("No such column or operation for vehicle table");
        };
        return s;
    }



}
