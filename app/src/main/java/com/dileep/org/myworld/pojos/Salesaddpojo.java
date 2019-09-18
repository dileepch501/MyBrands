package com.dileep.org.myworld.pojos;

public class Salesaddpojo {
    String access,mobiles;

    public Salesaddpojo(String access, String mobiles) {
        this.access = access;
        this.mobiles = mobiles;
    }

    public Salesaddpojo() {
    }

    public String getAccess() {
        return access;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }
}
