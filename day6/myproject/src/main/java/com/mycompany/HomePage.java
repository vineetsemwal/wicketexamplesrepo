package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);


    public HomePage(final PageParameters parameters) {
        super(parameters);
    }



    @Override
    protected void onInitialize() {
        super.onInitialize();

        Product product=new Product();
        product.setId(1);
        product.setName("samsung");
        product.setPrice(10000d);

       /*
        Label priceLabel=new Label("price",new PropertyModel<>(product,"price"));
        add(priceLabel);

        Label nameLabel=new Label("name",new PropertyModel<>(product,"name"));
        add(nameLabel);

        */
        IModel<Double>priceModel= LambdaModel.of(product::getPrice,product::setPrice);
        Label priceLabel=new Label("price",priceModel);
        add(priceLabel);
        IModel<String>nameModel=LambdaModel.of(product::getName,product::setName);
        Label nameLabel=new Label("name",nameModel);
        add(nameLabel);

    }

}
