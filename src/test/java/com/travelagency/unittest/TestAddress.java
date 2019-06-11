package com.travelagency.unittest;

import com.travelagency.model.Address;
import com.travelagency.model.City;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestAddress {
    private static final String AddressLine = "AddressLine";
    private static final String AddressLine2 = "AddressLine2";
    private static final String ZipCode = "ZipCode";
    private static final String ZipCode2 = "ZipCode2";
    private static final Long Id = 1L;
    private Address address;

    @Before
    public void setUp() {
        City city = Mockito.mock(City.class);
        address = new Address(AddressLine, ZipCode, city);
    }

    @After
    public void tearDown() {
        address = null;
    }

    @Test
    public void testGetIdInitial() {
        Assert.assertNull(address.getId());
    }

    @Test
    public void testSetAndGetId() {
        address.setId(Id);
        Assert.assertEquals(Id, address.getId());
    }

    @Test
    public void testGetAddress() {
        Assert.assertEquals(AddressLine, address.getAddressLine());
    }

    @Test
    public void testSetAddress() {
        address.setAddressLine(AddressLine2);
        Assert.assertEquals(AddressLine2, address.getAddressLine());
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
