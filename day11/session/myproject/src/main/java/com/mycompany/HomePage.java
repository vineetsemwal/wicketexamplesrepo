package com.mycompany;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);

    private int counter;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        Session session=  getSession();
        System.out.println("inside onconfigure,"+"session temporary="+session.isTemporary()+ "session id="+session.getId());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("counter",()->counter));
        add(new Link<Void>("incrementLink"){
            @Override
            public void onClick() {
                System.out.println("inside onclick");
                counter++;
            }
        });

    }

}
