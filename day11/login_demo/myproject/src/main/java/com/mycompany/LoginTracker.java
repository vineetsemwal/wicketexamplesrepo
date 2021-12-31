package com.mycompany;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

public class LoginTracker  {

    public static final MetaDataKey<Customer> key = new MetaDataKey<Customer>() {
    };

    private static LoginTracker instance=new LoginTracker();

    private  LoginTracker() {

    }

    public static LoginTracker get(){
        return instance;
    }


    private  Customer findCustomerById() {
        CustomerSession session = CustomerSession.get();
        if(session.isTemporary()){
            return null;
        }
        int id=CustomerSession.get().getCustomerId();
        Customer customer=CustomerStore.get().findById(id);
        return customer;
    }


    public  Customer getCustomer(){
        RequestCycle cycle=RequestCycle.get();
        Customer customer = cycle.getMetaData(key);
        if (customer != null) {
            return customer;
        }
        customer = findCustomerById();
        cycle.setMetaData(key, customer);
        System.out.println("customer added in requestscope");
        return customer;
    }



}
