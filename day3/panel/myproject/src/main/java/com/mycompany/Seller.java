package com.mycompany;

import java.io.Serializable;

public class Seller implements Serializable {

    private  long id;

    private String name;

    public Seller(){}

    public Seller(long id, String name){
        setId(id);
        setName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
