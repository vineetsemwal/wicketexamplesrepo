package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class CheckMultiChoicePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(CheckMultiChoicePage.class);


    public CheckMultiChoicePage(final PageParameters parameters) {
        super(parameters);
    }



    @Override
    protected void onInitialize() {
        super.onInitialize();
        CustomForm form=new CustomForm("form");
        add(form);
        Label javaField=new Label("java",()->{
         Collection<Language>chosens= form.getMultiChoice().getModelObject();

           boolean chosen=chosens.contains(new Language("java"));
           if(chosen){
             return "java is chosen";

           }
           return "java is not chosen";
        });
        add(javaField);

        Label pythonField=new Label("python",()->{
            Collection<Language>chosens= form.getMultiChoice().getModelObject();
            boolean chosen=chosens.contains(new Language("python"));
           if(chosen){
               return "python is chosen";
           }
           return "python is not chosen";
        });
        add(pythonField);

        Label kotlinField=new Label("kotlin",()->{
            Collection<Language>chosens= form.getMultiChoice().getModelObject();
            boolean chosen=chosens.contains(new Language("kotlin"));
            if(chosen){
                return "kotlin is chosen";
            }
            return "kotlin is not chosen";
        });
        add(kotlinField);


    }

 class CustomForm extends Form<Void> {
        private boolean java, python,kotlin;

     public boolean isJava() {
         return java;
     }

     public void setJava(boolean java) {
         this.java = java;
     }

     public boolean isPython() {
         return python;
     }

     public void setPython(boolean python) {
         this.python = python;
     }

     public boolean isKotlin() {
         return kotlin;
     }

     public void setKotlin(boolean kotlin) {
         this.kotlin = kotlin;
     }

     private CheckBoxMultipleChoice<Language>multiChoice;

     public CheckBoxMultipleChoice<Language> getMultiChoice() {
         return multiChoice;
     }

     public CustomForm(String id){
            super(id);
        }

     @Override
     protected void onInitialize() {
         super.onInitialize();

         List<Language> choices= Arrays.asList(
                 new Language("java"),
                 new Language("python"),
                 new Language("kotlin")
                 );

         IModel<List<Language>>listModel=new ListModel<>(new ArrayList<>());
         multiChoice=new CheckBoxMultipleChoice<>("multi",listModel,choices,new LanguageChoiceRenderer());
         add(multiChoice);
         multiChoice.setSuffix("<br>");
         add(new Button("submit"));
     }



 }


}
