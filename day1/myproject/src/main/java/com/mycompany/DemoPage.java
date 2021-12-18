package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import java.util.List;
import java.util.Set;

public class DemoPage extends WebPage {

    public DemoPage(PageParameters parameters){
        super(parameters);
    }

    public DemoPage(Product product){
        super(new Model<>(product));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        PageParameters parameters= getPageParameters();
        Set<String> keys=parameters.getNamedKeys();
        for (String key:keys){
            System.out.println("param key="+key);
           List<StringValue> values =parameters.getValues(key);
           for (StringValue value:values){
              int paramValue  =value.toOptionalInteger();
               System.out.println("param value="+paramValue);
           }
        }
    }
}
