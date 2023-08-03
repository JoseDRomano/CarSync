package model;

import java.util.Date;
import util.LogUtil;

public class Customer extends Person {
    private int driverLicenseNum;
    private String licenseType;
    private Date startingDate;
    private Date expDate;


    public int getDriverLicenseNum() {return driverLicenseNum;}

    public void setDriverLicenseNum(int driverLicenseNum) {
//        LogUtil.info("Setting Driver License Number: " + driverLicenseNum);
        this.driverLicenseNum = driverLicenseNum;
    }


    public String getLicenseType() {
        return licenseType;
    }

    public int getLicenseTypeNumber() {
        int licenseTypen = switch (this.licenseType) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            case "D" -> 4;
            default -> throw new IllegalStateException("No such category");
        };
        return licenseTypen;
    }

    public void setLicenseType(String licenseType) {
//        LogUtil.info("Setting License Type: " + licenseType);
        this.licenseType = licenseType;
    }


    public void setLicenseType(int sLicenseType) {
        String licenseType = switch (sLicenseType) {
            case 1 -> this.licenseType = "A";
            case 2 -> this.licenseType = "B";
            case 3 -> this.licenseType = "C";
            case 4 -> this.licenseType = "D";
            default -> throw new IllegalStateException("No such category");
        };
//        LogUtil.info("Setting License Type: " + licenseType);
        this.licenseType = licenseType;
    }


    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
//        LogUtil.info("Setting Starting Date: " + startingDate);
        this.startingDate = startingDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
//        LogUtil.info("Setting Expiration Date: " + expDate);
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "Customer with NIF: " + super.nif + " info:" + "\n" +
                "Driver's license number: " + driverLicenseNum + "\n" +
                "License Category: " + licenseType + "\n" +
                "Registration Date: " + startingDate + "\n" +
                "Expiration Data: " + expDate +  "\n";
    }
}