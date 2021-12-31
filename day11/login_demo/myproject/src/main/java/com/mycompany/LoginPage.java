package com.mycompany;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(LoginPage.class);
    private Integer id;
    private String password;
    private FeedbackPanel feedback;

    public LoginPage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        feedback = new FeedbackPanel("feedback");
        add(feedback);
        add(new LoginForm("form"));
    }

    @Override
    public CustomerSession getSession() {
        return (CustomerSession) super.getSession();
    }

    class LoginForm extends Form <Void>{
       public LoginForm(String id){
            super(id);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            TextField<Integer>idField;
            PasswordTextField passwordField;
            add(idField=new TextField<Integer>("cid", LambdaModel.<Integer>of(()->id,(newId)->id=newId)));
             idField.setType(Integer.class);
            add(passwordField=new PasswordTextField("password",new Model<String>(){
                @Override
                public String getObject() {
                    return password;
                }

                @Override
                public void setObject(String newPassword) {
                    password=newPassword;
                }
            }));

            add(new Button("submit"){


                @Override
                public void onSubmit() {
                  boolean check= CustomerStore.get().isCredentialsCorrect(id,password);
                  if(!check){
                   feedback.error("incorrect credentials");
                   return;
                  }
                  CustomerSession session= LoginPage.this.getSession();
                  session.setCustomerId(id);
                  session.bind();
                  throw new RestartResponseException(CustomerHomePage.class);

                }
            });
        }
    }

}
