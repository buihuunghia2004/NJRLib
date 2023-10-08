package com.example.njrlib.model;

import java.io.Serializable;

public class Librarian implements Serializable {
    private String id;
    private String password;
    private String name;

    public Librarian() {
    }

    public Librarian(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
