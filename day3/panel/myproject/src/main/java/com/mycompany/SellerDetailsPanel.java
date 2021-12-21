package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class SellerDetailsPanel extends Panel {

    public SellerDetailsPanel(String id){
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Seller seller=new Seller(11,"scooby");
        add(new Label("name",()->seller.getName()));
        add(new Label("sellerid",()->seller.getId()));

    }
}
