package com.travelagency.controllers;

import com.travelagency.model.Address;
import com.travelagency.model.City;
import com.travelagency.model.Continent;
import com.travelagency.model.Country;
import com.travelagency.repository.AddressRepository;
import com.travelagency.repository.CityRepository;
import com.travelagency.repository.ContinentRepository;
import com.travelagency.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeographyController {

    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final ContinentRepository continentRepository;
    private final CountryRepository countryRepository;

    public GeographyController(AddressRepository addressRepository, CityRepository cityRepository,
                               ContinentRepository continentRepository, CountryRepository countryRepository) {
        this.addressRepository = addressRepository;
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

        return Optional.ofNullable(continentRepository.findByName(name));
    }

    public Optional<Country> createCountry(String name, Continent continent) {
        if(name == null) return Optional.empty();
        if(continent == null) return Optional.empty();

        Country country = new Country(name, continent);
        return Optional.of(countryRepository.save(country));
    }

    public Optional<Country> getCountry(String name) {
        if(name == null) return Optional.empty();

        return Optional.ofNullable(countryRepository.findByName(name));
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
        return Optional.ofNullable(cityRepository.findByName(name));
    }

    public Optional<Address> createAddress(String street, String zipCode, City city) {
        if(street == null) return Optional.empty();
        if(zipCode == null) return Optional.empty();
        if(city == null) return Optional.empty();

        Optional<City> cityInDatabase = getCity(city.getName());

        if (cityInDatabase.isPresent()) {
            Address address = new Address(street, zipCode, cityInDatabase.get());
            return Optional.of(addressRepository.save(address));
        }

        return Optional.empty();
    }

    public Optional<Address> getAddress(Address address) {
        if (address == null) return Optional.empty();

        Address result = addressRepository.findByAddressLineAndCityName(address.getAddressLine(), address.getCity().getName());

        return Optional.ofNullable(result);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    public List<Country> getAllCountries() { return countryRepository.findAll(); }
    public List<Continent> getAllContinents() { return continentRepository.findAll(); }
    public List<Country> getCountryByContinent(String continentName) {
        return countryRepository.findByContinent_Name(continentName);
    }
}
