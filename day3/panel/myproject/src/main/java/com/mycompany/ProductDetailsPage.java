package com.mycompany;

import org.apache.wicket.markup.html.WebPage;

public class ProductDetailsPage extends WebPage {


    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ProductDetailsPanel("productinfo"));

    }
}
