package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;

public class SimplePage extends WebPage {

    private int evenValue;

    public int getEvenValue(){
        return evenValue;
    }

    public void setEvenValue(int evenValue){
        this.evenValue=evenValue;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form<Void>form=new Form<>("form");
        TextField<Integer>evenField=new TextField<>("evenField", LambdaModel.of(SimplePage.this::getEvenValue, SimplePage.this::setEvenValue));
        form.add(evenField);
        evenField.add(EvenValidator.getInstance());
        evenField.setType(Integer.class);
        form.add(new Button("button"));
        add(form);
        add(new Label("even",()->evenValue));
        add(new FeedbackPanel("feedback"));

    }
}
