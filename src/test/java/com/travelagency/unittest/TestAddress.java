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
    private static String ZipCode = "ZipCode";
    private Address address;

    @Before
    public void setUp() {
        City city = Mockito.mock(City.class);
        Country country = Mockito.mock(Country.class);
        address = new Address(Address, city, ZipCode, country);
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
    public void testGetZipCode() { Assert.assertEquals(ZipCode, address.getZipCode()); }

    @Test
    public void testGetCountry() { Assert.assertNotNull(address.getCountry()); }

    @Test
    public void testGetCity() { Assert.assertNotNull(address.getCity()); }
}
