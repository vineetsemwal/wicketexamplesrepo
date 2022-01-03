package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import java.io.Serializable;

public class FormPage extends WebPage {
    @Override
    protected void onInitialize() {
        super.onInitialize();
        MyForm form=new MyForm("form");
        add(form);

        add(new FeedbackPanel("feedback"));

    }

    class MyForm extends Form <Void>{
        private int age;
        private String name;
       public MyForm(String id){
           super(id);
          TextField<Integer> ageField=new TextField<>("age",new Model<Integer>(){
              @Override
              public Integer getObject() {
                  return age;
              }

              @Override
              public void setObject(Integer newAge) {
                  age=newAge;
              }
          });
          ageField.setType(Integer.class);
          ageField.add(RangeValidator.range(21,50));
          add(ageField);

          TextField<String>nameField=new TextField<>("name", LambdaModel.<String>of(()->name,(newVal)->name=newVal));
          add(nameField);
          nameField.add(StringValidator.lengthBetween(5,10));
          add(new Button("btn"){
              @Override
              public void onSubmit() {
                 FormPage.this.success("successful");
              }
          });
       }

    }
}
