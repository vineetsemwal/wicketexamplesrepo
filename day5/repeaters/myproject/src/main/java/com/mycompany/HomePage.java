package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);


    public HomePage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");

        List<Product> list=new ArrayList<>();
        list.add(new Product(1, "samsung", 1000));
        list.add(new Product(2, "motorolla", 2000));
        list.add( new Product(3, "iphone", 3000));
        list.add( new Product(4, "hp", 4000));
        IDataProvider<Product>dataProvider=new ListDataProvider<>(list);
        DataView<Product>dataView=new DataView<Product>("products",dataProvider,2){
            @Override
            protected void populateItem(Item<Product> item) {
                Product product=item.getModelObject();
              item.add(new Label("id",Model.of(product.getId())));
              item.add(new Label("name",()->product.getName()));
              item.add(new Label("price",Model.of(product.getPrice())));
            }
        };
        add(dataView);
        add(new PagingNavigator("navigator",dataView));
    }



}
