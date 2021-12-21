package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class SellerDetailsPanel extends Panel {

    public SellerDetailsPanel(String id, IModel model){
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Seller seller=(Seller)getDefaultModelObject();
        add(new Label("name",()->seller.getName()));
        add(new Label("sellerid",()->seller.getId()));

    }
}
