package com.travelagency.controllers;

import com.travelagency.model.Travel;
import com.travelagency.model.TripItem;
import com.travelagency.repository.TravelRepository;
import com.travelagency.repository.TripItemRepository;
import org.springframework.data.domain.Page;
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
        return Optional.ofNullable(travelRepository.getOne(id));
    }

    public Optional<Travel> createTravel(Travel travel) {
        if(travel == null) return Optional.empty();

        List<TripItem> tripItems = new ArrayList<>();

        List<TripItem> travelTripItems = travel.getTripItems();
        if (travelTripItems != null) {
            for (TripItem tripItem : travelTripItems) {
                Optional<TripItem> item = tripItemRepository.findById(tripItem.getId());
                item.ifPresent(tripItems::add);
            }
        }

        travel.setTripItems(tripItems);

        return Optional.ofNullable(travelRepository.save(travel));
    }

    public Optional<List<Travel>> getAllTravels(int maximumNumber) {
        Pageable limit = maximumNumber > 0 ? PageRequest.of(0,maximumNumber) : Pageable.unpaged();
        Page<Travel> travels = travelRepository.findAll(limit);

        if (travels == null) return Optional.empty();
        return Optional.of(travels.getContent());
    }
}
