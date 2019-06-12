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
import java.util.stream.Collectors;

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
    
    public Optional<List<Trip>> getAllTrips() {
        return Optional.of(tripRepository.findAll());
    }

    public Optional<List<Trip>> getAllTrips(int maximumNumber) {
        Pageable limit = PageRequest.of(0,maximumNumber);
        return Optional.of(tripRepository.findAll(limit).getContent());
    }

    public Optional<List<Trip>> searchTripsByKeywordAndContinentOrCountry(TripSearchDTO search) {
        List<Trip> result = new ArrayList<>();
        List<Trip> distinct = new ArrayList<>();
        String keyword = search.getKeyword();

        if (search.fromPresent()) {
            distinct.addAll(tripRepository.findDistinctByAvailableFromLessThanAndAvailableToGreaterThan(search.getFrom(), search.getFrom()));
        } else if (search.toPresent()) {
            distinct.addAll(tripRepository.findDistinctByAvailableFromLessThanAndAvailableToGreaterThan(search.getTo(), search.getTo()));
        }

        if (!distinct.isEmpty()) {
            distinct.stream().distinct().collect(Collectors.toList());
        }

        if (search.countryPresent()) {
            result.addAll(tripRepository.findDistinctByDestinations_City_Country_Name(search.getCountry()));
        } else if (search.continentPresent()) {
            result.addAll(tripRepository.findDistinctByDestinations_City_Country_Continent_Name(search.getContinent()));
        } else if (search.keywordPresent()) {
            result.addAll(tripRepository.findDistinctByNameContainsOrSummaryContainsOrDescriptionContains(keyword, keyword, keyword));
        }

        if (search.fromPresent() || search.toPresent()) {
            distinct.retainAll(result);
        } else {
            distinct = result;
        }

        if (search.keywordPresent()) {
            List<Trip> response = new ArrayList<>();
            for (Trip trip : distinct) {
                if (trip.getDescription().toLowerCase().contains(keyword.toLowerCase()) || trip.getSummary().toLowerCase().contains(keyword.toLowerCase()) || trip.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    response.add(trip);
                }
            }
            return Optional.of(response);
        } else {
            return Optional.of(distinct);
        }
    }
}
