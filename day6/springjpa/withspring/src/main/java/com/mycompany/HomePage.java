package com.mycompany;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends WebPage {

    @SpringBean
    private IProductService productService;



    public void addProducts() {
        Product product1 = new Product("samsung", 1000);
        productService.add(product1);
        Product product2 = new Product("moto", 2000);
        productService.add(product2);
        Product product3 = new Product("htc", 3000);
        productService.add(product3);
        Product product4 = new Product("iphone", 4000);
        productService.add(product4);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        IDataProvider<Product>dataProvider=new ProductsDataProvider();
        addProducts();
        DataView<Product> dataView = new DataView<Product>("products", dataProvider, 2) {
            @Override
            protected void populateItem(Item<Product> item) {
                Product product = item.getModelObject();
                item.add(new Label("id", Model.of(product.getId())));
                item.add(new Label("name", () -> product.getName()));
                item.add(new Label("price", Model.of(product.getPrice())));
            }
        };
        add(dataView);
        PagingNavigator navigator = new PagingNavigator("navigator", dataView);
        add(navigator);
    }
}
