package com.mycompany.service;

import com.mycompany.entity.Product;

import java.util.Iterator;
import java.util.List;

public interface IProductService {

    Product add(Product product);

    Iterator<Product> findAll(long first, long blockSize);

    long allProductsCount();

    Product findById(int id);

}
