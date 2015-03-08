package com.vtyurin.service;

import com.vtyurin.domain.Seller;

import java.util.List;

public interface SellerService {

    public Seller create(Seller seller);

    public Seller findById(Long id);

    public List<Seller> findAll();

    public void delete(Seller seller);

    public void delete(Long id);
}
