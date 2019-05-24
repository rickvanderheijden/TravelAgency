package com.travelagency.controllers;

import com.travelagency.model.City;
import com.travelagency.model.Destination;
import com.travelagency.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationController {

    private final DestinationRepository destinationRepository;
    private final GeographyController geographyController;

    public DestinationController(DestinationRepository destinationRepository, GeographyController geographyController) {
        this.destinationRepository= destinationRepository;
        this.geographyController = geographyController;
    }

    public Optional<Destination> createDestination(Destination destination) {
        if (destination == null) return Optional.empty();

        City city = destination.getCity();
        if (city == null) return Optional.empty();

        Optional<City> cityInDatabase = geographyController.getCity(city.getName());
        if (!cityInDatabase.isPresent()){
            return Optional.empty();
        } else {
            destination.setCity(cityInDatabase.get());
        }

        return Optional.of(destinationRepository.save(destination));
    }

    public Destination getById(Long id) {
        return destinationRepository.getOne(id);
    }

    public Optional<Destination> getByCityName(String cityName) {
        return Optional.ofNullable(destinationRepository.getByCityName(cityName));
    }

    public Optional<List<Destination>> getAllDestinations() {
        return Optional.of(destinationRepository.findAll());
    }
}
