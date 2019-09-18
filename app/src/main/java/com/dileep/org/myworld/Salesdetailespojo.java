package com.dileep.org.myworld;

public class Salesdetailespojo {
    String salesmanid;
             long accesscount,mobilescount;

    public Salesdetailespojo() {
    }

    public Salesdetailespojo(String salesmanid, long accesscount, long mobilescount) {
        this.salesmanid = salesmanid;
        this.accesscount = accesscount;
        this.mobilescount = mobilescount;
    }

    public String getSalesmanid() {
        return salesmanid;
    }

    public long getAccesscount() {
        return accesscount;
    }

    public long getMobilescount() {
        return mobilescount;
    }

    public void setSalesmanid(String salesmanid) {
        this.salesmanid = salesmanid;
    }

    public void setAccesscount(long accesscount) {
        this.accesscount = accesscount;
    }

    public void setMobilescount(long mobilescount) {
        this.mobilescount = mobilescount;
    }
}
