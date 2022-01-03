package com.mycompany;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

import java.util.Locale;

public class DemoPanel extends Panel {
    private int counter;
    public DemoPanel(String id){
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        //setting german local will use german properties, this helps in internationalization
        //foreg if the user is german show him german text , if japan, show japanese text
        // remembr to create properties file for that local foreg. in current case DemoPanel_de.properties
        // a part from DemoPanel.properties which can can be for english locale
        //getSession().setLocale(Locale.GERMAN);
        Label property1Label=new Label("msg1",new ResourceModel("msg1"));
        add(property1Label);
        Label property2Label=new Label("msg2",new ResourceModel("msg2"));
        add(property2Label);


        Label counterLabel;
        add(counterLabel=new Label("counter",()->counter));
        counterLabel.setOutputMarkupPlaceholderTag(true);
        add(new AjaxLink<Void>("increment") {
            @Override
            public void onClick(AjaxRequestTarget target) {
               counter++;
               target.add(counterLabel);
            }
        });
    }
}
