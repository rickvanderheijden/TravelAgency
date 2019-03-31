package com.travelagency.controller;

import com.travelagency.services.TripService;
import com.travelagency.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @RequestMapping(value = "/createTrip", method = RequestMethod.POST)
    public Trip createTrip(@Valid @RequestBody Trip trip) {
        return this.tripService.createTrip(trip);
    }

    @RequestMapping(value = "/updateTrip", method = RequestMethod.PUT)
    public ResponseEntity<Trip> updateTrip(@Valid @RequestBody Trip trip) {
        Trip updatedTrip = this.tripService.updateTrip(trip);
        if(updatedTrip == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTrip);
    }

    @RequestMapping(value = "/deleteTrip/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Trip> deleteTrip(@PathVariable long id) {
        boolean isDeleted = this.tripService.deleteTrip(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Trip> getById(@PathVariable long id) {
        Trip foundedTrip = this.tripService.getById(id);
        if(foundedTrip == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundedTrip);
    }
}
