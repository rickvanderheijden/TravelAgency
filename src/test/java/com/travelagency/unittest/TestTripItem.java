package com.travelagency.unittest;

import com.travelagency.model.Address;
        import com.travelagency.model.TripItem;
        import com.travelagency.model.TripItemType;
        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.Mockito;

        import java.util.Date;

public class TestTripItem {
    private static final String Name = "Name";
    private static final String Name2 = "Name2";
    private static final String Description = "Description";
    private static TripItemType tripItemType;
    private static TripItemType tripItemType2;
    private static final int Price = 0;
    private static final int Price2 = 2;
    private static final int MinimumNumberOfAttendees = 3;
    private static final int MinimumNumberOfAttendeesDefault = 1;
    private static final int MaximumNumberOfAttendees = 3;
    private static final int MaximumNumberOfAttendeesDefault = 8;
    private static final int NumberOfAttendees = 3;
    private static final int NumberOfAttendeesDefault = 8;

    private static final Date Date = new Date();
    private static final Date Date2 = new Date();
    private TripItem tripItem;

    @Before
    public void setUp() {
        Address address = Mockito.mock(Address.class);
        tripItem = new TripItem(tripItemType, Name, Description, null, address, Price, Date, NumberOfAttendeesDefault);
    }

    @After
    public void tearDown() { tripItem = null; }

    @Test
    public void testGetName() { Assert.assertEquals(Name, tripItem.getName()); }

    @Test
    public void testSetName() {
        tripItem.setName(Name2);
        Assert.assertEquals(Name2, tripItem.getName());
    }

    @Test
    public void testGetTripItemType() { Assert.assertEquals(tripItemType, tripItem.getTripItemType()); }

    @Test
    public void testSetTripItemType() {
        tripItem.setTripItemType(tripItemType2);
        Assert.assertEquals(tripItemType2, tripItem.getTripItemType());
    }

    @Test
    public void testGetPrice() { Assert.assertEquals(Price, tripItem.getPrice()); }

    @Test
    public void testSetPrice() {
        tripItem.setPrice(Price2);
        Assert.assertEquals(Price2, tripItem.getPrice()); }

    @Test
    public void testGetDate() { Assert.assertEquals(Date, tripItem.getDate()); }

    @Test
    public void testSetDate() {
        tripItem.setDate(Date2);
        Assert.assertEquals(Date2, tripItem.getDate());
    }

    @Test
    public void testGetAddress() { Assert.assertNotNull(tripItem.getAddress()); }

    @Test
    public void testSetAddress() {
        Address a = Mockito.mock(Address.class);
        tripItem.setAddress(a);
        Assert.assertEquals(a, tripItem.getAddress());
    }

    @Test
    public void testGetMinimumNumberOfAttendees() { Assert.assertEquals(MinimumNumberOfAttendeesDefault, tripItem.getMinimumNumberOfAttendees()); }

    @Test
    public void testSetMinimumNumberOfAttendees() {
        tripItem.setMinimumNumberOfAttendees(MinimumNumberOfAttendees);
        Assert.assertEquals(MinimumNumberOfAttendees, tripItem.getMinimumNumberOfAttendees()); }

    @Test
    public void testGetMaximumNumberOfAttendees() {
        Assert.assertEquals(MaximumNumberOfAttendeesDefault, tripItem.getMaximumNumberOfAttendees());
    }

    @Test
    public void testSetMaximumNumberOfAttendees() {
        tripItem.setMaximumNumberOfAttendees(MaximumNumberOfAttendees);
        Assert.assertEquals(MaximumNumberOfAttendees, tripItem.getMaximumNumberOfAttendees()); }

    @Test
    public void testNumberOfAttendees() {
        Assert.assertEquals(NumberOfAttendeesDefault, tripItem.getNumberOfAttendees());
    }

    @Test
    public void testSetNumberOfAttendees() {
        tripItem.setNumberOfAttendees(NumberOfAttendees);
        Assert.assertEquals(NumberOfAttendees, tripItem.getNumberOfAttendees()); }
}
