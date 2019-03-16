package com.travelagency.controller;

import com.travelagency.services.TripService;
import com.travelagency.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/trips")
public class TripController {

    private TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @RequestMapping(value = "/createTrip", method = RequestMethod.POST)
    public Trip createTrip(@Valid @RequestBody Trip trip) {
        return this.tripService.createTrip(trip);
    }
}
