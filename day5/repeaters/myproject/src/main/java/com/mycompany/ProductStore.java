package com.mycompany;

import java.util.HashMap;
import java.util.Map;

public class ProductStore {
    private static final ProductStore instance = new ProductStore();

    private Map<Integer, Product> map = new HashMap<>();

    private ProductStore() {
        map.put(1, new Product(1, "samsung", 1000));
        map.put(2, new Product(2, "motorolla", 2000));
        map.put(3, new Product(3, "iphone", 3000));

    }



    public static ProductStore get() {
        return instance;
    }

    public Product getById(int id){
        return map.get(id);
    }


}
