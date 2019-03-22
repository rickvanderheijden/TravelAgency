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

}
