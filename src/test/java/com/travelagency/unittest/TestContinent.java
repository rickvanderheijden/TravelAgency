package com.travelagency.unittest;

import com.travelagency.model.Continent;
import com.travelagency.model.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class TestContinent {

    private static final String CountryName = "CountryName";
    private static final String ContinentName = "ContinentName";
    private Continent continent;

    @Before
    public void setUp() {
        continent= new Continent(ContinentName);
    }

    @After
    public void tearDown() {
        continent = null;
    }

    @Test
    public void testAddCountryNull() {
        Assert.assertFalse(continent.addCountry(null));
    }

    @Test
    public void testAddDuplicateCountryNotAllowed() {
        int expectedSize = 1;
        Country country = new Country(CountryName, continent);

        Assert.assertTrue(continent.addCountry(country));
        Assert.assertFalse(continent.addCountry(country));

        Set<Country> countries = continent.getCountries();
        Assert.assertEquals(expectedSize, countries.size());
    }

    @Test
    public void testAddCountryWithExistingNameNotAllowed() {
        int expectedSize = 1;
        Country country = new Country(CountryName, continent);
        Country anotherCountry = new Country(CountryName, continent);

        Assert.assertTrue(continent.addCountry(country));
        Assert.assertFalse(continent.addCountry(anotherCountry));

        Set<Country> countries = continent.getCountries();
        Assert.assertEquals(expectedSize, countries.size());
    }

    @Test
    public void testGetName() {
        Assert.assertEquals(ContinentName, continent.getName());
    }
}
