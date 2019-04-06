package com.travelagency.unittest;

import com.travelagency.model.Trip;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTrip {
    private static String Name = "Name";
    private static String Name2 = "Name2";
    private static String Description = "Description";
    private static String ImageUrl= "ImageURL";
    private static int TotalPrice = 0;
    private static int TotalPrice2 = 2;
    private static int Discount = 230;
    private static int Discount2 = 130;
    private Trip trip;

    @Before
    public void setUp() {
        trip = new Trip(Name, Description, ImageUrl, TotalPrice, Discount);
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
}
