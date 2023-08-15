package model;

import java.sql.Date;
import java.time.LocalDate;

public abstract class Person implements Comparable<Person> {
    protected int nif;
    protected String pwd;
    protected String name;
    protected Date birht_date;
    protected String address;

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        if(nif < 100000000) {
            throw new IllegalArgumentException("NIF must have 9 digits");
        }
        this.nif = nif;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirht_date() {
        return birht_date;
    }

    public void setBirht_date(Date birht_date) {
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
//        if(birht_date.toLocalDate().isAfter(eighteenYearsAgo)) {
//            System.out.println("You must be at least 18 years old to register");
//        }
        this.birht_date = birht_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
