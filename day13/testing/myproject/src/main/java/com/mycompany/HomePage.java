package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
  public static final String LABEL_ID="counter", CONTAINER_ID="container",LINK_ID="increment";
    private static final Logger log= LoggerFactory.getLogger(HomePage.class);

    private int counter;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");
    }


    @Override
    protected void onConfigure() {
        super.onConfigure();
        System.out.println("inside homepage onconfigure");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer container=new WebMarkupContainer("container");
        add(container);
        container.add(new Label("counter",()->counter));
        add(new Link<Void>("increment"){
            @Override
            public void onClick() {
              counter++;
            }
        });


    }


}
