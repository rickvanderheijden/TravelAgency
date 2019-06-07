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

    private final HotelController hotelController;

    public HotelResource(HotelController hotelController) {
        this.hotelController = hotelController;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel Hotel) {
        Optional<Hotel> createdHotel = hotelController.createHotel(Hotel);
        return createdHotel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Optional<List<Hotel>> getAll() {
        return hotelController.getAllHotels();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Hotel> getById( @PathVariable("id") long id) {
        return Optional.ofNullable(hotelController.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteHotel(@PathVariable("id") long id) {
        return hotelController.deleteHotel(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Hotel> update(@PathVariable final Long id, @RequestBody Hotel Hotel) {
        return hotelController.updateHotel(id,Hotel);
    }

    @GetMapping(value = "/city/{name}")
    public Optional<List<Hotel>> getHotelsByCityName(@PathVariable("name") String cityName) {
        return hotelController.getByCityName(cityName);
    }

    @GetMapping(value = "/getAvailability/{id}")
    public int getAvailability(@PathVariable("id") long id) {
        return hotelController.getAvailability(id);
    }

}
