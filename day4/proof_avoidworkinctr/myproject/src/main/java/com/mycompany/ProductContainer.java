package com.mycompany;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ProductContainer extends WebMarkupContainer {
    public ProductContainer(String id, IModel<Product> model){
        super(id,model);
    }

    public IModel<Product>getProductModel(){
        return (IModel<Product>) getDefaultModel();
    }

    public Product getProduct(){
        return getProductModel().getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("productname", Model.of(getProduct().getName())));
        add(new Label("productprice",Model.of(getProduct().getPrice()) ));
    }
}