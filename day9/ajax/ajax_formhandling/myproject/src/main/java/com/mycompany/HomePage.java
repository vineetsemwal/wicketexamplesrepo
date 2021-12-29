package com.mycompany;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
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

     private Product formsProduct =new Product();
     private Product product;

    private boolean productAdded;
    private FeedbackPanel failureFeedbackPanel,successFeedbackPanel;

    private ProductDetailsContainer productDetailsContainer;


    @Override
    protected void onInitialize() {
        super.onInitialize();
        IModel<Product>formsModel= LambdaModel.<Product>of(()-> formsProduct,(newProduct)-> formsProduct =newProduct);
        add(new ProductForm("productForm",formsModel));
        add(failureFeedbackPanel=new FeedbackPanel("feedbackFail",new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
        add(successFeedbackPanel=new FeedbackPanel("feedbackWin", new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));
       failureFeedbackPanel.setOutputMarkupPlaceholderTag(true);
       successFeedbackPanel.setOutputMarkupPlaceholderTag(true);
       IModel<Product>detailsModel=LambdaModel.<Product>of(()->product,(newProduct)->product=newProduct);
        add( productDetailsContainer=new ProductDetailsContainer("productDetails",detailsModel));

    }

    class ProductDetailsContainer extends WebMarkupContainer{
        public ProductDetailsContainer(String id,IModel<Product>model){
            super(id,model);
            setOutputMarkupPlaceholderTag(true);
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
            HomePage.this.setOutputMarkupPlaceholderTag(true);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            TextField<String>nameField=new TextField<>("name",LambdaModel.<String>of(()-> formsProduct.getName(),(newName)->formsProduct.setName(newName)));
             nameField.setRequired(true);
             nameField.add(StringValidator.exactLength(5));
             add(nameField);
             priceField=new TextField<>("price",LambdaModel.<Double>of(()->formsProduct.getPrice(),(newPrice)->formsProduct.setPrice(newPrice)));
             add(priceField);
             priceField.setRequired(true);
             priceField.setType(Double.class);
             priceField.add(RangeValidator.range(100.0,200.0));
            // priceField.setLabel(Model.of("pricefield"));

             TextField<Integer>idField=new TextField<>("id",new Model<Integer>(){
                 @Override
                 public Integer getObject() {
                     return formsProduct.getId();
                 }

                 @Override
                 public void setObject(Integer id) {
                    formsProduct.setId(id);
                 }
             });

             add(idField);
             idField.setType(Integer.class);
            idField.add(RangeValidator.minimum(1));
            idField.setRequired(true);
            add(new AjaxButton("btn"){
                @Override
                protected void onSubmit(AjaxRequestTarget target) {
                    Product modelObject=ProductForm.this.getModelObject();
                    System.out.println("inside onsubmit id"+modelObject.getId()+"-name-"+modelObject.getName()+"-"+modelObject.getPrice());
                    productAdded=true;
                    success("Thankyou, product successfully added");
                    productAdded=true;
                    HomePage.this.product=formsProduct;
                    formsProduct =new Product();
                    target.add(ProductForm.this);
                    target.add(successFeedbackPanel);
                    target.add(failureFeedbackPanel);
                    target.add(productDetailsContainer);

                }

                @Override
                protected void onError(AjaxRequestTarget target) {
                    super.onError(target);
                    Product product=ProductForm.this.getModelObject();
                    System.out.println("inside onError id"+product.getId()+"-name-"+product.getName()+"-"+product.getPrice());
                    productAdded=false;
                    target.add(successFeedbackPanel);
                    target.add(failureFeedbackPanel);
                    target.add(productDetailsContainer);
                }

            });


        }


    }

}
