package com.travelagency.rest;

import com.travelagency.controllers.DestinationController;
import com.travelagency.model.Destination;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinations")
public class DestinationResource {

    private final DestinationController destinationController;

    public DestinationResource(DestinationController destinationController) {
        this.destinationController = destinationController;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Destination> createDestination(@Valid @RequestBody Destination destination) {
        Optional<Destination> createdDestination = destinationController.createDestination(destination);
        return createdDestination.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Optional<List<Destination>> getAll() {
        return destinationController.getAllDestinations();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Destination> getById( @PathVariable("id") long id) {
        return Optional.ofNullable(destinationController.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteDestination(@PathVariable("id") long id) {
        return destinationController.deleteDestination(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Destination> update(@PathVariable final Long id, @RequestBody Destination destination) {
        return Optional.ofNullable(destinationController.updateDestination(id,destination));
    }

}
