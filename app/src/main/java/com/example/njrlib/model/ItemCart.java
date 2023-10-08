package com.example.njrlib.model;

public class ItemCart {
    private Book book;
    private int num;
    private boolean ischeck;

    public ItemCart(Book book, int num,boolean ischeck) {
        this.book = book;
        this.num = num;
        this.ischeck=ischeck;
    }

    public ItemCart() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }
}
