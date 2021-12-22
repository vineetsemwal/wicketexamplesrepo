package com.mycompany;

import java.io.Serializable;

public class Seller implements Serializable {

    private  long id;

    private String name;

    private Product product;

    public Seller(){}

    public Seller(long id, String name,Product product){
        setId(id);
        setName(name);
        setProduct(product);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
