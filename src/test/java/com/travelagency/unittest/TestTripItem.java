package com.travelagency.unittest;

import com.travelagency.model.Address;
import com.travelagency.model.TripItem;
import com.travelagency.model.TripItemType;
import com.travelagency.util.Dateparser;
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

    private static final Date AvailableFrom = Dateparser.parseDate("2019-01-01");
    private static final Date AvailableTo = Dateparser.parseDate("2019-12-31");
    private static final String AvailableFromString = "2019-01-01";
    private static final String AvailableToString = "2019-12-31";
    private static final Date NewDate =Dateparser.parseDate("2019-01-02");
    private TripItem tripItem;

    @Before
    public void setUp() {
        Address address = Mockito.mock(Address.class);
        tripItem = new TripItem(tripItemType, Name, Description, null, address, Price, AvailableFromString, AvailableToString);
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
    public void testGetAvailableFrom() { Assert.assertEquals(AvailableFrom, tripItem.getAvailableFrom()); }

    @Test
    public void testSetAvailableFrom() {
        tripItem.setAvailableFrom(NewDate);
        Assert.assertEquals(NewDate, tripItem.getAvailableFrom());
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

}
