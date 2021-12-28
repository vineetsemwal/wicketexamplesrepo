package com.mycompany;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AttributeAppenderPage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(AttributeAppenderPage.class);

    private int counter;

    public AttributeAppenderPage(final PageParameters parameters) {
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

        Label counterLabel=new Label("counter",()->counter);
        IModel<String>model=()->{
            if(counter%2==0){
                return "even";
            }
            return "odd";
        };
        //attributeappender doesn't always need model as the value of tag property,
        // use model if you want to be dynamic ie. on every render if you want different style/css add to the tag
        AttributeAppender appender=new AttributeAppender("class",model);
        appender.setSeparator(" ");
        counterLabel.add(appender);
        add(counterLabel);

    }


}
