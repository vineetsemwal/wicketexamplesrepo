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
        Product product1=ProductStore.get().getById(1);
        Seller seller1=new Seller(1,"mohan",product1);
        Product product2=ProductStore.get().getById(2);
        Seller seller2=new Seller(2,"venkat",product2);
        add(new SellerDetailsPanel("sellerinfo1",new Model<>(seller1)));
        add(new SellerDetailsPanel("sellerinfo2",new Model<>(seller2)));

        PageParameters seller1Parameters=new PageParameters();
        seller1Parameters.add("sellerid",seller1.getId());
        add(new BookmarkablePageLink("sellerlink1",SellerPage.class, seller1Parameters));
        PageParameters seller2Parameters=new PageParameters();
        seller2Parameters.add("sellerid",seller2.getId());
        add(new BookmarkablePageLink("sellerlink2",SellerPage.class,seller2Parameters));

        add(new ProductDetailsPanel("prouctinfo", new DetachableProductModel(product1.getId())));
        add(new BookmarkablePageLink("productlink",ProductDetailsPage.class));

    }

}
