package com.example.njrlib.model;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
    private String id;
    private String name;
<<<<<<< HEAD
    private Date dob;
=======
    private long dob;
>>>>>>> d2b3ea0 (Initial commit)
    private String phoneNumber;
    private int money;
    private String linkAvatar;

    public Member() {

    }
<<<<<<< HEAD
    public Member(String id, String name, Date dob,String phoneNumber,int money, String linkAvatar) {
=======
    public Member(String id, String name, long dob,String phoneNumber,int money, String linkAvatar) {
>>>>>>> d2b3ea0 (Initial commit)
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.money=money;
        this.linkAvatar = linkAvatar;
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
<<<<<<< HEAD
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
=======
    public long getDob() {
        return dob;
    }
    public void setDob(long dob) {
>>>>>>> d2b3ea0 (Initial commit)
        this.dob = dob;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }
}
