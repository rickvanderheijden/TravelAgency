package com.travelagency.controllers;

import com.travelagency.model.Traveler;
import com.travelagency.repository.TravelerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelerController {

    private final TravelerRepository travelerRepository;

    public TravelerController(TravelerRepository travelerRepository) {
        this.travelerRepository = travelerRepository;
    }

    public Optional<Traveler> createTraveler(Traveler traveler) {
        if (traveler == null) return Optional.empty();

        return Optional.of(travelerRepository.save(traveler));
    }

}
