package com.mycompany;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);


    public HomePage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        ResourceReference imgRef=new PackageResourceReference(HomePage.class,"flag.png");
        add(new Image("img1",imgRef));

    }
}
