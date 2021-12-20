package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
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
        System.out.println("inside homepage onconfigure");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        System.out.println("inside homepage onintialize");
        add(new Label("msg", new Model<>("welcome to wicket")));

        Label counterLabel = new Label("counter", ()->counter);
        add(counterLabel);

		Link<Void>incrementLink=new Link<Void>("increment"){
			@Override
			public void onClick() {
              counter++;
            //  counterLabel.setDefaultModelObject(counter);
              System.out.println("counter new val="+counter);
			}
		};

		add(incrementLink);

		PageParameters parameters=new PageParameters();
		parameters.add("even",2);
		parameters.add("even",4);
		parameters.add("odd",1);
		parameters.add("odd",3);
        parameters.add("productid",10);

		Link demoLink=new Link<Void>("demoLink"){
            @Override
            public void onClick() {
                 setResponsePage(DemoPage.class,parameters);
               // counter++;
                //System.out.println("after response page called");
            }
        };
		add(demoLink);


		//BookmarkablePageLink<Void>demoLink=new BookmarkablePageLink("demoLink",DemoPage.class,parameters);
		//add(demoLink);

    }

}
