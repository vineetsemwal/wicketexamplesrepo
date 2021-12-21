package com.mycompany;

import java.util.HashMap;
import java.util.Map;

public class ProductStore {

    private static final ProductStore store=new ProductStore();

    private Map<Integer,Product> productsMap=new HashMap<>();

    private ProductStore(){
        productsMap.put(1, new Product(1,"samsung",10000));
        productsMap.put(2,new Product(2,"motorolla",150000));
    }

    public static ProductStore get(){
        return store;
    }

    public Product getById(int id){
        return productsMap.get(id);
    }



}
