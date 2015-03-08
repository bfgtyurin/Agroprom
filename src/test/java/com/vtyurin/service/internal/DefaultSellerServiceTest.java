package com.vtyurin.service.internal;

import com.vtyurin.config.AppConfig;
import com.vtyurin.config.DataConfigForTest;
import com.vtyurin.domain.Seller;
import com.vtyurin.service.SellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DataConfigForTest.class})
public class DefaultSellerServiceTest {

    @Inject
    SellerService sellerService;

    @Test
    public void testCreate() throws Exception {
        Seller seller = new Seller();
        seller.setName("Dummy Test");
        seller.setEmail("dummytest@testmail.com");
        seller.setAddress("Dummy Street Dummy Country");
        seller = sellerService.create(seller);
        assertNotNull(seller.getId());
    }
}