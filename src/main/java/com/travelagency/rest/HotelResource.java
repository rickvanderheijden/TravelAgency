package com.travelagency.rest;

import com.travelagency.controllers.HotelController;
import com.travelagency.model.Hotel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelResource {

    private final HotelController HotelController;

    public HotelResource(HotelController HotelController) {
        this.HotelController = HotelController;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel Hotel) {
        Optional<Hotel> createdHotel = HotelController.createHotel(Hotel);
        return createdHotel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Optional<List<Hotel>> getAll() {
        return HotelController.getAllHotels();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Hotel> getById( @PathVariable("id") long id) {
        return Optional.ofNullable(HotelController.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteHotel(@PathVariable("id") long id) {
        return HotelController.deleteHotel(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Hotel> update(@PathVariable final Long id, @RequestBody Hotel Hotel) {
        return HotelController.updateHotel(id,Hotel);
    }

}
