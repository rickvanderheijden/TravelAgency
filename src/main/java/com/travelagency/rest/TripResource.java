package com.travelagency.rest;

import com.travelagency.controllers.TripController;
import com.travelagency.model.Trip;
import com.travelagency.rest.DataTranfersObjects.TripSearchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripResource {

    private final TripController tripController;

    public TripResource(TripController tripController) {
        this.tripController = tripController;
    }

    @RequestMapping(value = "/createTrip", method = RequestMethod.POST)
    public Optional<Trip> createTrip(@Valid @RequestBody Trip trip) {
        return tripController.createTrip(trip);
    }

    @RequestMapping(value = "/updateTrip", method = RequestMethod.PUT)
    public ResponseEntity<Trip> updateTrip(@Valid @RequestBody Trip trip) {
        Trip updatedTrip = this.tripController.updateTrip(trip);
        if(updatedTrip == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTrip);
    }

    @RequestMapping(value = "/deleteTrip/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Trip> deleteTrip(@PathVariable Long id) {
        boolean isDeleted = this.tripController.deleteTrip(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Trip> getById(@PathVariable final Long id) {
        return tripController.getById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Optional<List<Trip>> getAll() {
        return tripController.getAllTrips();
    }

    @RequestMapping(value = "/all/{limit}", method = RequestMethod.GET)
    public Optional<List<Trip>> getAll(@PathVariable int limit) {
        return tripController.getAllTrips(limit);
    }

    @RequestMapping(value = "/searchTrips", method = RequestMethod.POST)
    public Optional<List<Trip>> search(@Valid @RequestBody TripSearchDTO search) { return tripController.searchTripsFilter(search); }
}
