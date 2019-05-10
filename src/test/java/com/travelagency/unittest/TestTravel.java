package com.travelagency.unittest;

import com.travelagency.model.Travel;
import com.travelagency.model.Trip;
import com.travelagency.model.TripItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

@SuppressWarnings("FieldCanBeLocal")
public class TestTravel {
    private Travel travel;
    private Trip trip;

    private final int TravelPrice = 2089;
    private final int TripPrice = 1449;

    @Before
    public void setUp() {
        trip = Mockito.mock(Trip.class);
        when (trip.getTotalPrice()).thenReturn(TripPrice);
        travel = new Travel(trip);
    }

    @After
    public void tearDown() { trip = null; }

    @Test
    public void testGetPriceReturnsTotalPriceWhenSet() {
        travel.setTotalPrice(TravelPrice);
        int result = travel.getTotalPrice();
        Assert.assertEquals(TravelPrice, result);
    }

    @Test
    public void testGetPriceReturnsTotalTripAndTripItemPriceWhenNotSet() {
        int result = travel.getTotalPrice();
        Assert.assertEquals(TripPrice, result);
    }

    @Test
    public void testGetTripItemsNotNullWhenNotSet() {
        Assert.assertNotNull(travel.getTripItems());
    }

    @Test
    public void testGetTripItemsAfterAddingIdenticalTripItems() {
        TripItem tripItem = Mockito.mock(TripItem.class);
        travel.addTripItem(tripItem);
        travel.addTripItem(tripItem);

        Assert.assertEquals(1, travel.getTripItems().size());
    }

    @Test
    public void testGetTripItemsAfterAddingUnidenticalTripItems() {
        TripItem tripItem  = Mockito.mock(TripItem.class);
        TripItem tripItem2 = Mockito.mock(TripItem.class);
        travel.addTripItem(tripItem);
        travel.addTripItem(tripItem2);

        Assert.assertEquals(2, travel.getTripItems().size());
    }
}
