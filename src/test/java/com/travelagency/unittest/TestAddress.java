package com.travelagency.unittest;

import com.travelagency.model.Address;
import com.travelagency.model.City;
import com.travelagency.model.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestAddress {
    private static String Address = "Address";
    private static String Address2 = "Address2";
    private static String ZipCode = "ZipCode";
    private static String ZipCode2 = "ZipCode2";
    private Address address;

    @Before
    public void setUp() {
        City city = Mockito.mock(City.class);
        address = new Address(Address, city, ZipCode);
    }

    @After
    public void tearDown() {
        address = null;
    }

    @Test
    public void testGetAddress() {
        Assert.assertEquals(Address, address.getAddress());
    }

    @Test
    public void testSetAddress() {
        address.setAddress(Address2);
        Assert.assertEquals(Address2, address.getAddress());
    }

    @Test
    public void testGetZipCode() { Assert.assertEquals(ZipCode, address.getZipCode()); }

    @Test
    public void testSetZipCode() {
        address.setZipCode(ZipCode2);
        Assert.assertEquals(ZipCode2, address.getZipCode());
    }

    @Test
    public void testGetCity() { Assert.assertNotNull(address.getCity()); }

    @Test
    public void testSetCity() {
        City c = Mockito.mock(City.class);
        address.setCity(c);
        Assert.assertEquals(c, address.getCity());
    }
}
