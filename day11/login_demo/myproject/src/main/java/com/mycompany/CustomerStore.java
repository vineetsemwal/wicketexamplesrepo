package com.mycompany;

import java.util.HashMap;
import java.util.Map;

public class CustomerStore {

    private static final CustomerStore instance=new CustomerStore();

    private Map<Integer,Customer> map=new HashMap<>();

    private CustomerStore(){
        map.put(1,new Customer(1,"manik","1234"));
        map.put(2,new Customer(2,"sourav","1234"));
        map.put(3,new Customer(3,"shiva","1234"));

    }

    public static CustomerStore get(){
        return instance;
    }

    public boolean isCredentialsCorrect(int id, String password){
       Customer customer= findById(id);
       return customer.getId()==id && customer.getPassword().equals(password);
    }

    public Customer findById(int id){
        return map.get(id);
    }

}
