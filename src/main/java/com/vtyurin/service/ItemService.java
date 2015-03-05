package com.vtyurin.service;

import com.vtyurin.dao.ItemDao;
import com.vtyurin.domain.Category;
import com.vtyurin.domain.CategoryItemRelationship;
import com.vtyurin.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Inject
    private ItemDao itemDao;

    public Item findOne(Long id) {
        return itemDao.getById(id);
    }

    public void save(Item item) {
        itemDao.save(item);
    }

    public Item update(Item item) {
        return itemDao.update(item);
    }

    public Item changeCategory() {
        return null;
    }

    public Set<Category> getCategoriesById(Long id) {
        Set<Category> categories = new HashSet<>();
        Item item = itemDao.getById(id);
        Set<CategoryItemRelationship> relationships = item.getCategoryItemRelationship();
        for (CategoryItemRelationship relationship : relationships) {
            categories.add(relationship.getCategory());
            logger.info(relationship.getCategory().getName());
        }

        return categories;
    }


}
