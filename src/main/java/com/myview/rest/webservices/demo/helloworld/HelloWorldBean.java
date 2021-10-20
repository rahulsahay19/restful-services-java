package com.myview.rest.webservices.demo.helloworld;

public class HelloWorldBean {
    private String mesg;
    public HelloWorldBean(String mesg) {
        this.mesg = mesg;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "mesg='" + mesg + '\'' +
                '}';
    }
}
