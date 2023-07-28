package model;

import java.sql.Date;

public class Ticket {

    private int driver_license_number;
    private String plate;
    private Date date;
    private Date expiry_date;
    private double value;
    private String reason;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(int reason) {
        String s = switch (reason) {
            case 1 -> this.reason = "Speeding";
            case 2 -> this.reason = "Red light";
            case 3 -> this.reason = "Illegal parking";
            case 4 -> this.reason = "Reckless driving";
            case 5 -> this.reason = "Driving Under the Influence";
            default -> throw new IllegalStateException("No such reason to be fined");
        };
    }

    public int getDriver_license_number() {
        return driver_license_number;
    }

    public void setDriver_license_number(int driver_license_number) {
        this.driver_license_number = driver_license_number;
    }

    public String toString() {
        return "Ticket info: " + "\n" +
                "- driver_license_number: " + driver_license_number + "\n" +
                "- plate: " + plate + "\n + " +
                "- date: " + date + "\n" + "- expiry_date: " + expiry_date + "\n" + "- value: " + value + "\n"
                + "- reason: " + reason;
    }
}
