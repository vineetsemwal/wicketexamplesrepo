package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
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
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);



    public HomePage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");

        WebMarkupContainer parent=new WebMarkupContainer("parent");
        parent.setOutputMarkupPlaceholderTag(true);
        add(parent);
        IDataProvider<Product>dataProvider=new ProductsDataProvider();
        DataView<Product>dataView=new DataView<Product>("products",dataProvider){
            @Override
            protected void populateItem(Item<Product> item) {
               Product product=item.getModelObject();
              item.add(new Label("id",Model.of(product.getId())));
              item.add(new Label("name",()->product.getName()));
              item.add(new Label("price",Model.of(product.getPrice())));
            }
        };
        parent.add(dataView);


        add(new AjaxFallbackLink<Void>("link") {
            @Override
            public void onClick(Optional<AjaxRequestTarget> optionalTarget) {
                ProductStore.get().add(9,"lenov0",30000);
              if(optionalTarget.isPresent()){
                  AjaxRequestTarget target=optionalTarget.get();
                  target.add(parent);
              }
            }
        });
    //    PagingNavigator navigator=new PagingNavigator("navigator",dataView);
     //   add(navigator);
    }



}
