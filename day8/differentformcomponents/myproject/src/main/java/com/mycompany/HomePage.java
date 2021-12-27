package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        Label javaField=new Label("java",()->{
           boolean chosen=form.getJavaCheck().getModelObject();
           if(chosen){
             return "java is chosen";

           }
           return "java is not chosen";
        });
        add(javaField);

        Label pythonField=new Label("python",()->{
           boolean chosen=form.getPythonCheck().getModelObject();
           if(chosen){
               return "python is chosen";
           }
           return "python is not chosen";
        });
        add(pythonField);

        Label kotlinField=new Label("kotlin",()->{
            boolean chosen=form.getKotlinCheck().getModelObject();
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
     private CheckBox javaCheck;
     private CheckBox pythonCheck;
    private CheckBox kotlinCheck;

     public CheckBox getJavaCheck() {
         return javaCheck;
     }

     public CheckBox getPythonCheck() {
         return pythonCheck;
     }

     public CheckBox getKotlinCheck() {
         return kotlinCheck;
     }

     public CustomForm(String id){
            super(id);
        }

     @Override
     protected void onInitialize() {
         super.onInitialize();
         javaCheck=new CheckBox("java", LambdaModel.of(this::isJava,this::setJava));
         add(javaCheck);
         pythonCheck=new CheckBox("python",LambdaModel.of(this::isPython,this::setPython));
         add(pythonCheck);
         kotlinCheck=new CheckBox("kotlin",LambdaModel.of(this::isKotlin,this::setKotlin));
         add(kotlinCheck);
         add(new Button("submit"));
     }
 }


}
