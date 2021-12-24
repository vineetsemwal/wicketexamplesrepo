package com.mycompany;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

public class DetachableProductModel extends LoadableDetachableModel<Product> {

    @SpringBean
    private IProductService productService;

    private int productId;

    public DetachableProductModel(int productId){
        Injector.get().inject(this);
        this.productId=productId;

    }

    @Override
    protected Product load() {
        return productService.findById(productId);
    }
}
