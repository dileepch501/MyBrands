package com.dileep.org.myworld.pojos;

public class TemplatesPojo {
    String name,temid,msg;
    boolean selected;

    public TemplatesPojo() {

    }

    public TemplatesPojo(String name, String temid, String msg) {
        this.name = name;
        this.temid = temid;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public String getTemid() {
        return temid;
    }

    public String getMsg() {
        return msg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemid(String temid) {
        this.temid = temid;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
