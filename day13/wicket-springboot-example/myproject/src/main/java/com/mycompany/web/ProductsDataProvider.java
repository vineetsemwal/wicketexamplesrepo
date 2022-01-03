package com.mycompany.web;

import com.mycompany.entity.Product;
import com.mycompany.service.IProductService;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;


public class ProductsDataProvider implements IDataProvider<Product> {
    @SpringBean
    private IProductService service;


    public ProductsDataProvider(){
        Injector.get().inject(this);
    }

    @Override
    public Iterator<? extends Product> iterator(long first, long count) {
        Iterator<Product> iterator=service.findAll(first, count);
        return iterator;
    }

    @Override
    public long size() {
        return service.allProductsCount();
    }

    @Override
    public IModel<Product> model(Product object) {
        return new DetachableProductModel(object.getId());
    }
}
