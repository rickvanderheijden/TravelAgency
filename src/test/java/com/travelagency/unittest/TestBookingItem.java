package com.travelagency.unittest;

import com.travelagency.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBookingItem {
    private static final String Name = "Name";
    private static final Long Id = 10L;
    private static final Long ItemId = 20L;
    private static final String Description = "Description";
    private static final int NumberOfAttendees = 2;
    private static final int Price = 2199;

    private BookingItem bookingItem;

    @Before
    public void setUp() {
        bookingItem = new BookingItem();
    }

    @After
    public void tearDown() { bookingItem = null; }

    @Test
    public void testSetAndGetName() {
        bookingItem.setName(Name);
        Assert.assertEquals(Name, bookingItem.getName());
    }

    @Test
    public void testSetAndGetTripItemType() {
        bookingItem.setBookingItemType(BookingItemType.HOTEL);
        Assert.assertEquals(BookingItemType.HOTEL, bookingItem.getBookingItemType());
    }

    @Test
    public void testSetAndGetId() {
        bookingItem.setId(Id);
        Assert.assertEquals(Id, bookingItem.getId());
    }

    @Test
    public void testSetAndGetItemId() {
        bookingItem.setItemId(ItemId);
        Assert.assertEquals(ItemId, bookingItem.getItemId());
    }

    @Test
    public void testSetAndGetDescription() {
        bookingItem.setDescription(Description);
        Assert.assertEquals(Description, bookingItem.getDescription());
    }

    @Test
    public void testSetAndGetNumberOfAttendees() {
        bookingItem.setNumberOfAttendees(NumberOfAttendees);
        Assert.assertEquals(NumberOfAttendees, bookingItem.getNumberOfAttendees());
    }

    @Test
    public void testSetAndGetPrice() {
        bookingItem.setPrice(Price);
        Assert.assertEquals(Price, bookingItem.getPrice());
    }
}
