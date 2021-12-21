package com.mycompany;

import java.util.HashMap;
import java.util.Map;

public class SellerStore {

    private static final SellerStore store=new SellerStore();

    private Map<Long, Seller>sellersMap=new HashMap<>();

    private SellerStore(){
        sellersMap.put(1l,new Seller(1,"mohan"));
        sellersMap.put(2l,new Seller(2,"venkat"));
    }

    public static  SellerStore get(){
        return store;
    }

    public Seller getSellerById(long sellerId){
        return sellersMap.get(sellerId);
    }



}
