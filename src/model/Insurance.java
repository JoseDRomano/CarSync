package model;

import java.util.Date;

public class Insurance {
    private int policy;
    private Date startDate;
    private Date expDate;
    private String companyName;
    private String extraCategory;
    private int carPlate;

    public int getPolicy() {
        return policy;
    }

    public void setPolicy(int policy) {
        this.policy = policy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExtraCategory() {
        return extraCategory;
    }

    public void setExtraCategory(String extraCategory) {
        this.extraCategory = extraCategory;
    }

    public int getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(int carPlate) {
        this.carPlate = carPlate;
    }
}
