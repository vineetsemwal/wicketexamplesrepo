package com.mycompany;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;
import java.util.List;

public class ProductsDataProvider implements IDataProvider<Product> {

    @Override
    public Iterator<? extends Product> iterator(long first, long count) {
        List<Product> list=ProductStore.get().findProducts(first, count);
        return list.listIterator();
    }

    @Override
    public long size() {
        return ProductStore.get().size();
    }

    @Override
    public IModel<Product> model(Product object) {
        return new DetachableProductModel(object.getId());
    }
}
