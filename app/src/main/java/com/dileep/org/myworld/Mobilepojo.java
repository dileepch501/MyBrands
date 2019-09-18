package com.dileep.org.myworld;

public class Mobilepojo {
    String imei,brand,modelnumber,amount,id;
    String salesmanname,salesmanId;


    public Mobilepojo() {
    }

    public Mobilepojo(String imei, String brand, String modelnumber, String amount, String id,String salesmanname,String salesmanId) {
        this.imei = imei;
        this.brand = brand;
        this.modelnumber = modelnumber;
        this.amount = amount;
        this.id = id;
        this.salesmanname=salesmanname;
        this.salesmanId=salesmanId;
    }

    public String getSalesmanname() {
        return salesmanname;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanname(String salesmanname) {
        this.salesmanname = salesmanname;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getImei() {
        return imei;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelnumber() {
        return modelnumber;
    }

    public String getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModelnumber(String modelnumber) {
        this.modelnumber = modelnumber;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }
}
