package com.travelagency.rest;

import com.travelagency.controllers.CountryController;
import com.travelagency.model.Country;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/countries")
public class CountryResource {

    private final CountryController countryController;

    public CountryResource(CountryController countryController) {
        this.countryController = countryController;
    }

    @RequestMapping(value = "/continent/{continent}", method = RequestMethod.GET)
    public Optional<List<Country>> getByContinent(@PathVariable final String continent) {
        return countryController.getByContinent(continent);
    }
}
