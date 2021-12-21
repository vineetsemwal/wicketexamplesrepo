package com.mycompany;

import org.apache.wicket.markup.html.WebPage;

public class SellerPage extends WebPage {
    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new SellerDetailsPanel("sellerinfo"));
    }
}
