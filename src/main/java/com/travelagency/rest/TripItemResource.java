package com.travelagency.rest;

import com.travelagency.controllers.TripItemController;
import com.travelagency.model.Trip;
import com.travelagency.model.TripItem;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tripItems")
public class TripItemResource {

    private final TripItemController tripItemController;

    public TripItemResource(TripItemController tripItemController) {
        this.tripItemController = tripItemController;
    }

    @RequestMapping(value = "/createTripItem", method = RequestMethod.POST)
    public Optional<TripItem> createTripItem(@Valid @RequestBody TripItem tripItem) {
        return tripItemController.createTripItem(tripItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<TripItem> getById(@PathVariable final Long id) {
        return Optional.of(tripItemController.getById(id));
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Optional<TripItem> getFirst() {
        return tripItemController.getFirst();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Optional<List<TripItem>> getAll() {
        return tripItemController.getAllTripItems();
    }
}
