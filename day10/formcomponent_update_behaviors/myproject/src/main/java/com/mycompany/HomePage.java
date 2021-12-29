package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.validation.validator.StringValidator;
import org.danekja.java.util.function.serializable.SerializableConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    public HomePage(final PageParameters parameters) {
        super(parameters);
    }

    private String name, nameVal;

    private Component nameLabel;

    private FeedbackPanel feedbackPanel;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(nameLabel = new Label("nameVal", () -> nameVal).setOutputMarkupPlaceholderTag(true));
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
                protected void onUpdate(AjaxRequestTarget target) {
                    System.out.println("inside updatingbehavior onupdate");
                    feedbackPanel.success("name is Correct");
                    nameVal = name;
                    target.add(nameLabel);
                    target.add(feedbackPanel);
                }

                @Override
                protected void onError(AjaxRequestTarget target, RuntimeException e) {
                    target.add(feedbackPanel);

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
