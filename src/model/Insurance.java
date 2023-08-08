package model;

import java.util.Comparator;
import java.util.Date;

public class Insurance implements Comparable<Insurance> {
    private int policy;
    private Date startDate;
    private Date expDate;
    private String companyName;
    private String extraCategory;
    private String carPlate;

    public static class StringPlateComparator implements Comparator<Insurance> {
        @Override
        public int compare(Insurance o1, Insurance o2) {
            return o1.getCarPlate().compareTo(o2.getCarPlate());
        }

        @Override
        public Comparator<Insurance> reversed() {
            return Comparator.super.reversed();
        }
    }

    public static class RegistrationDateComparator implements Comparator<Insurance> {
        @Override
        public int compare(Insurance o1, Insurance o2) {
            return o1.getStartDate().compareTo(o2.getStartDate());
        }

        @Override
        public Comparator<Insurance> reversed() {
            return Comparator.super.reversed();
        }
    }

    public static class ExpirationateComparator implements Comparator<Insurance> {
        @Override
        public int compare(Insurance o1, Insurance o2) {
            return o1.getExpDate().compareTo(o2.getExpDate());
        }

        @Override
        public Comparator<Insurance> reversed() {
            return Comparator.super.reversed();
        }
    }


    public int getPolicy() {
        return policy;
    }

    public void setPolicy(int policy) {
        this.policy = policy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate() {
        this.startDate = new java.sql.Date(java.util.Calendar.getInstance().getTime().getTime());
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
        switch (extraCategory) {
            case 1 -> this.extraCategory = "Comprehensive Insurance";
            case 2 -> this.extraCategory = "Auto Liability Insurance";
            case 3 -> this.extraCategory = "Theft Insurance";
            default -> this.extraCategory = "Default";
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
        return "Insurance policy number - " + policy + ", info:" + "\n"
                + "- Start date: " + startDate + "\n"
                + "- Expiration date: " + expDate + "\n"
                + "- Company name: " + companyName + "\n"
                + "- Extra category: " + extraCategory + "\n"
                + "- Car plate: " + carPlate + "\n";
    }

    //Default way of comparing
    @Override
    public int compareTo(Insurance o) {
        return this.policy - o.policy;
    }

}
