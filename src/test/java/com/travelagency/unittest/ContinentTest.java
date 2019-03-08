package com.travelagency.unittest;

import com.travelagency.model.Continent;
import com.travelagency.model.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;


//TODO: Use interface and stub instead of class

public class ContinentTest {

    private Continent continent = new Continent();

    @Before
    public void setUp() {
        continent= new Continent();
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
        Country country = new Country("Netherlands", continent);

        Assert.assertTrue(continent.addCountry(country));
        Assert.assertFalse(continent.addCountry(country));

        Set<Country> countries = continent.getCountries();
        Assert.assertEquals(expectedSize, countries.size());
    }

    @Test
    public void testAddCountryWithExistingNameNotAllowed() {
        int expectedSize = 1;
        Country country = new Country("Netherlands", continent);
        Country anotherCountry = new Country("Netherlands", continent);

        Assert.assertTrue(continent.addCountry(country));
        Assert.assertFalse(continent.addCountry(anotherCountry));

        Set<Country> countries = continent.getCountries();
        Assert.assertEquals(expectedSize, countries.size());
    }
}
