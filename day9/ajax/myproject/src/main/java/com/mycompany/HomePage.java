package com.mycompany;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
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
    protected void onInitialize() {
        super.onInitialize();

        Label counterLabel=new Label("counter",()->counter);
        counterLabel.setOutputMarkupPlaceholderTag(true);
        IModel<String>model=()->{
            if(counter%2==0){
                return "even";
            }
            return "odd";
        };

        AjaxLink<Void> link=new AjaxLink<Void>("incrementlink"){
            @Override
            public void onClick(AjaxRequestTarget target) {
                counter++;
                target.add(counterLabel);
            }

        };
        add(link);

        //attributemodifier doesn't always need model as the value of tag property, string can be used as property value
        // use model if you want to be dynamic ie. on every render if you want different style/css add to the tag
        AttributeModifier modifier=new AttributeModifier("class",model);
        counterLabel.add(modifier);
        add(counterLabel);

    }


}
