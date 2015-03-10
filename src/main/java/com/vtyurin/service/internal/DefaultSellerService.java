package com.vtyurin.service.internal;

import com.vtyurin.domain.Seller;
import com.vtyurin.repository.SellerRepository;
import com.vtyurin.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class DefaultSellerService implements SellerService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultSellerService.class);

    @Inject
    SellerRepository sellerRepository;

    @Override
    public Seller create(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller findById(Long id) {
        return sellerRepository.findOne(id);
    }

    @Override
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    @Override
    public void delete(Seller seller) {
        sellerRepository.delete(seller);
    }

    @Override
    public void delete(Long id) {
        sellerRepository.delete(id);
    }
}
