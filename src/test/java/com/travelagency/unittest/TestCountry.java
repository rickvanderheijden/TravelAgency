package com.travelagency.unittest;

import com.travelagency.model.City;
import com.travelagency.model.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;


//TODO: Use interface and stub instead of class

public class TestCountry {

    private Country country = new Country("Test");

    @Before
    public void setUp() {
        country = new Country("Test");
    }

    @After
    public void tearDown() {
        country = null;
    }

    @Test
    public void testAddCityNull() {
        Assert.assertFalse(country.addCity(null));
    }

    @Test
    public void testAddDuplicateCityNotAllowed() {
        int expectedSize = 1;
        City city = new City("Eindhoven");

        Assert.assertTrue(country.addCity(city));
        Assert.assertFalse(country.addCity(city));

        Set<City> cities = country.getCities();
        Assert.assertEquals(expectedSize, cities.size());
    }

    @Test
    public void testAddCityWithExistingNameNotAllowed() {
        int expectedSize = 1;
        City city = new City("Eindhoven");
        City anotherCity = new City("Eindhoven");

        Assert.assertTrue(country.addCity(city));
        Assert.assertFalse(country.addCity(anotherCity));

        Set<City> cities = country.getCities();
        Assert.assertEquals(expectedSize, cities.size());
    }
}
