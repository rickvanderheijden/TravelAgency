package com.travelagency.controllers;

import com.travelagency.model.Country;
import com.travelagency.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.Optional;

@Service
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Optional<List<Country>> getByContinent(String continentName) {
        return Optional.of(countryRepository.findByContinent_Name(continentName));
    }
}
