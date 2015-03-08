package com.vtyurin.service.internal;

import com.vtyurin.domain.Product;
import com.vtyurin.repository.ItemRepository;
import com.vtyurin.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class DefaultItemService implements ItemService {
    private final static Logger logger = LoggerFactory.getLogger(DefaultSellerService.class);

    @Inject
    ItemRepository itemRepository;

    @Override
    public List<Product> findAll() {
        List<Product> all = itemRepository.findAll();
        for (Product product : all) {
            logger.info(product.getName());

        }
        return all;
    }
}
