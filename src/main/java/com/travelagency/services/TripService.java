package com.travelagency.services;

import com.travelagency.repository.ITripRepository;
import com.travelagency.model.Trip;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    private ITripRepository tripRepository;

    public TripService(ITripRepository tripRepository){
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(Trip trip) {
        if(trip.getId() != 0){
            return null;
        }
        return this.tripRepository.save(trip);
    }

    public Trip getById(long id) { return this.tripRepository.getOne(id); }

    public Trip updateTrip(Trip updatedTrip) {
        Trip existingTrip = this.tripRepository.getOne(updatedTrip.getId());
        if(existingTrip == null){
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

}
