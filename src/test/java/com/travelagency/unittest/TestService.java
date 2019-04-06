package com.travelagency.unittest;

        import com.travelagency.model.Address;
        import com.travelagency.model.Service;
        import com.travelagency.model.ServiceType;
        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.Mockito;

        import java.util.Date;

public class TestService {
    private static final String Name = "Name";
    private static final String Name2 = "Name2";
    private static ServiceType serviceType;
    private static ServiceType serviceType2;
    private static final int Price = 0;
    private static final int Price2 = 2;
    private static final Date Date = new Date();
    private static final Date Date2 = new Date();
    private Service service;

    @Before
    public void setUp() {
        Address address = Mockito.mock(Address.class);
        service = new Service(serviceType, Name, address, Price, Date);
    }

    @After
    public void tearDown() { service = null; }

    @Test
    public void testGetName() { Assert.assertEquals(Name, service.getName()); }

    @Test
    public void testSetName() {
        service.setName(Name2);
        Assert.assertEquals(Name2, service.getName());
    }

    @Test
    public void testGetServiceType() { Assert.assertEquals(serviceType, service.getServiceType()); }

    @Test
    public void testSetServiceType() {
        service.setServiceType(serviceType2);
        Assert.assertEquals(serviceType2, service.getServiceType());
    }

    @Test
    public void testGetPrice() { Assert.assertEquals(Price, service.getPrice()); }

    @Test
    public void testSetPrice() {
        service.setPrice(Price2);
        Assert.assertEquals(Price2, service.getPrice()); }

    @Test
    public void testGetDate() { Assert.assertEquals(Date, service.getDate()); }

    @Test
    public void testSetDate() {
        service.setDate(Date2);
        Assert.assertEquals(Date2, service.getDate());
    }

    @Test
    public void testGetAddress() { Assert.assertNotNull(service.getAddress()); }

    @Test
    public void testSetAddress() {
        Address a = Mockito.mock(Address.class);
        service.setAddress(a);
        Assert.assertEquals(a, service.getAddress());
    }

}
