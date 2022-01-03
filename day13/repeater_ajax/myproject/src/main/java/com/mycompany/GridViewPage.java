package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GridViewPage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(GridViewPage.class);


    public GridViewPage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");


        IDataProvider<Product>dataProvider=new ProductsDataProvider();
        GridView<Product> gridView=new GridView<Product>("products",dataProvider){
            @Override
            protected void populateItem(Item<Product> item) {
                Product product=item.getModelObject();
              item.add(new Label("id",Model.of(product.getId())));
              item.add(new Label("name",()->product.getName()));
              item.add(new Label("price",Model.of(product.getPrice())));
            }

            @Override
            protected void populateEmptyItem(Item<Product> item) {
                item.add(new Label("id","empty id"));
                item.add(new Label("name",()->"empty name"));
                item.add(new Label("price",Model.of("empty price")));
            }
        };
        gridView.setRows(2);
        gridView.setColumns(2);
        add(gridView);
        PagingNavigator navigator=new PagingNavigator("navigator",gridView);
        add(navigator);
    }



}
