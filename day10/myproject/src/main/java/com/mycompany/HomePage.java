package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.danekja.java.util.function.serializable.SerializableConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);

    private String name,nameVal;

    private Label nameLabel;

    public HomePage(final PageParameters parameters) {
        super(parameters);
    }



    @Override
    protected void onInitialize() {
        super.onInitialize();
       nameLabel=new Label("nameVal",()->nameVal);
       add(nameLabel);
       nameLabel.setOutputMarkupPlaceholderTag(true);

       add(new CustomerForm("form"));

    }

    class CustomerForm extends Form<Void> {

        private TextField<String>nameField;
        public CustomerForm(String id){
            super(id);
            setOutputMarkupPlaceholderTag(true);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            IModel<String>model= LambdaModel.<String>of(()->name,(newName)->name=newName);
            add(nameField=new TextField<>("name",model));
            add(new AjaxButton("btn") {
                @Override
                protected void onSubmit(AjaxRequestTarget target) {
                  nameVal=name;
                  name="";
                   // nameField.setModelObject("");
                  target.add(nameLabel);
                  target.add(CustomerForm.this);
                }

            });
        }
    }


}
