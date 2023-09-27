package com.example.njrlib.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String name;
    private int rentCost;
    private String idKOB;
    private String linkImg;

    public Book() {
    }

    public Book(String id, String name, int rentCost, String idKOB,String linkImg) {
        this.id = id;
        this.name = name;
        this.rentCost = rentCost;
        this.idKOB = idKOB;
        this.linkImg=linkImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public String getIdKOB() {
        return idKOB;
    }

    public void setIdKOB(String idKOB) {
        this.idKOB = idKOB;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
}
