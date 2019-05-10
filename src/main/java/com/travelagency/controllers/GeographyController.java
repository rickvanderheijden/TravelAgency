package com.travelagency.controllers;

import com.travelagency.model.City;
import com.travelagency.model.Continent;
import com.travelagency.model.Country;
import com.travelagency.repository.CityRepository;
import com.travelagency.repository.ContinentRepository;
import com.travelagency.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeographyController {

    private final CityRepository cityRepository;
    private final ContinentRepository continentRepository;
    private final CountryRepository countryRepository;

    public GeographyController(CityRepository cityRepository, ContinentRepository continentRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.continentRepository = continentRepository;
        this.countryRepository = countryRepository;
    }

    public Optional<Continent> createContinent(String name) {
        if(name == null) return Optional.empty();

        Continent continent = new Continent(name);
        return Optional.of(continentRepository.save(continent));
    }

    public Optional<Continent> getContinent(String name) {
        if(name == null) return Optional.empty();

        return Optional.of(continentRepository.findByName(name));
    }

    public Optional<Country> createCountry(String name, Continent continent) {
        if(name == null) return Optional.empty();
        if(continent == null) return Optional.empty();

        Country country = new Country(name, continent);
        return Optional.of(countryRepository.save(country));
    }

    public Optional<Country> getCountry(String name) {
        if(name == null) return Optional.empty();

        return Optional.of(countryRepository.findByName(name));
    }

    public Optional<City> createCity(String name, Country country) {
        if(name == null) return Optional.empty();
        if(country == null) return Optional.empty();

        City city = new City(name, country);

        Optional<City> result = Optional.of(cityRepository.save(city));

        System.out.println(result.get().getId());
        return result;
    }

    public Optional<City> getCity(String name) {
        if(name == null) return Optional.empty();
        return Optional.of(cityRepository.findByName(name));
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    public List<Country> getAllCountries() { return countryRepository.findAll(); }
    public List<Continent> getAllContinents() { return continentRepository.findAll(); }
}
