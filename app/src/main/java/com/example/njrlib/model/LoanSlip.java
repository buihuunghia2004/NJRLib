package com.example.njrlib.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class LoanSlip implements Serializable {
    private String id;
    private String memberId;
    private String librarianId;
    private String  idBook;
    private long dayOfBorrowing;
    private long dayReturn;
    private int money;
    private int condition;//
    //0 -> chưa xác nhận
    //1 -> đã xác nhận chờ lấy sách
    //2 -> đang thuê sách
    //3 -> đã trả sách

    public LoanSlip(String id, String memerId, String librarianId,String idBook, long dayOfBorrowing, long dayReturn, int money,int condition) {
        this.id = id;
        this.memberId = memerId;
        this.librarianId = librarianId;
        this.idBook = idBook;
        this.dayOfBorrowing = dayOfBorrowing;
        this.dayReturn = dayReturn;
        this.money = money;
        this.condition=condition;
    }
    public LoanSlip(String memerId, String librarianId,String idBook, long dayOfBorrowing, long dayReturn, int money,int condition) {
        this.memberId = memerId;
        this.librarianId = librarianId;
        this.idBook = idBook;
        this.dayOfBorrowing = dayOfBorrowing;
        this.dayReturn = dayReturn;
        this.money = money;
        this.condition=condition;
    }

    public LoanSlip() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemerId() {
        return memberId;
    }

    public void setMemerId(String memerId) {
        this.memberId = memerId;
    }

    public String getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(String librarianId) {
        this.librarianId = librarianId;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public long getDayOfBorrowing() {
        return dayOfBorrowing;
    }

    public void setDayOfBorrowing(long dayOfBorrowing) {
        this.dayOfBorrowing = dayOfBorrowing;
    }

    public long getDayReturn() {
        return dayReturn;
    }

    public void setDayReturn(long dayReturn) {
        this.dayReturn = dayReturn;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }
}
