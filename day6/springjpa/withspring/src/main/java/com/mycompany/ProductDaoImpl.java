package com.mycompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product add(Product product) {
       return em.merge(product);
    }

    @Override
    public List<Product> findAll(long first, long blockSize) {
        TypedQuery<Product>query=em.createQuery("from Product",Product.class);
         query.setFirstResult((int)first);
         query.setMaxResults((int)blockSize);
         return query.getResultList();
    }

    @Override
    public long allProductsCount() {
        TypedQuery<Long>query=em.createQuery("select count(id) from Product",Long.class);
        return query.getSingleResult();
    }

    @Override
    public Product findById(int id) {
        return em.find(Product.class,id);
    }
}
