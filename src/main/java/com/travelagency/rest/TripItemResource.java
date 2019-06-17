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

    @PostMapping(value = "/createTripItem")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TripItem> createTripItem(@Valid @RequestBody TripItem tripItem) {

        Optional<TripItem> createdTripItem = tripItemController.createTripItem(tripItem);
        return createdTripItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}")
    public Optional<TripItem> getById(@PathVariable final Long id) {
        return tripItemController.getById(id);
    }

    @PutMapping()
    public Optional<TripItem> update(@RequestBody TripItem tripItem) {
        return tripItemController.updateTripItem(tripItem);
    }

    @DeleteMapping(value = "/{id}")
    public Optional<Boolean> delete(@PathVariable final Long id) {
        return Optional.of(tripItemController.deleteTripItem(id));
    }

    @GetMapping(value = "/first")
    public Optional<TripItem> getFirst() {
        return tripItemController.getFirst();
    }

    @GetMapping(value = "/all")
    public Optional<List<TripItem>> getAll() {
        return tripItemController.getAllTripItems();
    }

    @GetMapping(value = "/city/{name}")
    public Optional<List<TripItem>> getHotelsByCityName(@PathVariable("name") String cityName) {
        return tripItemController.getByCityName(cityName);
    }
}
