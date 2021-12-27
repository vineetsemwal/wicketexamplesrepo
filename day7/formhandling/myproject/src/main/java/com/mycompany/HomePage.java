package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);


    public HomePage(final PageParameters parameters) {
        super(parameters);
    }

     private Product product=new Product();

    private boolean productAdded;
    private FeedbackPanel failureFeedbackPanel,successFeedbackPanel;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        IModel<Product>model= new Model<>(product);
        add(new ProductForm("productForm",model));
        add(failureFeedbackPanel=new FeedbackPanel("feedbackFail",new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
        add(successFeedbackPanel=new FeedbackPanel("feedbackWin", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));
        add(new ProductDetailsContainer("productDetails",model));

    }

    class ProductDetailsContainer extends WebMarkupContainer{
        public ProductDetailsContainer(String id,IModel<Product>model){
            super(id,model);
        }

        public  IModel<Product>getModel(){
            return (IModel<Product>)getDefaultModel();
        }

        @Override
        protected void onConfigure() {
            super.onConfigure();
            setVisible(productAdded);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            add(new Label("id",()->getModel().getObject().getId()));
            add(new Label("name",()->getModel().getObject().getName()));
            add(new Label("price",()->getModel().getObject().getPrice()));
        }
    }


    class ProductForm extends Form<Product> {

        private TextField<Double>priceField;
        public ProductForm(String id, IModel<Product>model){
            super(id,model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            TextField<String>nameField=new TextField<>("name",LambdaModel.of(getModelObject()::getName,getModelObject()::setName));
             nameField.setRequired(true);
             nameField.add(StringValidator.exactLength(5));
             add(nameField);
             priceField=new TextField<>("price",LambdaModel.of(getModelObject()::getPrice,getModelObject()::setPrice));
             add(priceField);
             priceField.setRequired(true);
             priceField.setType(Double.class);
             priceField.add(RangeValidator.range(100.0,200.0));
            // priceField.setLabel(Model.of("pricefield"));

             TextField<Integer>idField=new TextField<>("id",new Model<Integer>(){
                 @Override
                 public Integer getObject() {
                     return ProductForm.this.getModelObject().getId();
                 }

                 @Override
                 public void setObject(Integer id) {
                     ProductForm.this.getModelObject().setId(id);
                 }
             });

             add(idField);
             idField.setType(Integer.class);
            idField.add(RangeValidator.minimum(1));
            idField.setRequired(true);
            add(new Button("btn"));


        }

        @Override
        protected void onSubmit() {
            Product product=getModelObject();
            System.out.println("inside onsubmit id"+product.getId()+"-name-"+product.getName()+"-"+product.getPrice());
            productAdded=true;
            success("Thankyou, product successfully added");
        }

        @Override
        protected void onValidate() {
          Double price= priceField.getConvertedInput();
           if(price<0){
               error("price too low");
           }else{
               success("correct price entered");
           }

        }


        @Override
        protected void onError() {
            Product product=getModelObject();
            System.out.println("inside onError id"+product.getId()+"-name-"+product.getName()+"-"+product.getPrice());

        }
    }

}
