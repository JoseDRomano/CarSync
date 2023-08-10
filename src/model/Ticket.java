package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;

public class Ticket implements Comparable<Ticket> {

    private int nif;
    private String plate;
    private Date date;
    private Date expiry_date;
    private double value;
    private String reason;
    private boolean isPaid = false;

    public static class StringPlateComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getPlate().compareTo(o2.getPlate());
        }

        @Override
        public Comparator<Ticket> reversed() {
            return Comparator.super.reversed();
        }
    }

    public static class RegistrationDateComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getDate().compareTo(o2.getDate());
        }

        @Override
        public Comparator<Ticket> reversed() {
            return Comparator.super.reversed();
        }
    }

    public static class ExpirationateComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getExpiry_date().compareTo(o2.getExpiry_date());
        }

        @Override
        public Comparator<Ticket> reversed() {
            return Comparator.super.reversed();
        }
    }

    public boolean isPaid() {
        boolean b = isPaid;
        return b;
    }

    public boolean setPaid(int paid) {
        if(paid == 1) {
            isPaid = true;
            return true;
        }
        return false;
    }

    public boolean payTicket(double value) {
        if(isPaid == true) {
            System.out.println("Ticket already paid");
            return false;
        }
        if(value >= this.value) {
            setPaid(1);
            return true;
        }
        System.out.println("Value not enough to pay ticket");
        return false;
    }
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
        this.value = value <= 0 ? 0 : value;
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
            default -> throw new IllegalStateException("No such reason");
        };
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        if(nif < 200000000  || nif > 299999999) {
            throw new IllegalArgumentException("NIF must have 9 digits");
        }
        this.nif = nif;
    }

    public String toString() {
        String s = isPaid ? "Paid" : "Not paid";
        return "Ticket info: " + "\n" +
                "- Driver's NIF: " + nif + "\n" +
                "- Car plate: " + plate + "\n" +
                "- TIcket date: " + date + "\n"
                + "- Ticket expiry date: " + expiry_date + "\n"
                + "- Value: " + value + "\n"
                + "- Ticket reason: " + reason + "\n" +
                "- Ticket status: " + s + "\n";
    }

    @Override
    public int compareTo(Ticket o) {
        return this.nif - o.nif;
    }



}
