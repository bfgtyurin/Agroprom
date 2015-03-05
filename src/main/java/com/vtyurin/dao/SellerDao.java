package com.vtyurin.dao;

import com.vtyurin.domain.Seller;

public interface SellerDao {

    void addSeller(Seller seller);

    Seller getSellerById(Long id);
}
