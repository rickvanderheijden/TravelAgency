package com.travelagency.rest;

import com.travelagency.controllers.TripItemController;
import com.travelagency.model.TripItem;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TripItem> createTripItem(@Valid @RequestBody TripItem tripItem) {

        Optional<TripItem> createdTripItem = tripItemController.createTripItem(tripItem);
        return createdTripItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<TripItem> getById(@PathVariable final Long id) {
        return Optional.ofNullable(tripItemController.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Optional<TripItem> update(@PathVariable final Long id, @RequestBody TripItem tripItem) {
        return Optional.ofNullable(tripItemController.updateTripItem(id,tripItem));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Optional<Boolean> update(@PathVariable final Long id) {
        return Optional.of(tripItemController.deleteTripItem(id));
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Optional<TripItem> getFirst() {
        return tripItemController.getFirst();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Optional<List<TripItem>> getAll() {
        return tripItemController.getAllTripItems();
    }

    @GetMapping(value = "/city/{name}")
    public Optional<List<TripItem>> getHotelsByCityName(@PathVariable("name") String cityName) {
        return tripItemController.getByCityName(cityName);
    }
}
