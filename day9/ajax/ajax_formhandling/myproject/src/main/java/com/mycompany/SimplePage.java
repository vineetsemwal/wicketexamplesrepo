package com.mycompany;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
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

    private FeedbackPanel feedback;

    private Label label;

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
       // form.setOutputMarkupPlaceholderTag(true);
        TextField<Integer>evenField=new TextField<>("evenField", LambdaModel.of(SimplePage.this::getEvenValue, SimplePage.this::setEvenValue));
        form.add(evenField);
        evenField.add(EvenValidator.getInstance());
        evenField.setType(Integer.class);
        form.add(new AjaxButton("button") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                System.out.println("inside onsubmit evenValue="+evenValue);
                target.add(feedback);
                target.add(label);
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                System.out.println("inside onerror evenValue="+evenValue);
                target.add(feedback);
                target.add(label);
                evenValue=-1;
            }
        });
        add(form);
        add(label=new Label("even",()->evenValue){
            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(getEvenValue()%2==0);
            }
        });
        label.setOutputMarkupPlaceholderTag(true);
        feedback=new FeedbackPanel("feedback");
        feedback.setOutputMarkupPlaceholderTag(true);
        feedback.setFilter(new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR));
        add(feedback);

    }
}
