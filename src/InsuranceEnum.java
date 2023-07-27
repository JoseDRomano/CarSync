public enum InsuranceEnum {

    TABLE_INSURANCE,
    COLUMN_POLICY,
    COLUMN_EXPIRY_DATE,
    COLUMN_COMPANY,
    COLUMN_START_DATE,
    COLUMN_EXTRA_CATEGORY,
    COLUMN_PLATE,
    INSERT_INSURANCE,
    UPDATE_INSURANCE,
    QUERY_TABLE_INSURANCE,
    DELETE_INSURANCE;


    public static String getString(InsuranceEnum isq) {

        String s = switch (isq) {
            case TABLE_INSURANCE -> "insurance";
            case COLUMN_PLATE -> "plate";
            case COLUMN_COMPANY -> "company";
            case COLUMN_EXPIRY_DATE -> "expiry_date";
            case COLUMN_POLICY -> "policy";
            case COLUMN_EXTRA_CATEGORY -> "category";
            case COLUMN_START_DATE -> "start_date";
            case INSERT_INSURANCE ->
                    "INSERT INTO " + InsuranceEnum.getString(InsuranceEnum.TABLE_INSURANCE) + '('
                            + InsuranceEnum.getString(InsuranceEnum.COLUMN_COMPANY) + ", " +
                            InsuranceEnum.getString(InsuranceEnum.COLUMN_PLATE) + ", " +
                            InsuranceEnum.getString(InsuranceEnum.COLUMN_EXPIRY_DATE) + ", " +
                            InsuranceEnum.getString(InsuranceEnum.COLUMN_POLICY) + ", " +
                            InsuranceEnum.getString(InsuranceEnum.COLUMN_EXTRA_CATEGORY) + ", " +
                            InsuranceEnum.getString(InsuranceEnum.COLUMN_START_DATE) + ") VALUES(?, ?, ?, ?, ?, ?)";
            case DELETE_INSURANCE ->
                    "DELETE FROM " + InsuranceEnum.getString(InsuranceEnum.TABLE_INSURANCE) + " WHERE " +
                            InsuranceEnum.getString(InsuranceEnum.COLUMN_POLICY) + " = ? ";
            case QUERY_TABLE_INSURANCE ->
                    " SELECT * FROM " + InsuranceEnum.getString(InsuranceEnum.TABLE_INSURANCE) +
                            " WHERE " + InsuranceEnum.getString(InsuranceEnum.COLUMN_POLICY) + " = ? ";
            default -> throw new IllegalArgumentException("No such column or operation for vehicle table");
        };
        return s;
    }


}
