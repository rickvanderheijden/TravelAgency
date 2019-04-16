package com.travelagency.rest;

import com.travelagency.controllers.TripServiceController;
import com.travelagency.model.TripService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class TripServiceResource {

    private final TripServiceController tripServiceController;

    public TripServiceResource(TripServiceController tripServiceController) {
        this.tripServiceController = tripServiceController;
    }

    @RequestMapping(value = "/createService", method = RequestMethod.POST)
    public Optional<TripService> createService(@Valid @RequestBody TripService tripService) {
        return tripServiceController.createService(tripService);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<TripService> getById(@PathVariable final Long id) {
        return Optional.of(tripServiceController.getById(id));
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Optional<TripService> getFirst() {
        return tripServiceController.getFirst();
    }
}
