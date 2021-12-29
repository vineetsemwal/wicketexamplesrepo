package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.validation.validator.StringValidator;
import org.danekja.java.util.function.serializable.SerializableConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    public HomePage(final PageParameters parameters) {
        super(parameters);
    }

    private String name, nameVal;

    private boolean tea,teaVal;

    private Component nameLabel,checkLabel;

    private FeedbackPanel feedbackPanel;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(nameLabel = new Label("nameVal", () -> nameVal).setOutputMarkupPlaceholderTag(true));
        add(checkLabel= new Label("teaVal", () -> {
            if(teaVal){
                return "tea chosen";
            }
            return "tea not chosen";
        }).setOutputMarkupPlaceholderTag(true));
        add(new NameForm("form"));
        feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        add(feedbackPanel);

    }

    class NameForm extends Form<Void> {
       private CheckBox teaCheckBox;

        public NameForm(String id) {
            super(id);
            setOutputMarkupPlaceholderTag(true);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            TextField<String> nameField;
            add(nameField = new TextField<>("name", LambdaModel.<String>of(() -> name, (newName) -> name = newName)));
            nameField.setOutputMarkupPlaceholderTag(true);
            nameField.add(StringValidator.lengthBetween(3, 6));
            nameField.add(new AjaxFormComponentUpdatingBehavior("blur") {

                @Override
                protected void onError(AjaxRequestTarget target,RuntimeException e) {
                    target.add(feedbackPanel);
                }

                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    System.out.println("inside updatingbehavior onupdate");
                    feedbackPanel.success("name is Correct");
                    nameVal = name;
                    target.add(nameLabel);
                    target.add(feedbackPanel);
                }

            });

            IModel<List<String>>checkGroupModel=new ListModel<>(new ArrayList<>());
            CheckGroup<String>checkGroup=new CheckGroup("checkGroup",checkGroupModel);
            checkGroup.setOutputMarkupPlaceholderTag(true);
            add(checkGroup);
            teaCheckBox=new CheckBox("tea",LambdaModel.<Boolean>of(()->tea,(newTea)->tea=newTea));
            checkGroup.add(teaCheckBox);
            teaCheckBox.setOutputMarkupPlaceholderTag(true);

            checkGroup.add(new AjaxFormChoiceComponentUpdatingBehavior(){


                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                  target.add(feedbackPanel);
                  target.add(checkLabel);
                  if(tea){
                    feedbackPanel.success("Good, you have chosen tea");
                  }
                }
            });

            add(new AjaxButton("btn") {
                @Override
                protected void onSubmit(AjaxRequestTarget target) {
                    nameVal = name;
                    name = "";
                    target.add(NameForm.this, nameLabel);
                    target.add(feedbackPanel);
                }
            });

        }
    }


}
