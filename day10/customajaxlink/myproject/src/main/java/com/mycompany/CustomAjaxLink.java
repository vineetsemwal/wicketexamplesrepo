package com.mycompany;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class CustomAjaxLink extends Label implements IAjaxIndicatorAware {

    private AjaxIndicatorAppender appenderBehavior =new AjaxIndicatorAppender();

    public CustomAjaxLink(String id, IModel<String> model){
        super(id,model);
    }

    public CustomAjaxLink(String id, String val){
        this(id,new Model<>(val));
    }

    protected  abstract void onClick(AjaxRequestTarget target);




    @Override
    public String getAjaxIndicatorMarkupId() {
     return appenderBehavior.getMarkupId();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(appenderBehavior);
        add(new AjaxEventBehavior("click") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                System.out.println("inside onevent");
                onClick(target);
            }
        });
    }
}
