package com.vtyurin.service;

import com.vtyurin.domain.Seller;

import java.util.List;

public interface SellerService {

    Seller create(Seller seller);

    Seller findById(Long id);

    List<Seller> findAll();

    void delete(Seller seller);

    void delete(Long id);
}
