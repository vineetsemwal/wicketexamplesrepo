package com.mycompany;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class CustomerSession extends WebSession {
    private int customerId;

     public CustomerSession(Request request){
        super(request);
    }

    public boolean isLoggedIn(){
       return customerId>0;
    }

    public static CustomerSession get(){
        return (CustomerSession) Session.get();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
