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
    private static String Name = "Name";
    private static ServiceType serviceType;
    private static int Price = 0;
    private static Date Date = new Date();
    private Service service;

    @Before
    public void setUp() {
        Address address = Mockito.mock(Address.class);
        service = new Service(serviceType, Name, address, Price, Date);
    }

    @After
    public void tearDown() { service = null; }

    @Test
    public void testGetName() {
        Assert.assertEquals(Name, service.getName());
    }

    @Test
    public void testGetServiceType() { Assert.assertEquals(serviceType, service.getServiceType()); }

    @Test
    public void testGetPrice() { Assert.assertEquals(Price, service.getPrice()); }

    @Test
    public void testGetDate() { Assert.assertEquals(Date, service.getDate()); }

    @Test
    public void testGetAddress() { Assert.assertNotNull(service.getAddress()); }

}
