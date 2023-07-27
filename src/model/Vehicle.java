package model;

import java.sql.Date;

public class Vehicle {

    private String plate;
    private String vin;
    private String color;
    private String brand;
    private String model;
    private Date registration_date;

    public enum VehicleCategory {
        Light_Commercial_Vehicle,
        Light_Passenger_Vehicle,
        Heavy_duty_Passenger_Vehicle,
        Heavy_duty_Goods_Vehicle,
        Motorcycle,
        Moped;
    }

    private VehicleCategory category;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        if(plate.matches("^([0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2}[\\-]{1}[0-9A-Z]{2})$")) {
            this.plate = plate;
        }
        System.out.println("Incorrect plate number");
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(String category) {
        switch(category) {
            case "Light Commercial Vehicle" -> this.category = VehicleCategory.Light_Commercial_Vehicle;
            case "Light Passenger Vehicle" -> this.category = VehicleCategory.Light_Passenger_Vehicle;
            case "Heavy-duty Passenger Vehicle" -> this.category = VehicleCategory.Heavy_duty_Passenger_Vehicle;
            case "Heavy-duty Goods Vehicle" -> this.category = VehicleCategory.Heavy_duty_Goods_Vehicle;
            case "Motorcycle" -> this.category = VehicleCategory.Motorcycle;
            case "Moped" -> this.category = VehicleCategory.Moped;
            default -> System.out.println("No such category");
        }
    }


    @Override
    public String toString() {
        return "model.Vehicle info: " + "\n" + "- Plate: " + plate + "\n" + "- Category: " +
                category + "\n" + "- Vin: " + vin + "\n" + "- Registration Date: "
                + registration_date + "\n" + "- Color: " + color + "\n"
                + "- Model: " + model + "\n" + "- Brand: " + brand;
    }

}
