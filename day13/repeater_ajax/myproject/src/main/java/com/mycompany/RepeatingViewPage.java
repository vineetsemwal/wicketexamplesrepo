package com.mycompany;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.List;

public class RepeatingViewPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<Product>products=ProductStore.get().allProducts();
        RepeatingView repeater=new RepeatingView("products");
        add(repeater);
        for (Product product:products){
            WebMarkupContainer item=new WebMarkupContainer(repeater.newChildId(), new DetachableProductModel(product.getId()));
            repeater.add(item);
            item.add(new Label("id",Model.of(product.getId())));
            item.add(new Label("name",()->product.getName()));
            item.add(new Label("price",Model.of(product.getPrice())));
        }
    }
}
