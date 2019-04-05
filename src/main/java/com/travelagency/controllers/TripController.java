package com.travelagency.controllers;

import com.travelagency.repository.ITripRepository;
import com.travelagency.model.Trip;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TripController {

    private ITripRepository tripRepository;

    public TripController(ITripRepository tripRepository){
        this.tripRepository = tripRepository;
    }

    public Optional<Trip> createTrip(Trip trip) {
        if(trip.getId() != 0){
            return null;
        }
        return Optional.ofNullable(tripRepository.save(trip));
    }

    public Trip getById(long id) { return this.tripRepository.getOne(id); }

    public Trip updateTrip(Trip updatedTrip) {
        if(!this.tripRepository.existsById(updatedTrip.getId())){
            return null;
        }
        return this.tripRepository.save(updatedTrip);
    }

    public boolean deleteTrip(long id) {
        boolean doesExist = this.tripRepository.existsById(id);
        if(!doesExist){
            return false;
        }
        this.tripRepository.deleteById(id);
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
            result.addAll(this.tripRepository.findByNameContains(searchKeyword));
        }
        return result;
    }

}
