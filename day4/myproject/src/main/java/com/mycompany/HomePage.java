package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);

    private int counter;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");
    }


    @Override
    protected void onConfigure() {
        super.onConfigure();
        System.out.println("inside homepage onconfigure");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        System.out.println("inside homepage onintialize");

        add(new Label("homepageLabel",Model.of("label test cmp")));

        Product product = new Product(1,"samsung",10000);
/*
        WebMarkupContainer productContainer=new WebMarkupContainer("productcon");

        add(productContainer);

        productContainer.add(new Label("productname", Model.of(product.getName())));

        productContainer.add(new Label("productprice",Model.of(product.getPrice()) ));

        */

        add(new ProductContainer("productcon",new Model<>(product)));

    }
/*
    class ProductContainer extends WebMarkupContainer{
        public ProductContainer(String id,IModel<Product>model){
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
*/
}
