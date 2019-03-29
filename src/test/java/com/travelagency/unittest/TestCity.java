package com.travelagency.unittest;

import com.travelagency.model.City;
import com.travelagency.model.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestCity {

    private static final String CityName = "CityName";
    private City city;

    @Before
    public void setUp() {
        Country country = Mockito.mock(Country.class);
        city = new City(CityName, country);
    }

    @After
    public void tearDown() {
        city = null;
    }

    @Test
    public void testGetName() {
        Assert.assertEquals(CityName, city.getName());
    }

    @Test
    public void testGetCountry() {
        Assert.assertNotNull(city.getCountry());
    }
}
