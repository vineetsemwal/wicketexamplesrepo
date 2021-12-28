package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);


    public HomePage(final PageParameters parameters) {
        super(parameters);
    }



    @Override
    protected void onInitialize() {
        super.onInitialize();
        CustomForm form=new CustomForm("form");
        add(form);
        Label chosenDetails=new Label("chosen",()->{
         Language language= form.getDropdown().getModelObject();

           if(language!=null){
             return language.getLanguageName();
           }
           return "Nothing chosen";
        });
        add(chosenDetails);


    }

 class CustomForm extends Form<Void> {


        private Language chosen;

     public Language getChosen() {
         return chosen;
     }

     public void setChosen(Language chosen) {
         this.chosen = chosen;
     }


     private DropDownChoice<Language> dropdown;

     public DropDownChoice<Language> getDropdown() {
         return dropdown;
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
         dropdown =new DropDownChoice<>("dropdown",radioModel,choices,new LanguageChoiceRenderer());
         add(dropdown);
         add(new Button("submit"));
     }



 }


}
