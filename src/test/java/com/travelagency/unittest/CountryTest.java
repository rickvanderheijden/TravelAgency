package com.travelagency.unittest;

import com.travelagency.model.Continent;
import com.travelagency.model.Country;
import com.travelagency.model.ICity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;


//TODO: Use interface and stub instead of class

public class CountryTest {

    private Country country;

    @Before
    public void setUp() {
        country = new Country("Test", new Continent());
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
        CityStub city = new CityStub();
        city.name = "Eindhoven";

        Assert.assertTrue(country.addCity(city));
        Assert.assertFalse(country.addCity(city));

        Set<ICity> cities = country.getCities();
        Assert.assertEquals(expectedSize, cities.size());
    }

    @Test
    public void testAddCityWithExistingNameNotAllowed() {
        int expectedSize = 1;
        CityStub city = new CityStub();
        CityStub anotherCity = new CityStub();
        city.name = "Eindhoven";
        anotherCity.name = "Eindhoven";

        Assert.assertTrue(country.addCity(city));
        Assert.assertFalse(country.addCity(anotherCity));

        Set<ICity> cities = country.getCities();
        Assert.assertEquals(expectedSize, cities.size());
    }

    private class CityStub implements ICity {

        public String name;
        public Country country;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Country getCountry() {
            return country;
        }
    }
}


