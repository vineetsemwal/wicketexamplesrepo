package com.mycompany;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;

    private Double price;

    public Product(){}

    public Product(int id, String name, double price){
        this.id=id;
        this.name = name;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
