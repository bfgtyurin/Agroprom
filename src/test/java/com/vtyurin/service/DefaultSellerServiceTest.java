package com.vtyurin.service;

import com.vtyurin.domain.Seller;
import com.vtyurin.repository.SellerRepository;
import com.vtyurin.service.internal.DefaultSellerService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSellerServiceTest {

    public static final Long ID = 1000L;
    public static final String NAME = "Dummy Seller";
    public static final String ADDRESS = "dummy country, dummy city, dummy street";
    public static final String EMAIL = "dummy@dummymail.com";
    public static final DateTime CREATED = DateTime.now();
    public static final DateTime UPDATED = DateTime.now();

    @Mock
    SellerRepository sellerRepositoryMock;

    @InjectMocks
    DefaultSellerService sellerService;

    private Seller modelObject;

    @Before
    public void setUp() {
        modelObject = new Seller();
        modelObject.setId(ID);
        modelObject.setName(NAME);
        modelObject.setEmail(EMAIL);
        modelObject.setAddress(ADDRESS);
        modelObject.setCreationTime(CREATED);
        modelObject.setModificationTime(UPDATED);
    }

    @Test
    public void create() throws Exception {
        when(sellerRepositoryMock.save(any(Seller.class))).thenReturn(modelObject);

        Seller created = new Seller();
        created.setName(NAME);
        created.setEmail(EMAIL);
        created.setAddress(ADDRESS);
        created.setCreationTime(CREATED);
        created.setModificationTime(UPDATED);

        Seller returned = sellerService.create(created);
        verify(sellerRepositoryMock, times(1)).save(created);
        verifyNoMoreInteractions(sellerRepositoryMock);
        assertNotNull(returned.getId());
        assertEquals(returned, modelObject);
    }

    @Test
    public void findById() {
        when(sellerRepositoryMock.findOne(ID)).thenReturn(modelObject);
        Seller returned = sellerService.findById(ID);

        verify(sellerRepositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(sellerRepositoryMock);

        assertEquals(modelObject, returned);
    }

    @Test
    public void findAll() {
        List<Seller> sellers = new ArrayList<>();
        when(sellerRepositoryMock.findAll()).thenReturn(sellers);

        List<Seller> returned = sellerService.findAll();

        verify(sellerRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(sellerRepositoryMock);

        assertEquals(sellers, returned);
    }
}