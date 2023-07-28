public enum VehicleEnum {

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
    QUERY_TABLE_VEHICLE_BY_PLATE,
    QUERY_TABLE_VEHICLE,
    COLUMN_VEHICLE_VIN;

    public static String getString(VehicleEnum vps) {

        String s = switch(vps) {
            case TABLE_VEHICLE -> "vehicle";
            case COLUMN_VEHICLE_BRAND -> "brand";
            case COLUMN_VEHICLE_VIN -> "vin";
            case COLUMN_VEHICLE_COLOR -> "color";
            case COLUMN_VEHICLE_MODEL -> "model";
            case COLUMN_VEHICLE_PLATE -> "plate";
            case COLUMN_VEHICLE_CATEGORY -> "category";
            case INSERT_VEHICLE -> "INSERT INTO " + VehicleEnum.getString(VehicleEnum.TABLE_VEHICLE) + '('
                    + VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_MODEL) + ", " +
                    VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_BRAND) + ", " +
                    VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_COLOR) + ", " +
                    VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_PLATE) + ", " +
                    VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_CATEGORY) + ", " +
                    VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_REGISTRATION_DATE) + ", " +
                    VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_VIN) + ") VALUES(?, ?, ?, ?, ?, ?, ?)";
            case DELETE_VEHICLE -> "DELETE FROM " + VehicleEnum.getString(VehicleEnum.TABLE_VEHICLE) + " WHERE " +
                    VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_PLATE) + " = ?";
            case QUERY_TABLE_VEHICLE_BY_PLATE -> " SELECT * FROM " + VehicleEnum.getString(VehicleEnum.TABLE_VEHICLE) +
                    " WHERE " + VehicleEnum.getString(VehicleEnum.COLUMN_VEHICLE_PLATE) + " = ? ";
            case QUERY_TABLE_VEHICLE -> " SELECT * FROM " + VehicleEnum.getString(VehicleEnum.TABLE_VEHICLE);
            case COLUMN_VEHICLE_REGISTRATION_DATE -> "registration_date";
            default -> throw new IllegalArgumentException("No such column or operation for vehicle table");
        };
        return s;
    }



}