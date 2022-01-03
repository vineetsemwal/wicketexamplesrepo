package com.mycompany.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.wicketstuff.annotation.mount.MountPath;


@MountPath("/sample")
public class SamplePage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();
        System.out.println("inside oninitialize");
        add(new Label("label3","i am the label 3"));
    }
}
