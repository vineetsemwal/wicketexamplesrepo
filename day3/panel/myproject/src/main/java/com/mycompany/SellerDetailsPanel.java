package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SellerDetailsPanel extends Panel {


    private int counter; // incrementing

    public SellerDetailsPanel(String id, IModel model){
        super(id, model);
    }

    public IModel<Seller>getSellerModel(){
        return (IModel<Seller>)getDefaultModel();
    }

    public Seller getSeller(){
        return getSellerModel().getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("name",()->getSeller().getName()));
        add(new Label("sellerid",()->getSeller().getId()));

    }
}
