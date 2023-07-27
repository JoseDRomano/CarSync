public enum InsuranceConstansSQL {

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


    public static String getString(InsuranceConstansSQL isq) {

        String s = switch(isq) {
            case TABLE_INSURANCE -> "insurance";
            case COLUMN_PLATE-> "plate";
            case COLUMN_COMPANY -> "company";
            case COLUMN_EXPIRY_DATE -> "expiry_date";
            case COLUMN_POLICY -> "policy";
            case COLUMN_EXTRA_CATEGORY -> "category";
            case COLUMN_START_DATE -> "start_date";
            case INSERT_INSURANCE -> "INSERT INTO " + InsuranceConstansSQL.getString(InsuranceConstansSQL.TABLE_INSURANCE) + '('
                    + InsuranceConstansSQL.getString(InsuranceConstansSQL.COLUMN_COMPANY) + ", " +
                    InsuranceConstansSQL.getString(InsuranceConstansSQL.COLUMN_PLATE) + ", " +
                    InsuranceConstansSQL.getString(InsuranceConstansSQL.COLUMN_EXPIRY_DATE) + ", " +
                    InsuranceConstansSQL.getString(InsuranceConstansSQL.COLUMN_POLICY) + ", " +
                    InsuranceConstansSQL.getString(InsuranceConstansSQL.COLUMN_EXTRA_CATEGORY) + ", " +
                    InsuranceConstansSQL.getString(InsuranceConstansSQL.COLUMN_START_DATE) + ") VALUES(?, ?, ?, ?, ?, ?)";
            case DELETE_INSURANCE -> "DELETE FROM " + InsuranceConstansSQL.getString(InsuranceConstansSQL.TABLE_INSURANCE) + " WHERE " +
                    InsuranceConstansSQL.getString(InsuranceConstansSQL.COLUMN_POLICY) + " = ? ";
            case QUERY_TABLE_INSURANCE -> " SELECT * FROM " + InsuranceConstansSQL.getString(InsuranceConstansSQL.TABLE_INSURANCE) +
                    " WHERE " + InsuranceConstansSQL.getString(InsuranceConstansSQL.COLUMN_POLICY) + " = ? ";
            default -> throw new IllegalArgumentException("No such column or operation for vehicle table");
        };
        return s;
    }


}
