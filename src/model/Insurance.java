package model;

import java.util.Date;

public class Insurance {
    private int policy;
    private Date startDate;
    private Date expDate;
    private String companyName;
    private String extraCategory;
    private String carPlate;

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

    public void setExtraCategory(int extraCategory) {
        String s = switch (extraCategory) {
            case 1 -> this.extraCategory = "Comprehensive Insurance";
            case 2 -> this.extraCategory = "Auto Liability Insurance";
            case 3 -> this.extraCategory = "Theft Insurance";
            default -> throw new IllegalStateException("No such category");
        };
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    @Override
    public String toString() {
        return "Insurance policy number - " + policy + " info:" + "\n"
                + "- Start date: " + startDate + "\n"
                + "- Expiration date: " + expDate + "\n"
                + "- Company name: " + companyName + "\n"
                + "- Extra category: " + extraCategory + "\n"
                + "- Car plate: " + carPlate + "\n";
    }

}
