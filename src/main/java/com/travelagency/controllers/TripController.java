package com.travelagency.controllers;

import com.travelagency.model.Destination;
import com.travelagency.repository.DestinationRepository;
import com.travelagency.repository.TripRepository;
import com.travelagency.model.Trip;
import com.travelagency.rest.DataTranfersObjects.TripSearchDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TripController {

    private final TripRepository tripRepository;
    private final DestinationRepository destinationRepository;

    public TripController(TripRepository tripRepository, DestinationRepository destinationRepository){
        this.tripRepository = tripRepository;
        this.destinationRepository = destinationRepository;
    }

    public Optional<Trip> createTrip(Trip trip) {
        if(trip == null) return Optional.empty();

        List<Destination> destinations = new ArrayList<>();

        if (trip.getDestinations() != null) {
            for (Destination destination : trip.getDestinations()) {
                Optional<Destination> dest = destinationRepository.findById(destination.getId());
                dest.ifPresent(destinations::add);
            }
        }

        trip.setDestinations(destinations);

        return Optional.of(tripRepository.save(trip));
    }

    public Optional<Trip> getById(Long id) {
        return tripRepository.findById(id);
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

    //TODO: Search trips method ->Ismail
    public Iterable<Trip> searchTrips(String searchInput) {
        if(searchInput == null || searchInput.isEmpty()){
            return null;
        }

        String[] searchKeywords = searchInput.split(" ");
        Set<Trip> result = new HashSet<>();
        for (String searchKeyword: searchKeywords) {
            result.addAll(tripRepository.findDistinctByNameContains(searchKeyword));
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

    public Optional<List<Trip>> searchTripsFilter(TripSearchDTO search) {
        if(search.getContinent() != null && search.getCountry() != null && search.getFrom() != null && search.getTo() != null) {
            return Optional.empty();
        }

        if(search.getContinent() != null && search.getCountry() != null && search.getFrom() != null) {
            return Optional.empty();
        }

        if(search.getCountry() != null) {
            return Optional.of(this.tripRepository.findDistinctByDestinations_City_Country_Name(search.getCountry()));
        }

        if(search.getContinent() != null) {
            return Optional.of(this.tripRepository.findDistinctByDestinations_City_Country_Continent_Name(search.getContinent()));
        }

        return Optional.empty();
    }
}
