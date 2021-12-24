package com.mycompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductDao dao;

    @Transactional
    @Override
    public Product add(Product product) {
        return dao.add(product);
    }
    @Override
    public List<Product> findAll(long first, long blockSize) {
        return dao.findAll(first, blockSize);
    }

    @Override
    public long allProductsCount(){
     return dao.allProductsCount();
    }

    @Override
    public Product findById(int id) {
        return dao.findById(id);
    }
}
