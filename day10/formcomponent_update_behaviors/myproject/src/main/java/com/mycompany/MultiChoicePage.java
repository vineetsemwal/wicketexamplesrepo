package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MultiChoicePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(MultiChoicePage.class);

    public MultiChoicePage(final PageParameters parameters) {
        super(parameters);
    }

    private String name, nameVal;

    private List<String>choicesModelObject=new ArrayList<>();

    private Component nameLabel, teaLabel,coffeeLabel;

    private FeedbackPanel feedbackPanel;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(nameLabel = new Label("nameVal", () -> nameVal).setOutputMarkupPlaceholderTag(true));
        add(teaLabel = new Label("teaVal", () -> {
            if(choicesModelObject.contains("tea")){
                return "tea chosen";
            }
            return "tea not chosen";
        }).setOutputMarkupPlaceholderTag(true));

        add(coffeeLabel = new Label("coffeeVal", () -> {
            if(choicesModelObject.contains("coffee")){
                return "coffee chosen";
            }
            return "coffee not chosen";
        }).setOutputMarkupPlaceholderTag(true));

        add(new NameForm("form"));
        feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        add(feedbackPanel);

    }

    class NameForm extends Form<Void> {

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

            List<String>choices= Arrays.asList("tea","coffee");
            IModel<List<String>>checkGroupModel=new ListModel<>(choicesModelObject);
            CheckBoxMultipleChoice<String>choicesField=new CheckBoxMultipleChoice<>("choices",checkGroupModel,choices);
            add(choicesField);
            choicesField.setSuffix("<br>");
            choicesField.add(new AjaxFormChoiceComponentUpdatingBehavior() {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    feedbackPanel.success("you have chosen "+choicesModelObject);
                    target.add(feedbackPanel,teaLabel,coffeeLabel);
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
