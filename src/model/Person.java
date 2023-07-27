package model;

import java.util.Date;

public abstract class Person {
    protected int nif;
    protected String pwd;
    protected String name;
    protected Date birht_date;
    protected String address;

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
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
        this.birht_date = birht_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
