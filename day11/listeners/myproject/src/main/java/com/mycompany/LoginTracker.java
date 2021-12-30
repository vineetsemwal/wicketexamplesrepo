package com.mycompany;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

public class LoginTracker implements IRequestCycleListener {

    public static final MetaDataKey<Customer> key = new MetaDataKey<Customer>() {
    };

    @Override
    public void onBeginRequest(RequestCycle cycle) {
        System.out.println("inside Logintracker onebeginrequest");

    }




    public static Customer getLoginCustomer() {
        Customer customer = new Customer(1, "sourav");
        return customer;
    }


    public static Customer getCustomer(){
        RequestCycle cycle=RequestCycle.get();
        Customer customer = cycle.getMetaData(key);
        if (customer != null) {
            return customer;
        }
        customer = getLoginCustomer();
        cycle.setMetaData(key, customer);
        System.out.println("customer addd in requestscope");
        return customer;
    }



}
