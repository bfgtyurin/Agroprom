package com.vtyurin.dao;

import com.vtyurin.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class JpaItemDao implements ItemDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Product product) {
        em.persist(product);
    }

    @Override
    public Product update(Product product) {
        return em.merge(product);
    }

    @Override
    public Product getById(Long id) {
        return em.find(Product.class, id);
    }

}
