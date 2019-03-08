package com.travelagency.unittest;

import com.travelagency.model.City;
import com.travelagency.model.Continent;
import com.travelagency.model.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.mockito.Mockito.when;


//TODO: Use interface and stub instead of class

public class TestCountry {

    private Country country;

    @Before
    public void setUp() {
        country = new Country("Test", new Continent("name"));
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
        City city = Mockito.mock(City.class);
        when(city.getName()).thenReturn("Eindhoven");

        Assert.assertTrue(country.addCity(city));
        Assert.assertFalse(country.addCity(city));

        Set<City> cities = country.getCities();
        Assert.assertEquals(expectedSize, cities.size());
    }

    @Test
    public void testAddCityWithExistingNameNotAllowed() {
        int expectedSize = 1;

        City city = Mockito.mock(City.class);
        City anotherCity = Mockito.mock(City.class);

        String name = "Eindhoven";
        when(city.getName()).thenReturn(name);
        when(anotherCity.getName()).thenReturn(name);

        Assert.assertTrue(country.addCity(city));
        Assert.assertFalse(country.addCity(anotherCity));

        Set<City> cities = country.getCities();
        Assert.assertEquals(expectedSize, cities.size());
    }
}


