package com.mycompany;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class CustomSession extends WebSession {
    public CustomSession(Request request){
        super(request);
    }

    public static CustomSession get(){
        return (CustomSession) Session.get();
    }

    private int count;

    public int getCount() {
     return count;
    }

     int increment(){
        return ++count;
    }


    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
