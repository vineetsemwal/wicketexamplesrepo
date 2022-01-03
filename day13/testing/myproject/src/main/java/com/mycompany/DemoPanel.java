package com.mycompany;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class DemoPanel extends Panel {
    private int counter;
    public DemoPanel(String id){
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
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
