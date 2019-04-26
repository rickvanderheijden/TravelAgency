package com.travelagency.controllers;

import com.travelagency.model.Trip;
import com.travelagency.model.TripItem;
import com.travelagency.repository.TripItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripItemController {

    private final TripItemRepository tripItemRepository;

    public TripItemController(TripItemRepository tripItemRepository) {
        this.tripItemRepository = tripItemRepository; }

    public Optional<TripItem> createTripItem(TripItem tripItem) {
        return Optional.of(tripItemRepository.save(tripItem));
    }

    public TripItem getById(Long id) {
        return this.tripItemRepository.getOne(id);
    }

    public Optional<TripItem> getFirst() {
        return tripItemRepository.findAll().stream().findFirst();
    }

    public TripItem updateTripItem(TripItem updatedTripItem) {
        if(!this.tripItemRepository.existsById(updatedTripItem.getId())){
            return null;
        }
        return this.tripItemRepository.save(updatedTripItem);
    }

    public boolean deleteTripItem(Long id) {
        boolean doesExist = this.tripItemRepository.existsById(id);
        if(!doesExist){
            return false;
        }
        this.tripItemRepository.deleteById(id);
        return true;
    }

    public Optional<TripItem> getByCityName(String cityName) {
        return Optional.of(tripItemRepository.getByAddressCityName(cityName));
    }

    public Optional<List<TripItem>> getAllTripItems() {
        return Optional.of(tripItemRepository.findAll());
    }
}
