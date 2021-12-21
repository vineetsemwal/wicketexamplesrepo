package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;

public class ProductDetailsPanel extends Panel {

    public ProductDetailsPanel(String id){
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Product product=new Product(1,"samsung",20000);
        IModel<String>nameModel= LambdaModel.of(product::getName,product::setName);
        add(new Label("name",nameModel));

        add(new Label("price",()->product.getPrice()));


    }
}
