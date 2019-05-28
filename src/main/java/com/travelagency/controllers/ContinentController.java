package com.travelagency.controllers;

import com.travelagency.model.Continent;
import com.travelagency.repository.ContinentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContinentController {

    private final ContinentRepository continentRepository;

    public ContinentController(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public Optional<List<Continent>> getAllContinents() {
        return Optional.of(this.continentRepository.findAll());
    }
}
