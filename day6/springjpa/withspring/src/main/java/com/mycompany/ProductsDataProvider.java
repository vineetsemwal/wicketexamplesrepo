package com.mycompany;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component("productsdp")
public class ProductsDataProvider implements IDataProvider<Product> {
    @Autowired
    private IProductService service;

    public ProductsDataProvider(){
    }

    @Override
    public Iterator<? extends Product> iterator(long first, long count) {
        List<Product> list=service.findAll(first, count);
        return list.listIterator();
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
