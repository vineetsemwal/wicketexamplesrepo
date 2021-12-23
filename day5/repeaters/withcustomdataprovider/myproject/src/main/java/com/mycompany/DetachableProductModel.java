package com.mycompany;

import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableProductModel extends LoadableDetachableModel<Product> {

    private int productId;
    public DetachableProductModel(int productId){
        this.productId=productId;
    }

    @Override
    protected Product load() {
        return ProductStore.get().getById(productId);
    }
}
