package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private int counter;

    public HomePage(final PageParameters parameters) {
        super(parameters);
    }


    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("msg", "welcome to wicket"));

        Label counterLabel = new Label("counter",()->counter);
        add(counterLabel);

		Link<Void>incrementLink=new Link<Void>("increment"){
			@Override
			public void onClick() {
              counter++;
                System.out.println("counter new val="+counter);
			}
		};

		add(incrementLink);

		PageParameters parameters=new PageParameters();
		parameters.add("even",2);
		parameters.add("even",4);
		parameters.add("odd",1);
		parameters.add("odd",3);

		/*
		Link demoLink=new Link<Void>("demoLink"){
            @Override
            public void onClick() {

                Product product=new Product();
                DemoPage page=new DemoPage(product);
               setResponsePage(page);
                // setResponsePage(DemoPage.class,parameters);
                counter++;
            }
        };
		add(demoLink);

		*/
		BookmarkablePageLink<Void>demoLink=new BookmarkablePageLink("demoLink",DemoPage.class,parameters);
		add(demoLink);

    }
}
