package com.example.njrlib.model;

import java.util.ArrayList;

public class Cart {
    private String uid;
    private ArrayList<String> listIdBook;

    public Cart() {
    }

    public Cart(String uid, ArrayList<String> listIdBook) {
        this.uid = uid;
        this.listIdBook = listIdBook;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<String> getListIdBook() {
        return listIdBook;
    }

    public void setListIdBook(ArrayList<String> listIdBook) {
        this.listIdBook = listIdBook;
    }
}
