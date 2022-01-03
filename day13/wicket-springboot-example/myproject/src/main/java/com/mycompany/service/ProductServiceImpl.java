package com.mycompany.service;

import com.mycompany.entity.Product;
import com.mycompany.exception.ProductNotFoundException;
import com.mycompany.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductRepository repository;


    @Override
    public Product add(Product product) {
        return repository.save(product);
    }
    @Override
    public Iterator<Product> findAll(long first, long blockSize) {
        long pageNum=first/blockSize;
        Pageable pageable= PageRequest.of((int)pageNum,(int)blockSize);
        Page<Product> page= repository.findAll(pageable);
         return page.iterator();
    }

    @Override
    public long allProductsCount(){
     return repository.count();
    }

    @Override
    public Product findById(int id) {
       Optional<Product> optional= repository.findById(id);
       if(optional.isPresent()){
           return optional.get();
       }
       throw new ProductNotFoundException("product not found for id="+id);
    }
}
