package com.vtyurin.dao;


import com.vtyurin.domain.Item;

public interface ItemDao {
    Item save(Item item);
    Item update(Item item);
    Item getById(Long id);
}
