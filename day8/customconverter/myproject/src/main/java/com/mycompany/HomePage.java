package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
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

    private Product product=new Product();

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public HomePage(final PageParameters parameters) {
        super(parameters);
    }



    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new ProductForm("productform"));

        add(new Label("pid",()->{
            if(product==null){
                return "";
            }
            return product.getId();
        }));

        add(new Label("pname",()->{
            if(product==null){
                return "";
            }
            return product.getName();
        } ));

        add(new Label("price",()->{
            if(product==null){
                return "";
            }
            return product.getPrice();
        }));

        add(new Label("categoryName", ()->{
            if(product==null||product.getCategory()==null){
                return "";
            }
            return product.getCategory().name();
        }));
        add(new Label("categoryId", ()->{
            if(product==null||product.getCategory()==null){
                return "";
            }
            return product.getCategory().getCategoryId();
        }));
        add(new Label("categoryShort", ()->{
            if(product==null||product.getCategory()==null){
                return "";
            }
            return product.getCategory().getShortName();
        }));

    }



    class ProductForm extends Form<Void> {
        public ProductForm(String id) {
            super(id);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            IModel<Product>productModel=LambdaModel.of(HomePage.this::getProduct,HomePage.this::setProduct);
            TextField<Product>productField=new TextField<>("productinput",productModel);
            add(productField);
            productField.setRequired(true);
            productField.setType(Product.class);
            IModel<Category>categoryModel=LambdaModel.of(HomePage.this::getCategory,HomePage.this::setCategory);
            TextField<Category>categoryField=new TextField("category",categoryModel);
            categoryField.setType(Category.class);
            categoryField.setRequired(true);
            add(categoryField);
            add(new Button("submit"));
        }

        @Override
        protected void onSubmit() {
            product.setCategory(getCategory());
            System.out.println("insde on submit, catgeory="+product.getCategory());
        }
    }

}
