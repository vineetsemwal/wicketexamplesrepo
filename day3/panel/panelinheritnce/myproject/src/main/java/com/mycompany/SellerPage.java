package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import java.util.List;

public class SellerPage extends WebPage {

    public SellerPage(PageParameters parameters){
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        PageParameters parameters=getPageParameters();
        List<StringValue>values=parameters.getValues("sellerid");
        StringValue value=values.get(0);
        long sellerId=value.toLong();
        add(new SellerDetailsPanel("sellerinfo",new DetachableSellerModel(sellerId)));
    }
}
