package com.mycompany;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomAttributeModifierPage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(CustomAttributeModifierPage.class);

    private int counter;

    public CustomAttributeModifierPage(final PageParameters parameters) {
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
                System.out.println("inside component's oncomponent tag");
            }
        };
        IModel<String>model=()->{
            if(counter%2==0){
                return "even";
            }
            return "odd";
        };
         Behavior modifier=new CustomAttributeModifier2("class",model);
        counterLabel.add(modifier);
        add(counterLabel);

    }


}
