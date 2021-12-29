package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.IAjaxCallListener;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class SecondPreconditionDemoPage extends WebPage {
    private String name;

    private Label nameLabel;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new NameForm("form"));
        add(nameLabel=new Label("nameVal",()->name) );
        nameLabel.setOutputMarkupPlaceholderTag(true);
    }

    class NameForm extends Form<Void> {
     private TextField<String> nameField;
        public NameForm(String id){
         super(id);
         setOutputMarkupPlaceholderTag(true);
     }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            add(nameField=new TextField<>("name",new Model<String>(){
                @Override
                public String getObject() {
                    return name;
                }

                @Override
                public void setObject(String object) {
                    name=object;
                }
            }));
            nameField.setOutputMarkupPlaceholderTag(true);

            add(new AjaxButton("btn") {

                @Override
                protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                    attributes.getAjaxCallListeners().add(new IAjaxCallListener(){
                        @Override
                        public CharSequence getPrecondition(Component component) {
                            String js=String.format("var val=document.getElementById('%s').value;if(val!=='shiva'){return false;}",nameField.getMarkupId());
                            return js;
                        }
                    });
                }

                @Override
                protected void onSubmit(AjaxRequestTarget target) {
                    System.out.println("request sent to server");
                    System.out.println("model object value="+name);
                    target.add(nameLabel);
                }
            });
        }
    }

}
