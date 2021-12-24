package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DetachableModelDemoPage extends WebPage {
    private static final long serialVersionUID = 1L;


    public DetachableModelDemoPage(final PageParameters parameters) {
        super(parameters);
    }


    @Override
    protected void onInitialize() {
        super.onInitialize();

        Product product=new Product();
        product.setId(1);
        product.setName(null);

        Label priceLabel=new Label("price",()->product.getPrice());
        add(priceLabel);

        Label nameLabel=new Label("name",Model.of(product.getName()));
        add(nameLabel);
    }

}
