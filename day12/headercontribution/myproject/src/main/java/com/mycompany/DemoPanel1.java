package com.mycompany;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class DemoPanel1 extends Panel {

    public DemoPanel1(String id){
        super(id);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(Style2Reference.get()));
        response.render(CssHeaderItem.forReference(new Style1Reference()));
        ResourceReference jqueryRef=getApplication().getJavaScriptLibrarySettings().getJQueryReference();
        response.render(JavaScriptHeaderItem.forReference(jqueryRef));
        ResourceReference javascrip1Ref=new Javascript1Reference();
        response.render(JavaScriptHeaderItem.forReference(javascrip1Ref));

    }
}
