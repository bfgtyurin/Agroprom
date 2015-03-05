package com.vtyurin.dao;

import com.vtyurin.domain.Item;
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
    public void save(Item item) {
        em.persist(item);
    }

    @Override
    public Item update(Item item) {
        return em.merge(item);
    }

    @Override
    public Item getById(Long id) {
        return em.find(Item.class, id);
    }
}
