package com.travelagency.controllers;

import com.travelagency.model.TripItem;
import com.travelagency.repository.TripItemRepository;
import com.travelagency.repository.TripRepository;
import com.travelagency.model.Trip;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TripController {

    private final TripRepository tripRepository;
    private final TripItemRepository tripItemRepository;

    public TripController(TripRepository tripRepository, TripItemRepository tripItemRepository){
        this.tripRepository = tripRepository;
        this.tripItemRepository = tripItemRepository;
    }

    public Optional<Trip> createTrip(Trip trip) {
        if(trip == null) return Optional.empty();

        List<TripItem> tripItems = new ArrayList<>();
        for (TripItem tripItem : trip.getTripItems()) {
            tripItems.add(tripItemRepository.findById(tripItem.getId()).get());
        }

        trip.setTripItems(tripItems);

        return Optional.of(tripRepository.save(trip));
    }

    public Optional<Trip> getById(Long id) {
        return Optional.of(tripRepository.getOne(id));
    }

    public Trip updateTrip(Trip updatedTrip) {
        if(!tripRepository.existsById(updatedTrip.getId())){
            return null;
        }
        return tripRepository.save(updatedTrip);
    }

    public boolean deleteTrip(Long id) {
        boolean doesExist = tripRepository.existsById(id);
        if(!doesExist){
            return false;
        }
        tripRepository.deleteById(id);
        return true;
    }

    //TODO: Search trips methode ->Ismail
    public Iterable<Trip> searchTrips(String searchInput) {
        if(searchInput == null || searchInput.isEmpty()){
            return null;
        }

        String[] searchKeywords = searchInput.split(" ");
        Set<Trip> result = new HashSet<>();
        for (String searchKeyword: searchKeywords) {
            result.addAll(tripRepository.findByNameContains(searchKeyword));
        }
        return result;
    }

    public Optional<List<Trip>> getAllTrips() {
        return Optional.of(tripRepository.findAll());
    }

    public Optional<List<Trip>> getAllTrips(int maximumNumber) {
        Pageable limit = PageRequest.of(0,maximumNumber);
        return Optional.of(tripRepository.findAll(limit).getContent());
    }
}
