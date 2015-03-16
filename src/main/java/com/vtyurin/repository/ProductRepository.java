package com.vtyurin.repository;

import com.vtyurin.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p order by id desc")
    List<Product> findAllOrderById();
}
