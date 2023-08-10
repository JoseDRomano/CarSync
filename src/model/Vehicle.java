package model;

import java.sql.Date;
import java.util.Comparator;
import java.util.HashMap;

public class Vehicle implements Comparable<Vehicle>{

    private String plate;
    private String vin;
    private String color;
    private String brand;
    private String model;
    private Date registrationDate;
    private int nif;
    private String category;


    public static class StringPlateComparator implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o1.getPlate().compareTo(o2.getPlate());
        }

        @Override
        public Comparator<Vehicle> reversed() {
            return Comparator.super.reversed();
        }
    }

    public static class RegistrationDateComparator implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o1.getregistrationDate().compareTo(o2.getregistrationDate());
        }

        @Override
        public Comparator<Vehicle> reversed() {
            return Comparator.super.reversed();
        }
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

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        if(plate.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$")) {
            this.plate = plate;
            return;
        }
        throw new IllegalArgumentException("Plate must have the following format: XX-XX-XX");
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        if(vin.matches("^([0-9A-Z]{17})$")) {
            this.vin = vin;
            return;
        }
       throw new IllegalArgumentException("VIN must have 17 digits");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getregistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setCategory(int categoryNumber) {
       String s = switch (categoryNumber) {
            case 1 -> this.category = "Light Commercial Vehicle";
            case 2 -> this.category = "Light Passenger Vehicle";
            case 3 -> this.category = "Heavy-duty Passenger Vehicle";
            case 4 -> this.category = "Heavy-duty Goods Vehicle";
            case 5 -> this.category = "Motorcycle";
            case 6 -> this.category = "Moped";
           default -> throw new IllegalStateException("No such category");
       };
    }


    @Override
    public String toString() {
        return "Vehicle info: " + "\n" + "- NIF: "+ nif + "\n" + "- Plate: " + plate + "\n" + "- Category: " +
                category + "\n" + "- Vin: " + vin + "\n" + "- Registration Date: "
                + registrationDate + "\n" + "- Color: " + color + "\n"
                + "- Model: " + model + "\n" + "- Brand: " + brand + "\n";
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.getNif() - o.getNif();
    }

}
