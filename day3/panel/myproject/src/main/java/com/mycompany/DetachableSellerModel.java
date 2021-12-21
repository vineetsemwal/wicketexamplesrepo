package com.mycompany;

import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableSellerModel extends LoadableDetachableModel<Seller> {

    private long sellerId;

    public DetachableSellerModel(long sellerId){
        this.sellerId=sellerId;
    }

    @Override
    protected Seller load() {
        return SellerStore.get().getSellerById(sellerId);
    }
}
