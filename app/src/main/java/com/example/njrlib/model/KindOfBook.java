package com.example.njrlib.model;

import java.util.ArrayList;

public class KindOfBook {
    private String Id;
    private String name;
    public KindOfBook() {
    }
    public KindOfBook(String id, String name) {
        Id = id;
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
