package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CustomAttributeModifier2 extends Behavior {

    private String propertyName;

    private IModel<String> model;

    public CustomAttributeModifier2() {

    }

    public CustomAttributeModifier2(String propertyName, String value) {
        this.propertyName = propertyName;
        this.model = new Model<>(value);
    }

    public CustomAttributeModifier2(String propertyName, IModel<String> model) {
        this.propertyName = propertyName;
        this.model = model;
    }


    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);
        System.out.println("inside CustombBehavior's onComponentTag");
        tag.put(propertyName, model.getObject());

    }

    @Override
    public void detach(Component component) {
        super.detach(component);
        model.detach();
    }
}
