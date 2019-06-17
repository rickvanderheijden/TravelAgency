package com.travelagency.unittest;

import com.travelagency.controllers.GeographyController;
import com.travelagency.controllers.TripController;
import com.travelagency.controllers.TripItemController;
import com.travelagency.model.*;
import com.travelagency.repository.DestinationRepository;
import com.travelagency.repository.TripItemRepository;
import com.travelagency.repository.TripRepository;
import com.travelagency.rest.DataTranfersObjects.TripSearchDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestTripItemController {
    private static final Long ValidId = 1L;
    private static final Long InvalidId = 2L;

    private final TripItemRepository tripItemRepository = Mockito.mock(TripItemRepository.class);
    private final GeographyController geographyController = Mockito.mock(GeographyController.class);


    private TripItemController tripItemController;
    private TripItem tripItemMock;
    private Address addressMock;
    private City cityMock;


    private List<TripItem> tripItemsToReturn;

    @Before
    public void setUp() {
        tripItemController = new TripItemController(tripItemRepository, geographyController);
        tripItemMock = Mockito.mock(TripItem.class);
        addressMock = Mockito.mock(Address.class);
        cityMock = Mockito.mock(City.class);

        tripItemsToReturn = new ArrayList<>();
        tripItemsToReturn.add(new Mockito().mock(TripItem.class));
        tripItemsToReturn.add(new Mockito().mock(TripItem.class));
        tripItemsToReturn.add(new Mockito().mock(TripItem.class));
    }

    @After
    public void tearDown() {
        tripItemController = null;
    }

    @Test
    public void testCreateTripItemWithNull() {
        Optional<TripItem> tripItem = tripItemController.createTripItem(null);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testCreateTripWithAddressNull() {
        when(tripItemMock.getAddress()).thenReturn(null);
        Optional<TripItem> tripItem = tripItemController.createTripItem(tripItemMock);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testCreateTripWithAddressValidButNotInRepositoryAndCityNull() {
        when(addressMock.getCity()).thenReturn(null);
        when(tripItemMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getAddress(any())).thenReturn(Optional.empty());
        when(geographyController.getCity(any())).thenReturn(Optional.empty());
        Optional<TripItem> tripItem = tripItemController.createTripItem(tripItemMock);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testCreateTripWithAddressValidButNotInRepositoryAndCityNotInRepository() {
        when (cityMock.getName()).thenReturn("City");
        when(addressMock.getCity()).thenReturn(cityMock);
        when(tripItemMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getAddress(any())).thenReturn(Optional.empty());
        when(geographyController.getCity(any())).thenReturn(Optional.empty());
        Optional<TripItem> tripItem = tripItemController.createTripItem(tripItemMock);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testCreateTripWithAddressValidButNotInRepositoryAndCityInRepository() {
        when (cityMock.getName()).thenReturn("City");
        when(addressMock.getCity()).thenReturn(cityMock);
        when(tripItemMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getAddress(any())).thenReturn(Optional.empty());
        when(geographyController.getCity(any())).thenReturn(Optional.of(cityMock));
        when(geographyController.createAddress(anyString(), anyString(), any())).thenReturn(Optional.of(addressMock));
        when(tripItemRepository.save(any())).thenReturn(tripItemMock);
        Optional<TripItem> tripItem = tripItemController.createTripItem(tripItemMock);
        Assert.assertTrue(tripItem.isPresent());
    }

    @Test
    public void testCreateTripWithAddressValidAndInRepository() {
        when(tripItemMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getAddress(any())).thenReturn(Optional.of(addressMock));
        when(tripItemRepository.save(any())).thenReturn(tripItemMock);
        Optional<TripItem> tripItem = tripItemController.createTripItem(tripItemMock);
        Assert.assertTrue(tripItem.isPresent());
    }


    @Test
    public void testUpdateTripWithNull() {
        Optional<TripItem> tripItem = tripItemController.updateTripItem(null);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testUpdateTripItemWithUnknownTripItem() {
        when(tripItemRepository.existsById(any())).thenReturn(false);
        Optional<TripItem> tripItem = tripItemController.updateTripItem(tripItemMock);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testUpdateTripItemWithKnownTripItemAddressNull() {
        when(tripItemRepository.existsById(any())).thenReturn(true);
        when(tripItemMock.getAddress()).thenReturn(null);
        Optional<TripItem> tripItem = tripItemController.updateTripItem(tripItemMock);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testUpdateTripItemWithKnownTripItemAddressNotInRepository() {
        when(tripItemRepository.existsById(any())).thenReturn(true);
        when(tripItemMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getAddress(any())).thenReturn(Optional.empty());
        Optional<TripItem> tripItem = tripItemController.updateTripItem(tripItemMock);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testUpdateTripItemWithKnownTripItemAddressInRepository() {
        when(tripItemRepository.existsById(any())).thenReturn(true);
        when(tripItemMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getAddress(any())).thenReturn(Optional.of(addressMock));
        when(tripItemRepository.save(any())).thenReturn(tripItemMock);
        Optional<TripItem> tripItem = tripItemController.updateTripItem(tripItemMock);
        Assert.assertTrue(tripItem.isPresent());
    }

    @Test
    public void testDeleteTripItemWithNull() {
        boolean result = tripItemController.deleteTripItem(null);
        Assert.assertFalse(result);
    }

    @Test
    public void testDeleteTripItemWithInvalidId() {
        when(tripItemRepository.existsById(InvalidId)).thenReturn(false);
        boolean result = tripItemController.deleteTripItem(InvalidId);
        Assert.assertFalse(result);
    }

    @Test
    public void testDeleteTripWithValidId() {
        when(tripItemRepository.existsById(ValidId)).thenReturn(true);
        boolean result = tripItemController.deleteTripItem(ValidId);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetByIdNull() {
        Optional<TripItem> tripItem = tripItemController.getById(null);
        Assert.assertFalse(tripItem.isPresent());
    }

    @Test
    public void testGetByIdValidId() {
        when(tripItemRepository.getOne(ValidId)).thenReturn(tripItemMock);
        Optional<TripItem> tripItem = tripItemController.getById(ValidId);
        Assert.assertTrue(tripItem.isPresent());
    }

    @Test
    public void testGetAllTripItemsNoTrips() {
        when(tripItemRepository.findAll()).thenReturn(null);
        Optional<List<TripItem>> tripItems = tripItemController.getAllTripItems();
        Assert.assertFalse(tripItems.isPresent());
    }

    @Test
    public void testGetAllTripItemsWithTripItems() {
        when(tripItemRepository.findAll()).thenReturn(tripItemsToReturn);
        Optional<List<TripItem>> tripItems = tripItemController.getAllTripItems();
        Assert.assertTrue(tripItems.isPresent());
        Assert.assertEquals(tripItemsToReturn.size(), tripItems.get().size());
    }

    @Test
    public void testGetByCityNameNull() {
        Optional<List<TripItem>> tripItems = tripItemController.getByCityName(null);
        Assert.assertFalse(tripItems.isPresent());
    }

    @Test
    public void testGetByCityNameCityValidNotInRepository() {
        when(tripItemRepository.getByAddressCityName(any())).thenReturn(null);
        Optional<List<TripItem>> tripItems = tripItemController.getByCityName("City");
        Assert.assertFalse(tripItems.isPresent());
    }
}
