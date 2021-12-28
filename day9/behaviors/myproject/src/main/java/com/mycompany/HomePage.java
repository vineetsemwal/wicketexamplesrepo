package com.mycompany;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    private int counter;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        setStatelessHint(false);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        counter++;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Label counterLabel=new Label("counter",()->counter){

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if(counter%2==0){
                    tag.put("class","even");
                }else{
                    tag.put("class","odd");
                }
            }
        };

        add(counterLabel);

    }


}
