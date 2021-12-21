package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class SamplePanel extends Panel {

    public  SamplePanel(String id) {
        super(id);
        //
        //this is going to throw WicketRuntimeException with msg No Page found for component
        // as the getPage() method of SamplePanel is called when panel itself is not added
        // to the page yet if you move below line in onInitialize,it will stop giving problem
        //
        add(new Label("pageclassname",new Model<>(getPage().getClass().getSimpleName())));

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

    }
}
