package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ProductPanel extends Panel {

    public  ProductPanel(String id, IModel<Product> model){
        super(id,model);
    }
    public IModel<Product>getModel(){
        return (IModel<Product>)getDefaultModel();
    }

    public  Product getProduct(){
        return getModel().getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("id", Model.of(getProduct().getId())));
        add(new Label("name",()->getProduct().getName()));
        add(new Label("price",Model.of(getProduct().getPrice())));
    }
}
