package com.dileep.org.myworld;

public class Accepojodil {

        String id,brand,modelnumber,amount;
        String salesmanname,salesmanId;

        public Accepojodil() {
        }

        public Accepojodil(String id, String brand, String modelnumber, String amount,String salesmanname,String salesmanId) {
            this.id = id;
            this.brand = brand;
            this.modelnumber = modelnumber;
            this.amount = amount;
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

        public String getId() {
            return id;
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

        public void setId(String id) {
            this.id = id;
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


}
