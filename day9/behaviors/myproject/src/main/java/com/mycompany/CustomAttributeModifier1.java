package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

public class CustomAttributeModifier1 extends Behavior {



    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);
        System.out.println("inside CustombBehavior's onComponentTag");
        int counter=(Integer)component.getDefaultModelObject();
        if(counter%2==0)
        {
            tag.put("class","odd");
        }else{
            tag.put("class","even");

        }
    }
}
