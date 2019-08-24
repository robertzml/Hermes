package com.shengdangjia.hermescompany.model;

public class Company {
    private long id;

    private String name;

    private String port;

    public Company() {

    }

    public Company(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
