package com.vtyurin.dao;


import com.vtyurin.domain.Product;

public interface ItemDao {
    void save(Product product);

    Product update(Product product);

    Product getById(Long id);
}
