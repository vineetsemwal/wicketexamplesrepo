package com.mycompany;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.Objects;

public class DetachableProductModel extends LoadableDetachableModel<Product> {

    private int productId;
    public DetachableProductModel(int productId){
        this.productId=productId;
    }

    @Override
    protected Product load() {
        return ProductStore.get().getById(productId);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this instanceof IModel) return false;
        DetachableProductModel that = (DetachableProductModel) o;
        return productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
