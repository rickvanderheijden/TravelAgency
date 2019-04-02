package com.travelagency.unittest;

import com.travelagency.model.Discount;
import com.travelagency.model.Trip;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestTrip {
    private static String Name = "Name";
    private static String Name2 = "Name2";
    private static int TotalPrice = 0;
    private static int TotalPrice2 = 2;
    private Trip trip;

    @Before
    public void setUp() {
        Discount discount = Mockito.mock(Discount.class);
        trip = new Trip(Name, TotalPrice, discount);
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
    public void testGetDiscount() { Assert.assertNotNull(trip.getDiscount()); }

    @Test
    public void testSetDiscount() {
        Discount d = Mockito.mock(Discount.class);
        trip.setDiscount(d);
        Assert.assertEquals(d, trip.getDiscount());
    }
}
