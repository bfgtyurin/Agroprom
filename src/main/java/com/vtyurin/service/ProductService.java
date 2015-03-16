package com.vtyurin.service;

import com.vtyurin.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<Product> findAllOrderById();

    Product create(Product product);

    void delete(Long id);
}
