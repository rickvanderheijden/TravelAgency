package com.travelagency.rest;

import com.travelagency.controllers.GeographyController;
import com.travelagency.model.City;
import com.travelagency.model.Continent;
import com.travelagency.model.Country;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geo")
public class GeographyResource {

    private final GeographyController geographyController;

    public GeographyResource(GeographyController geographyController) {
        this.geographyController = geographyController;
    }

    @RequestMapping(value = "/getCities", method = RequestMethod.GET)
    public List<City> getAllCities() {
        return geographyController.getAllCities();
    }

    @RequestMapping(value = "/getCountries", method = RequestMethod.GET)
    public List<Country> getAllCountries() {
        return geographyController.getAllCountries();
    }

    @RequestMapping(value = "/getContinents", method = RequestMethod.GET)
    public List<Continent> getAllContinents() {
        return geographyController.getAllContinents();
    }

    @RequestMapping(value = "/getCountryByContinent/{continent}", method = RequestMethod.GET)
    public List<Country> getByContinent(@PathVariable final String continent) {
        return geographyController.getCountryByContinent(continent);
    }
}
