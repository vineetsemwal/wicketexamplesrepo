package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;

public class ProductDetailsPanel extends Panel {



    public ProductDetailsPanel(String id,IModel<Product> model){
        super(id,model);
    }



    public IModel<Product>getModel(){
        return (IModel<Product>)getDefaultModel();
    }

    public Product getProduct(){
        return getModel().getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        IModel<String>nameModel= LambdaModel.of(getProduct()::getName,getProduct()::setName);
        add(new Label("name",nameModel));

        add(new Label("price",()->getProduct().getPrice()));


    }
}
