package com.mycompany;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class EvenCounterPanel extends Panel {

    private int counter;

    public EvenCounterPanel(String id){
        super(id);
    }


    @Override
    protected void onConfigure() {
        super.onConfigure();
        System.out.println("inside onconfigure,counter="+counter);
        if(counter%2==0){
            setVisible(true);
        }else{
            setVisible(false);
        }
        counter++;

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        System.out.println("inside eventcounterpanel oninitalize");
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        System.out.println("inside onbeforerender, counter="+counter);


    }
}
