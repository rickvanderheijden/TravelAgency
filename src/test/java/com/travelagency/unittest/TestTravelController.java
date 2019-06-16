package com.travelagency.unittest;

import com.travelagency.controllers.*;
import com.travelagency.model.*;
import com.travelagency.repository.TravelRepository;
import com.travelagency.repository.TripItemRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestTravelController {
    private static final Long ValidId = 1L;

    private final TravelRepository travelRepository = Mockito.mock(TravelRepository.class);
    private final TripItemRepository tripItemRepository = Mockito.mock(TripItemRepository.class);

    private TravelController travelController;
    private Travel travelMock;

    private List<Travel> travelsToReturn;
    private List<TripItem> tripItemsToReturn;

    @Before
    public void setUp() {
        travelController = new TravelController(travelRepository, tripItemRepository);
        travelMock = Mockito.mock(Travel.class);

        travelsToReturn = new ArrayList<>();
        travelsToReturn.add(new Mockito().mock(Travel.class));
        travelsToReturn.add(new Mockito().mock(Travel.class));
        travelsToReturn.add(new Mockito().mock(Travel.class));

        tripItemsToReturn = new ArrayList<>();
        tripItemsToReturn.add(new Mockito().mock(TripItem.class));
        tripItemsToReturn.add(new Mockito().mock(TripItem.class));
    }

    @After
    public void tearDown() {
        travelController = null;
    }

    @Test
    public void testCreateTravelWithNull() {
        Optional<Travel> travel = travelController.createTravel(null);
        Assert.assertFalse(travel.isPresent());
    }

    @Test
    public void testCreateTravelWithTripItemsItemsNull() {
        when(travelMock.getTripItems()).thenReturn(null);
        Optional<Travel> travel = travelController.createTravel(travelMock);
        Assert.assertFalse(travel.isPresent());
    }

    @Test
    public void testCreateTravelWithTripItemsValidButNotInRepository() {
        when(travelMock.getTripItems()).thenReturn(tripItemsToReturn);
        when(tripItemRepository.findById(any())).thenReturn(Optional.empty());
        when(travelRepository.save(any())).thenReturn(travelMock);
        Optional<Travel> travel = travelController.createTravel(travelMock);
        Assert.assertTrue(travel.isPresent());
    }

    @Test
    public void testGetByIdNull() {
        Optional<Travel> travel = travelController.getById(null);
        Assert.assertFalse(travel.isPresent());

    }

    @Test
    public void testGetByIdValidId() {
        when(travelRepository.getOne(ValidId)).thenReturn(travelMock);
        Optional<Travel> travel = travelController.getById(ValidId);
        Assert.assertTrue(travel.isPresent());
    }

    @Test
    public void testGetAllTravelsNoTravels() {
        when(travelRepository.findAll(any(Pageable.class))).thenReturn(null);
        Optional<List<Travel>> travels = travelController.getAllTravels(10);
        Assert.assertFalse(travels.isPresent());
    }

    @Test
    public void testGetAllTravelsWithTravelsLimitZero() {
        when(travelRepository.findAll()).thenReturn(travelsToReturn);
        Optional<List<Travel>> travels = travelController.getAllTravels(0);
        Assert.assertFalse(travels.isPresent());
    }

    @Test
    public void testGetAllTravelsWithTravelsLimitTen() {
        when(travelRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(travelsToReturn));
        Optional<List<Travel>> travels = travelController.getAllTravels(10);
        Assert.assertTrue(travels.isPresent());
        Assert.assertEquals(travelsToReturn.size(), travels.get().size());
    }
}
