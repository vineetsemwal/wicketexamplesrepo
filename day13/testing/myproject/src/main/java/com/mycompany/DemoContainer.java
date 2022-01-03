package com.mycompany;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;

public class DemoContainer extends WebMarkupContainer {
    public DemoContainer(String id){
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("mylabel","hello"));
    }
}
