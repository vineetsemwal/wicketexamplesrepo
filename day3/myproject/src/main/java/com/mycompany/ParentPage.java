package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class ParentPage extends WebPage {

    public static final String PARENTCMP_ID="parentcmp";

    private Label parentLabel;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(parentLabel=new Label(PARENTCMP_ID,"label in parent"));
    }

    public Label getParentLabel(){
        return parentLabel;
    }
}
