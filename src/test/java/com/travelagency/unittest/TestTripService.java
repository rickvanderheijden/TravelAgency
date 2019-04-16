package com.travelagency.unittest;

        import com.travelagency.model.Address;
        import com.travelagency.model.TripService;
        import com.travelagency.model.ServiceType;
        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.Mockito;

        import java.util.Date;

public class TestTripService {
    private static final String Name = "Name";
    private static final String Name2 = "Name2";
    private static final String Description = "Description";
    private static final String ImageUrl = "ImageUrl";
    private static ServiceType serviceType;
    private static ServiceType serviceType2;
    private static final int Price = 0;
    private static final int Price2 = 2;
    private static final Date Date = new Date();
    private static final Date Date2 = new Date();
    private TripService tripService;

    @Before
    public void setUp() {
        Address address = Mockito.mock(Address.class);
        tripService = new TripService(serviceType, Name, Description, ImageUrl, address, Price, Date);
    }

    @After
    public void tearDown() { tripService = null; }

    @Test
    public void testGetName() { Assert.assertEquals(Name, tripService.getName()); }

    @Test
    public void testSetName() {
        tripService.setName(Name2);
        Assert.assertEquals(Name2, tripService.getName());
    }

    @Test
    public void testGetServiceType() { Assert.assertEquals(serviceType, tripService.getServiceType()); }

    @Test
    public void testSetServiceType() {
        tripService.setServiceType(serviceType2);
        Assert.assertEquals(serviceType2, tripService.getServiceType());
    }

    @Test
    public void testGetPrice() { Assert.assertEquals(Price, tripService.getPrice()); }

    @Test
    public void testSetPrice() {
        tripService.setPrice(Price2);
        Assert.assertEquals(Price2, tripService.getPrice()); }

    @Test
    public void testGetDate() { Assert.assertEquals(Date, tripService.getDate()); }

    @Test
    public void testSetDate() {
        tripService.setDate(Date2);
        Assert.assertEquals(Date2, tripService.getDate());
    }

    @Test
    public void testGetAddress() { Assert.assertNotNull(tripService.getAddress()); }

    @Test
    public void testSetAddress() {
        Address a = Mockito.mock(Address.class);
        tripService.setAddress(a);
        Assert.assertEquals(a, tripService.getAddress());
    }

}
