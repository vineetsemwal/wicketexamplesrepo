package com.mycompany;

import org.apache.wicket.model.LoadableDetachableModel;


public class DetachableProductPriceModel extends LoadableDetachableModel<Double> {

    private int productId;

    public DetachableProductPriceModel(int productId){
        this.productId=productId;
    }

     @Override
    public Double load(){
      ProductStore store=ProductStore.get();
      Product product= store.getById(productId);
      return product.getPrice();
    }
}
