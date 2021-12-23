package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataviewPanelChildPage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(DataviewPanelChildPage.class);


    public DataviewPanelChildPage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");


        IDataProvider<Product>dataProvider=new ProductsDataProvider();
        DataView<Product>dataView=new DataView<Product>("products",dataProvider,2){
            @Override
            protected void populateItem(Item<Product> item) {
              item.add(new ProductPanel("product",item.getModel()));
            }
        };
        add(dataView);
        PagingNavigator navigator=new PagingNavigator("navigator",dataView);
        add(navigator);
    }



}
