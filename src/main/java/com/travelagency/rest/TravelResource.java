package com.travelagency.rest;

import com.travelagency.controllers.TravelController;
import com.travelagency.model.Travel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/travels")
public class TravelResource {

    private final TravelController travelController;

    public TravelResource(TravelController travelController) {
        this.travelController = travelController;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Travel> getTravelById(@PathVariable Long id) {
        return travelController.getById(id);
    }

    public Optional<Travel> createTravel(Travel travel) {
        return travelController.createTravel(travel);
    }

    @RequestMapping(value = "/all/{limit}", method = RequestMethod.GET)
    public Optional<List<Travel>> getAll(@PathVariable int limit) {
        return travelController.getAllTravels(limit);
    }
}
