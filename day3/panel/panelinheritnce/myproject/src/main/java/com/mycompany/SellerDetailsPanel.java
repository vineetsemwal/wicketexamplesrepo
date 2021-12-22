package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SellerDetailsPanel extends ProductDetailsPanel {

    private IModel<Seller>sellerModel;
    public SellerDetailsPanel(String id, IModel<Seller> model){
        super(id, new DetachableProductModel(model.getObject().getProduct().getId()));
        this.sellerModel=model;
    }

    public IModel<Seller>getSellerModel(){
        return sellerModel;
    }

    public Seller getSeller(){
        return getSellerModel().getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("sellername",()->getSeller().getName()));
        add(new Label("sellerid",()->getSeller().getId()));

    }



    @Override
    protected void onDetach() {
        super.onDetach();
        sellerModel.detach();
    }
}
