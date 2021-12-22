package com.mycompany;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class SamplePanel extends Panel {
    public SamplePanel(String id){
        super(id);

        //
        // currently we are going to get WicketRunTimeException with msg Page not found
        // Reason is the panel constructor got executed before panel is added to the page
        //move this to onInitialize and problem will go away
        //
        String pageName=getPage().getClass().getSimpleName();
        add(new Label("pageclass", new Model<>(pageName)));

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

    }
}
