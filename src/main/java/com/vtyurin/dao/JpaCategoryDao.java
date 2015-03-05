package com.vtyurin.dao;

import com.vtyurin.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class JpaCategoryDao implements CategoryDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public void save(Category category) {
        em.persist(category);
    }

    @Override
    public Category update(Category category) {
        return em.merge(category);
    }

    @SuppressWarnings("unchecked")
    public List<Category> findNestedCategoriesById(Long id) {
        Query query = em.createNamedQuery("Category.findByParentId").setParameter("id", id);
        return query.getResultList();
    }
}
