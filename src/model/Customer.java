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
        LogUtil.info("Setting Driver License Number: " + driverLicenseNum);
        this.driverLicenseNum = driverLicenseNum;
    }


    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        LogUtil.info("Setting License Type: " + licenseType);
        this.licenseType = licenseType;
    }


    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        LogUtil.info("Setting Starting Date: " + startingDate);
        this.startingDate = startingDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        LogUtil.info("Setting Expiration Date: " + expDate);
        this.expDate = expDate;
    }
}