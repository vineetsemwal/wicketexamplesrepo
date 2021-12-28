package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
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


public class RadioMultiChoicePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(RadioMultiChoicePage.class);


    public RadioMultiChoicePage(final PageParameters parameters) {
        super(parameters);
    }



    @Override
    protected void onInitialize() {
        super.onInitialize();
        CustomForm form=new CustomForm("form");
        add(form);
        Label javaField=new Label("java",()->{
         Language language= form.getMultiChoice().getModelObject();
           boolean chosen=language !=null && language.getLanguageName().equalsIgnoreCase("java");
           if(chosen){
             return "java is chosen";

           }
           return "java is not chosen";
        });
        add(javaField);

        Label pythonField=new Label("python",()->{
            Language language= form.getMultiChoice().getModelObject();
            boolean chosen=language !=null && language.getLanguageName().equalsIgnoreCase("python");
           if(chosen){
               return "python is chosen";
           }
           return "python is not chosen";
        });
        add(pythonField);

        Label kotlinField=new Label("kotlin",()->{
            Language language= form.getMultiChoice().getModelObject();
            boolean chosen=language !=null && language.getLanguageName().equalsIgnoreCase("kotlin");
            if(chosen){
                return "kotlin is chosen";
            }
            return "kotlin is not chosen";
        });
        add(kotlinField);


    }

 class CustomForm extends Form<Void> {
        private Language chosen;

     public Language getChosen() {
         return chosen;
     }

     public void setChosen(Language chosen) {
         this.chosen = chosen;
     }


     private RadioChoice<Language>multiChoice;

     public RadioChoice<Language> getMultiChoice() {
         return multiChoice;
     }

     public CustomForm(String id){
            super(id);
        }

     @Override
     protected void onInitialize() {
         super.onInitialize();

         //model for choices for which different radios will be managed internally by CheckBoxMultipleChoice
         IModel<List<Language>> choices=()->{ return Arrays.asList(
                 new Language("java"),
                 new Language("python"),
                 new Language("kotlin")
                 );};

         //will hold user input model objects for chosen checkbox
         IModel<Language>radioModel= LambdaModel.of(this::getChosen,this::setChosen);
         multiChoice=new RadioChoice<>("multi",radioModel,choices,new LanguageChoiceRenderer());
         add(multiChoice);
         multiChoice.setSuffix("<br>");
         add(new Button("submit"));
     }



 }


}
