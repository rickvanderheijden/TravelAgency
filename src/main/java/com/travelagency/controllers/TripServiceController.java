package com.travelagency.controllers;

import com.travelagency.model.TripService;
import com.travelagency.repository.TripServiceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripServiceController {

    private final TripServiceRepository tripServiceRepository;

    public TripServiceController(TripServiceRepository tripServiceRepository) {
        this.tripServiceRepository = tripServiceRepository; }

    public Optional<TripService> createService(TripService tripService) {
        return Optional.of(tripServiceRepository.save(tripService));
    }

    public TripService getById(Long id) {
        return this.tripServiceRepository.getOne(id);
    }

    public Optional<TripService> getFirst() {
        return tripServiceRepository.findAll().stream().findFirst();
    }

    public TripService updateService(TripService updatedTripService) {
        if(!this.tripServiceRepository.existsById(updatedTripService.getId())){
            return null;
        }
        return this.tripServiceRepository.save(updatedTripService);
    }

    public boolean deleteService(Long id) {
        boolean doesExist = this.tripServiceRepository.existsById(id);
        if(!doesExist){
            return false;
        }
        this.tripServiceRepository.deleteById(id);
        return true;
    }

    public Optional<TripService> getByCityName(String cityName) {
        return Optional.of(tripServiceRepository.getByAddressCityName(cityName));
    }

    public Optional<List<TripService>> getAllTripServices() {
        return Optional.of(tripServiceRepository.findAll());
    }

    public Optional<List<TripService>> getAllTripServies(int maximumNumber) {
        Pageable limit = PageRequest.of(0,maximumNumber);
        return Optional.of(tripServiceRepository.findAll(limit).getContent());
    }
}
