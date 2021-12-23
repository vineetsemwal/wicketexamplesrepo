package com.mycompany;

import java.util.*;

public class ProductStore {
    private static final ProductStore instance = new ProductStore();

    private Map<Integer, Product> map = new LinkedHashMap<>();

    private ProductStore() {
        map.put(1, new Product(1, "samsung", 1000));
        map.put(2, new Product(2, "motorolla", 2000));
        map.put(3, new Product(3, "iphone", 3000));
        map.put(4, new Product(4, "htc", 4000));
        map.put(5, new Product(5, "htc", 5000));
    }


    public static ProductStore get() {
        return instance;
    }

    public Product getById(int id){
        System.out.println("inside getbyid "+id);
        Product product= map.get(id);
        System.out.println("product fetched "+product);
        return product;
    }


    public int size(){
        return map.size();
    }

    public List allProducts(){
        List<Product>list=new ArrayList<>();
        for(int key:map.keySet()){
            Product product= map.get(key);
            list.add(product);
        }
        return list;
    }

    public List<Product>findProducts(long first, long blockSize){
        List<Product>list=allProducts();
        int toIndex = (int)first +(int) blockSize;
        List <Product>result= list.subList((int)first, toIndex);
        System.out.println(result);
        return result;
    }


}
