package com.travelagency.controllers;

import com.travelagency.model.Travel;
import com.travelagency.model.TripItem;
import com.travelagency.repository.TravelRepository;
import com.travelagency.repository.TripItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TravelController {

    private final TravelRepository travelRepository;
    private final TripItemRepository tripItemRepository;

    public TravelController(TravelRepository travelRepository, TripItemRepository tripItemRepository){
        this.travelRepository = travelRepository;
        this.tripItemRepository = tripItemRepository;
    }

    public Optional<Travel> getById(Long id) {
        return Optional.of(travelRepository.getOne(id));
    }

    public Optional<Travel> createTravel(Travel travel) {
        if(travel == null) return Optional.empty();

        List<TripItem> tripItems = new ArrayList<>();

        List<TripItem> travelTripItems = travel.getTripItems();
        if (travelTripItems != null) {
            for (TripItem tripItem : travelTripItems) {
                tripItems.add(tripItemRepository.findById(tripItem.getId()).get());
            }
        }

        travel.setTripItems(tripItems);

        return Optional.of(travelRepository.save(travel));
    }

    public Optional<List<Travel>> getAllTravels(int maximumNumber) {
        Pageable limit = PageRequest.of(0,maximumNumber);
        return Optional.of(travelRepository.findAll(limit).getContent());
    }
}
