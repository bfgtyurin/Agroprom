package com.vtyurin.service.internal;

import com.vtyurin.domain.Product;
import com.vtyurin.repository.ProductRepository;
import com.vtyurin.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class DefaultProductService implements ProductService {
    private final static Logger logger = LoggerFactory.getLogger(DefaultSellerService.class);

    @Inject
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        List<Product> all = productRepository.findAll();
        for (Product product : all) {
            logger.info(product.getName());

        }
        return all;
    }
}
