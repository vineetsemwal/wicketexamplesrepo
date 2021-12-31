package com.mycompany;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

public class CustomerHomePage extends WebPage {

    @Override
    public CustomerSession getSession(){
        return (CustomerSession) super.getSession();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        if(!getSession().isLoggedIn()){
            throw new RestartResponseException(LoginPage.class);
        }

        add(new Label("cid", ()->{
           Customer customer= LoginTracker.get().getCustomer();
           return customer.getId();

        }));

        add(new Label("name", ()->{
            Customer customer= LoginTracker.get().getCustomer();
            return customer.getName();

        }));

        add(new Link<Void>("logout"){
            @Override
            public void onClick() {
              CustomerSession.get().invalidate();
              throw new RestartResponseException(LoginPage.class);
            }
        });
    }
}
