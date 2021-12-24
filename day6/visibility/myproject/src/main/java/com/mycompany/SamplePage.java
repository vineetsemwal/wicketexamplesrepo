package com.mycompany;

import org.apache.wicket.markup.html.WebPage;

public class SamplePage extends WebPage {


    public SamplePage(){
        setStatelessHint(false);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new EvenCounterPanel("panel"));
        System.out.println("inside sample page oninitialize");
    }
}
