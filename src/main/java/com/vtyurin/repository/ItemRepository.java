package com.vtyurin.repository;

import com.vtyurin.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Product, Long> {
}
