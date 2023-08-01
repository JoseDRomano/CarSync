package enumssql;

public enum InsuranceEnum {

    TABLE_INSURANCE,
    COLUMN_INSURANCE_POLICY,
    COLUMN_INSURANCE_EXPIRY_DATE,
    COLUMN_INSURANCE_COMPANY,
    COLUMN_INSURANCE_START_DATE,
    COLUMN_INSURANCE_EXTRA_CATEGORY,
    COLUMN_INSURANCE_PLATE,
    INSERT_INSURANCE,
    RENEW_INSURANCE,
    QUERY_TABLE_INSURANCE,
    QUERY_TABLE_INSURANCE_BY_POLICY,
    DELETE_INSURANCE;

    public static String getString(InsuranceEnum isq) {

        String s = switch (isq) {
            case TABLE_INSURANCE -> "insurance";
            case COLUMN_INSURANCE_PLATE -> "plate";
            case COLUMN_INSURANCE_COMPANY -> "company";
            case COLUMN_INSURANCE_EXPIRY_DATE -> "expiry_date";
            case COLUMN_INSURANCE_POLICY -> "policy";
            case COLUMN_INSURANCE_EXTRA_CATEGORY -> "extra_category";
            case COLUMN_INSURANCE_START_DATE -> "start_date";
            case INSERT_INSURANCE ->
                    "INSERT INTO " + getString(InsuranceEnum.TABLE_INSURANCE) + '('
                            + getString(InsuranceEnum.COLUMN_INSURANCE_COMPANY) + ", " +
                            getString(InsuranceEnum.COLUMN_INSURANCE_PLATE) + ", " +
                            getString(InsuranceEnum.COLUMN_INSURANCE_EXPIRY_DATE) + ", " +
                            getString(InsuranceEnum.COLUMN_INSURANCE_POLICY) + ", " +
                            getString(InsuranceEnum.COLUMN_INSURANCE_EXTRA_CATEGORY) + ", " +
                            getString(InsuranceEnum.COLUMN_INSURANCE_START_DATE) + ") VALUES(?, ?, ?, ?, ?, ?)";
            case DELETE_INSURANCE ->
                    "DELETE FROM " + getString(InsuranceEnum.TABLE_INSURANCE) + " WHERE " +
                            getString(InsuranceEnum.COLUMN_INSURANCE_POLICY) + " = ? ";
            case QUERY_TABLE_INSURANCE ->
                    " SELECT * FROM " + getString(InsuranceEnum.TABLE_INSURANCE);
            case QUERY_TABLE_INSURANCE_BY_POLICY -> " SELECT * FROM " + getString(InsuranceEnum.TABLE_INSURANCE) +
                    " WHERE " + getString(InsuranceEnum.COLUMN_INSURANCE_POLICY) + " = ? ";
            case RENEW_INSURANCE -> "UPDATE " + getString(InsuranceEnum.TABLE_INSURANCE) + " SET " +
                    getString(InsuranceEnum.COLUMN_INSURANCE_START_DATE) + " = ?, " +
                    getString(InsuranceEnum.COLUMN_INSURANCE_EXPIRY_DATE) + " = ?, " +
                    getString(InsuranceEnum.COLUMN_INSURANCE_EXTRA_CATEGORY) + " = ?, " +
                    getString(InsuranceEnum.COLUMN_INSURANCE_COMPANY) + " = ? WHERE " +
                    getString(InsuranceEnum.COLUMN_INSURANCE_POLICY) + " = ?";
            default -> throw new IllegalArgumentException("No such column or operation for vehicle table");
        };
        return s;
    }


}
