package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;

public class ChildPage extends ParentPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("childcmp","label in child"));

    }
}
