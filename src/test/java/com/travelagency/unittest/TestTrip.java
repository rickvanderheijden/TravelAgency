package com.travelagency.unittest;

import com.travelagency.model.Destination;
import com.travelagency.model.Trip;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestTrip {
    private static final String Name = "Name";
    private static final String Name2 = "Name2";
    private static final String Description = "Description";
    private static final String Summary = "Summary";
    private static final String ImageUrl= "ImageURL";
    private static final int TotalPrice = 0;
    private static final int TotalPrice2 = 2;
    private static final int Discount = 230;
    private static final int Discount2 = 130;
    private Trip trip;

    @Before
    public void setUp() {
        trip = new Trip(Name, Description, Summary, ImageUrl, TotalPrice, Discount);
    }

    @After
    public void tearDown() { trip = null; }

    @Test
    public void testGetName() { Assert.assertEquals(Name, trip.getName()); }

    @Test
    public void testSetName() {
        trip.setName(Name2);
        Assert.assertEquals(Name2, trip.getName());
    }

    @Test
    public void testGetTotalPrice() { Assert.assertEquals(TotalPrice, trip.getTotalPrice()); }

    @Test
    public void testSetTotalPrice() {
        trip.setTotalPrice(TotalPrice2);
        Assert.assertEquals(TotalPrice2, trip.getTotalPrice()); }

    @Test
    public void testGetDiscount() { Assert.assertEquals(Discount, trip.getDiscount()); }

    @Test
    public void testSetDiscount() {
        trip.setDiscount(Discount2);
        Assert.assertEquals(Discount2, trip.getDiscount());
    }

    @Test
    public void testGetDestinationsNotNullWhenNotSet() {
        Assert.assertNotNull(trip.getDestinations());
    }

    @Test
    public void testGetTripItemsNotNullWhenSetToNull() {
        trip.setDestinations(null);
        Assert.assertNotNull(trip.getDestinations());
    }

    @Test
    public void testGetDestinationsAfterAddingIdenticalDestinations() {
        Destination destination = Mockito.mock(Destination.class);
        trip.addDestination(destination);
        trip.addDestination(destination);

        Assert.assertEquals(1, trip.getDestinations().size());
    }

    @Test
    public void testGetDestinationsAfterAddingUnIdenticalDestinations() {
        Destination destination  = Mockito.mock(Destination.class);
        Destination destination2 = Mockito.mock(Destination.class);
        trip.addDestination(destination);
        trip.addDestination(destination2);

        Assert.assertEquals(2, trip.getDestinations().size());
    }
}
