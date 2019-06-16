package com.travelagency.controllers;

import com.travelagency.model.Destination;
import com.travelagency.repository.DestinationRepository;
import com.travelagency.repository.TripRepository;
import com.travelagency.model.Trip;
import com.travelagency.rest.DataTranfersObjects.TripSearchDTO;
import org.springframework.data.domain.Page;
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

        return Optional.ofNullable(tripRepository.save(trip));
    }

    public Optional<Trip> getById(Long id) {
        return tripRepository.findById(id);
    }

    public Optional<Trip> updateTrip(Trip updatedTrip) {
        if (updatedTrip == null) return Optional.empty();

        if(!tripRepository.existsById(updatedTrip.getId())){
            return Optional.empty();
        }

        return Optional.ofNullable(tripRepository.save(updatedTrip));
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
        Pageable limit = maximumNumber > 0 ? PageRequest.of(0,maximumNumber) : Pageable.unpaged();
        Page<Trip> trips = tripRepository.findAll(limit);
        if (trips== null) return Optional.empty();
        return Optional.of(trips.getContent());
    }

    public Optional<List<Trip>> searchTripsByKeywordAndContinentOrCountry(TripSearchDTO search) {
        if (search == null) return Optional.empty();

        List<Trip> tripsFromDestination;
        List<Trip> tripsFromDate;
        String keyword = search.getKeyword();

        tripsFromDate = getTripsFromDate(search);
        tripsFromDestination = getTripsFromDestination(search);

        if (search.fromPresent() || search.toPresent()) {
            tripsFromDate.retainAll(tripsFromDestination);
        } else {
            tripsFromDate = tripsFromDestination;
        }

        if (search.keywordPresent()) {
            List<Trip> response = new ArrayList<>();
            String[] keywords = keyword.split(" ");
            for(String word : keywords) {
                for (Trip trip : tripsFromDate) {
                    if (
                            trip.getDescription().toLowerCase().contains(word.toLowerCase()) ||
                            trip.getSummary().toLowerCase().contains(word.toLowerCase()) ||
                            trip.getName().toLowerCase().contains(word.toLowerCase())
                    ) {
                        if (!response.contains(trip)) response.add(trip);
                    }
                }
            }

            return Optional.of(response);
        } else {
            return Optional.of(tripsFromDate);
        }
    }

    private List<Trip> getTripsFromDate(TripSearchDTO tripSearch) {
        List<Trip> tripsFromDate = new ArrayList<>();

        if (tripSearch.fromPresent() && tripSearch.toPresent()) {
            tripsFromDate.addAll(tripRepository.findDistinctByAvailableFromLessThanAndAvailableToGreaterThan(tripSearch.getFrom(), tripSearch.getTo()));
        } else if (tripSearch.fromPresent()) {
            tripsFromDate.addAll(tripRepository.findDistinctByAvailableFromLessThanAndAvailableToGreaterThan(tripSearch.getFrom(), tripSearch.getFrom()));
        } else if (tripSearch.toPresent()) {
            tripsFromDate.addAll(tripRepository.findDistinctByAvailableFromLessThanAndAvailableToGreaterThan(tripSearch.getTo(), tripSearch.getTo()));
        }

        if (!tripsFromDate.isEmpty()) {
            tripsFromDate.stream().distinct().collect(Collectors.toList());
        }

        return tripsFromDate;
    }

    private List<Trip> getTripsFromDestination(TripSearchDTO tripSearch) {
        List<Trip> tripsFromDestination = new ArrayList<>();
        String keyword = tripSearch.getKeyword();



            if (tripSearch.countryPresent()) {
                tripsFromDestination.addAll(tripRepository.findDistinctByDestinations_City_Country_Name(tripSearch.getCountry()));
            } else if (tripSearch.continentPresent()) {
                tripsFromDestination.addAll(tripRepository.findDistinctByDestinations_City_Country_Continent_Name(tripSearch.getContinent()));
            } else if (tripSearch.keywordPresent()) {
                String[] keywords = keyword.split(" ");
                for(String word : keywords) {
                    tripsFromDestination.addAll(tripRepository.findDistinctByNameContainsOrSummaryContainsOrDescriptionContains(word, word, word));
                }
            }

        return tripsFromDestination;
    }
}
